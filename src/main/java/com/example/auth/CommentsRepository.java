package com.example.auth;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentsRepository extends CrudRepository<CommentsApplication,Integer> {
    @Query(value = "select * from comments_application where post_application_id=?1 ",nativeQuery = true)
    public List<CommentsApplication> findByPostApplicationId(Long id);

}
