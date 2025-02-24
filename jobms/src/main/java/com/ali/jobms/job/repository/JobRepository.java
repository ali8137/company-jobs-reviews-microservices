package com.ali.jobms.job.repository;

import com.ali.jobms.job.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long>
{

}
