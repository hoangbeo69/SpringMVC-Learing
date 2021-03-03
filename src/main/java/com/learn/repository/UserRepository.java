package com.learn.repository;

import com.learn.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    /**
     *
     * @param name
     * @param status
     * @return
     * Hàm được viết bới Data Spring JPA
     */
    UserEntity findOneByUserNameAndStatus(String name,int status);
}
