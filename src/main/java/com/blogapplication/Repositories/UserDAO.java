package com.blogapplication.Repositories;

import com.blogapplication.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Add the Entity Name of particular table and the primarty key datatype in arguments
@Repository
public interface UserDAO extends JpaRepository<UserEntity,Integer> {
}
