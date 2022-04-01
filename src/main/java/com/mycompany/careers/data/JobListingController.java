package com.mycompany.careers.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/careers")
public class JobListingController {

  @Autowired
  private JobRepository jobRepository;

  @GetMapping()
  public @ResponseBody Iterable<JobPost> getJobList(){
    System.out.println("in default method");
        return jobRepository.findAll();
  }

}
