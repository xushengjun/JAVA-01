package com.gane.maple.service.impl;

import com.gane.maple.dao.UserDAO;
import com.gane.maple.entity.User;
import com.gane.maple.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


//    @Autowired
    private UserDAO userDAO;

    /**
     * 把 @DataSourceRouting 放进 dao 层
     *
     * @param userId
     * @return
     */
    @Override
    public User selectByUserId(String userId) {
        User userFromMaster = userDAO.selectByUserId(userId);
        return userFromMaster;
    }

    public List<User> selectUsers(){
        String sql = "select * from user";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<User>(User.class));
    }

    /**
     * 把 @DataSourceRouting 放进 dao 层
     *
     * @param userName
     * @return
     */
    @Override
    public User selectByUserName(String userName) {
        User userFromSlave = userDAO.selectByUserName(userName);
        return userFromSlave;
    }

    @Override
    public User selectByUserNameFromMaster(String userName) {
        User userFromSlave = userDAO.selectByUserName(userName);
        return userFromSlave;
    }

    @Override
    public int insert(User user) {
        return userDAO.inser(user);
    }


}
