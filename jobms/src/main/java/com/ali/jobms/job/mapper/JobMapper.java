package com.ali.jobms.job.mapper;

import com.ali.jobms.job.Job;
import com.ali.jobms.job.dto.JobDTO;
import com.ali.jobms.job.external.Company;
import com.ali.jobms.job.external.Review;

import java.util.List;

public class JobMapper {

    public static JobDTO mapToJobWithCompanyDTO(
            Job job,
            Company company,
            List<Review> reviews
    ) {
        JobDTO jobDTO = new JobDTO();

        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setLocation(job.getLocation());

        jobDTO.setCompany(company);

        jobDTO.setReviews(reviews);

        return jobDTO;
    }

}
