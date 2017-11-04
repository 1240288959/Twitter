package com.tanyang.twitter.dao;

import com.tanyang.twitter.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserDao extends JpaRepository<User,String> {
    User getUserByEmailAndPassword(String email,String password);

    @Query(value="update user set status = 1 where email = :email",nativeQuery = true)
    @Modifying
    Integer updateStatusByEmail(@Param("email") String email);

    @Query(value = "update user set image=:image where id=:id",nativeQuery = true)
    @Modifying
    Integer updateImageById(@Param("image") String image,@Param("id") String id);

    @Query(value = "select *  from user where name like %:name%",nativeQuery = true)
    List<User> getUserByName(@Param("name") String name);
}
