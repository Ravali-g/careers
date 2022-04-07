package com.mycompany.careers.data;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

public interface JobSeekerRepository extends CrudRepository<JobSeekerProfile, Long> {
  @Transactional
  void deleteByEmail(String email);
}
