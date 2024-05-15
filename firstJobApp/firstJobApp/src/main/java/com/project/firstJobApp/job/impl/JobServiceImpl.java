package com.project.firstJobApp.job.impl;

import com.project.firstJobApp.job.Job;
import com.project.firstJobApp.job.JobRepository;
import com.project.firstJobApp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    //private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;

    // JobRepository is a bean that will be managed by spring and because of this constructor it will be autowired at the runtime
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job findJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isPresent()) {
            jobRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateJobById(Long id, Job updatedjob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setDescription(updatedjob.getDescription());
            job.setTitle(updatedjob.getTitle());
            job.setLocation(updatedjob.getLocation());
            job.setMinSalary(updatedjob.getMinSalary());
            job.setMaxSalary(updatedjob.getMaxSalary());
            jobRepository.save(job);
            return true;
        }
        return false;
    }


}
