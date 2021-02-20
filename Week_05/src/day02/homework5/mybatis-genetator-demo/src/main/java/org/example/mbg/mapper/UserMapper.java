package org.example.mbg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.mbg.domain.User;
import org.example.mbg.domain.UserExample;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);
}