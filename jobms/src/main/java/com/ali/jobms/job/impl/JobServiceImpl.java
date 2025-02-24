package com.ali.jobms.job.impl;

import com.ali.jobms.job.Job;
import com.ali.jobms.job.JobService;
import com.ali.jobms.job.clients.CompanyClient;
import com.ali.jobms.job.clients.ReviewClient;
import com.ali.jobms.job.dto.JobDTO;
import com.ali.jobms.job.external.Company;
import com.ali.jobms.job.external.Review;
import com.ali.jobms.job.mapper.JobMapper;
import com.ali.jobms.job.repository.JobRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    JobRepository jobRepository;
    @Autowired
    CompanyClient companyClient;
    @Autowired
    ReviewClient reviewClient;

    int attempt = 0;

    public JobServiceImpl(
            JobRepository jobRepository,
            CompanyClient companyClient,
            ReviewClient reviewClient
    ) {
            this.jobRepository = jobRepository;
            this.companyClient = companyClient;
            this.reviewClient = reviewClient;
    }

    private JobDTO convertToDTO(Job job) {
        Company company = companyClient.getCompany(job.getCompanyId());
        List<Review> reviews = reviewClient.getReviews(job.getCompanyId());

        JobDTO jobDTO = JobMapper
                .mapToJobWithCompanyDTO(job, company, reviews);

        return jobDTO;
    }


    @CircuitBreaker(
            name = "companyBreaker",
            fallbackMethod = "companyBreakerFallback"
    )
    @Retry(
            name = "companyBreaker",
            fallbackMethod = "companyBreakerFallback"
    )
    @RateLimiter(name = "companyBreaker")
    public List<JobDTO> findAll() {
        System.out.println("Attempt: " + ++attempt);

        List<Job> jobs = jobRepository.findAll();
        List<JobDTO> jobDTOS = new ArrayList<>();

        return jobs.stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<String> companyBreakerFallback(Exception e) {
                List<String> list = new ArrayList<>();
                list.add("dummy");
                return list;
    }


    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public JobDTO getJobById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);

        return convertToDTO(job);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);

        if(jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());

            jobRepository.save(job);

            return true;
        }

        return false;
    }

}
