package com.blogapplication.Repositories;

import com.blogapplication.Entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDAO extends JpaRepository<CommentEntity,Integer> {

}
