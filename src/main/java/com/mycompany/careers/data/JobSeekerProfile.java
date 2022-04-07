package com.mycompany.careers.data;

import java.io.File;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class JobSeekerProfile implements Comparable<JobPost>{

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long jobSeekerId;
  private String firstName;
  private String lastName;
  private String email;
  private long jobId;

  public Set<JobPost> getJobPosts() {
    return jobPosts;
  }

  public void setJobPosts(Set<JobPost> jobPosts) {
    this.jobPosts = jobPosts;
  }

  @ManyToMany(cascade = CascadeType.MERGE)
  //@JoinColumn(name="id", referencedColumnName = "jobId")
  @JoinTable(
    name="JobApplication",
    joinColumns = @JoinColumn(name="jobSeekerId"),
    inverseJoinColumns = @JoinColumn(name="id")
  )
  private Set<JobPost> jobPosts;


  @Lob
  private byte[] resume;

  public long getJobSeekerId() {
    return jobSeekerId;
  }

  public void setJobSeekerId(long jobSeekerId) {
    this.jobSeekerId = jobSeekerId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public long getJobId() {
    return jobId;
  }

  public void setJobId(long jobId) {
    this.jobId = jobId;
  }

  public byte[] getResume() {
    return resume;
  }

  public void setResume(byte[] resume) {
    this.resume = resume;
  }


  @Override
  public int compareTo(JobPost job){
    return (int) (this.jobId - job.getId());
  }

}
