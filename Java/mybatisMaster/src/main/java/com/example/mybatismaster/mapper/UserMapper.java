package com.example.mybatismaster.mapper;

import com.example.mybatismaster.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * @ClassName UserMapper
 * @Author mrl
 * @Date 2022/7/9 19:38
 * @Version 1.6
 * @Description TODO
 **/

public interface UserMapper {

   User queryById(Integer idd);

   List<User> queryAllUser();

   boolean updateUserById(User user);

   boolean deleteUserById(Integer id);

   boolean insertUser(User user);

}
