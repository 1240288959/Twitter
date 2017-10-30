package com.tanyang.twitter.dao;

import com.tanyang.twitter.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,String> {
    User getUserByEmailAndPassword(String email,String password);
}
