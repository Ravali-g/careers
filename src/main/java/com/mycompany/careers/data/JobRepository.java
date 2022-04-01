package com.mycompany.careers.data;

import org.springframework.data.repository.CrudRepository;
import com.mycompany.careers.data.JobPost;

public interface JobRepository extends CrudRepository<JobPost,Integer> {

}
