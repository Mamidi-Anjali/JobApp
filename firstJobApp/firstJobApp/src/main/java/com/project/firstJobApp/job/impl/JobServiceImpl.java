package com.project.firstJobApp.job.impl;

import com.project.firstJobApp.job.Job;
import com.project.firstJobApp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private List<Job> jobs = new ArrayList<>();
    private Long nextId =1L;

    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobs.add(job);
    }

    @Override
    public Job findJobById(Long id) {
        for(Job job: jobs){
            if(job.getId().equals(id)){
                return job;
            }
        }
        return null;
    }

    @Override
    public void deleteJobById(Long id) {
        for(Job job: jobs){
            if(job.getId().equals(id)){
                jobs.remove(job);
            }
        }
//        Iterator<Job> it = jobs.iterator();
//        while(it.hasNext()){
//            Job job = it.next();
//            if(job.getId().equals(id)){
//                it.remove();
//                return true;
//            }
//        }
//        return false;
    }

    @Override
    public boolean updateJobById(Long id, Job updatedjob) {
        for(Job job: jobs){
            if(job.getId().equals(id)){
                job.setDescription(updatedjob.getDescription());
                job.setTitle(updatedjob.getTitle());
                job.setLocation(updatedjob.getLocation());
                job.setMinSalary(updatedjob.getMinSalary());
                job.setMaxSalary(updatedjob.getMaxSalary());
                return true;
            }
        }
        return false;
    }


}
