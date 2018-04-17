package com.play.moduls.user.repository;

import com.play.moduls.user.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2018/2/6.
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findById(Long id);

    User findByUsername(String username);

}
