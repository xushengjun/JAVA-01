package com.gane.maple.service.impl;

import com.gane.maple.dao.UserDAO;
import com.gane.maple.entity.User;
import com.gane.maple.jdbc.routing.ClientDataSourceContextHolder;
import com.gane.maple.jdbc.routing.annotation.ReadOnly;
import com.gane.maple.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    /**
     * 把 @DataSourceRouting 放进 dao 层
     *
     * @param userId
     * @return
     */
    @Override
    @ReadOnly
    public User selectByUserId(String userId) {
        User userFromMaster = userDAO.selectByUserId(userId);
        System.out.println("查询slave库：" + ClientDataSourceContextHolder.getClientDatabase());
        return userFromMaster;
    }

    /**
     * 把 @DataSourceRouting 放进 dao 层
     *
     * @param userName
     * @return
     */
    @Override
    @ReadOnly
    public User selectByUserName(String userName) {
        User userFromSlave = userDAO.selectByUserName(userName);
        System.out.println("查询slave库：" + ClientDataSourceContextHolder.getClientDatabase());
        return userFromSlave;
    }

    @Override
    public User selectByUserNameFromMaster(String userName) {
        User userFromSlave = userDAO.selectByUserName(userName);
        System.out.println("查询master库：" + ClientDataSourceContextHolder.getClientDatabase());
        return userFromSlave;
    }

    @Override
    public int insert(User user) {
        System.out.println("查询master库：" + ClientDataSourceContextHolder.getClientDatabase());
        return userDAO.inser(user);
    }


}
