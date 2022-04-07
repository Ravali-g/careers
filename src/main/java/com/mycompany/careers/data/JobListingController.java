package com.mycompany.careers.data;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;
import java.util.TreeSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MultipartFilter;

@Controller
@RequestMapping(path="/careers")
public class JobListingController {

  @Autowired
  private JobRepository jobRepository;
  @Autowired
  private JobSeekerRepository jobSeekerRepository;

  //Displays list of open jobs
  @GetMapping()
  public @ResponseBody Iterable<JobPost> getJobList(){
    String jobList="";
    Iterator<JobPost> jobsList = jobRepository.findAll().iterator();

    while(jobsList.hasNext()){
      JobPost job = jobsList.next();
      jobList.concat(job.getJobTitle()+"\t"+job.getLocation()+"\t"+job.getJobDescription());
    }
    //return new ResponseEntity<>(jobList, HttpStatus.OK);
    return jobRepository.findAll();

  }


  //Store user Data
  @PostMapping(path="/apply")
  public @ResponseBody String addApplicant(@RequestParam String firstName,
                                           @RequestParam String lastName,
                                           @RequestParam String email, @RequestParam long jobId,
                                           @RequestParam MultipartFile resume) throws IOException {

    JobSeekerProfile jobSeeker = new JobSeekerProfile();
    jobSeeker.setJobId(jobId);
    jobSeeker.setEmail(email);
    jobSeeker.setFirstName(firstName);
    jobSeeker.setLastName(lastName);
    jobSeeker.setResume(resume.getBytes());

    JobPost job = new JobPost();
    job.setId(jobId);

    //job.getJobseekers().add(jobSeeker);
    //job.setJobseekers(job.getJobseekers());
    if(jobSeeker.getJobPosts() != null){
      jobSeeker.getJobPosts().add(job);
    }else{
      Set<JobPost> jobSet = new TreeSet<JobPost>();
      jobSet.add(job);
      jobSeeker.setJobPosts(jobSet);
    }
    //jobSeeker.setJobPosts(jobSeeker.getJobPosts());

    jobSeekerRepository.save(jobSeeker);
    return "saved!";

  }

  // Get list of applicants
  @GetMapping(path="/applicants")
  public @ResponseBody Iterable<JobSeekerProfile> getApplicantList(){
    return jobSeekerRepository.findAll();
  }


  //Delete an applicant

  @DeleteMapping(path="/delete")
  public @ResponseBody String deleteApplicant(@RequestParam String email){
    //Optional<JobSeekerProfile> userProfile = jobSeekerRepository.findById(id);
    jobSeekerRepository.deleteByEmail(email);
    return "success";
  }

}
