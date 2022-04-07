package com.mycompany.careers.data;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class JobPost implements Comparable<JobPost>{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String jobTitle;
  private String jobDescription;
  private String location;

  //@OneToMany (mappedBy = "jobSeekerId", targetEntity = JobSeekerProfile.class)
  //@JoinColumn(name="jobSeekerId")
  @ManyToMany(mappedBy = "jobPosts",fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
private Set<JobSeekerProfile> jobseekers;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
  }

  public String getJobDescription() {
    return jobDescription;
  }

  public void setJobDescription(String jobDescription) {
    this.jobDescription = jobDescription;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

/*
  public JobSeekerProfile getJobseekers() {
    return jobseekers;
  }

  public void setJobseekers(JobSeekerProfile jobseekers) {
    this.jobseekers = jobseekers;
  }
  */

  public Set<JobSeekerProfile> getJobseekers() {
    return jobseekers;
  }

  public void setJobseekers(Set<JobSeekerProfile> jobseekers) {
    this.jobseekers = jobseekers;
  }

  @Override
  public int compareTo(JobPost jobPost) {
    return 0;
  }
}
