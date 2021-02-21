package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.UserInfo;

import java.util.List;
@Mapper
public interface UserInfoMapper {
    List<UserInfo> findAll();
}
