package com.play.moduls.user.repository.Impl;

import com.play.moduls.user.bean.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/2/6.
 */
@Repository
public class UserDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public User getById(Integer id){
        String sql = "select * from user where id = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
        return jdbcTemplate.queryForObject(sql,rowMapper,id);
    }
}
