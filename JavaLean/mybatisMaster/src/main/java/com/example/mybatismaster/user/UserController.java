package com.example.mybatismaster.user;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.example.mybatismaster.entity.User;
import com.example.mybatismaster.mapper.UserMapper;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserController
 * @Author mrl
 * @Date 2022/7/9 19:37
 * @Version 1.6
 * @Description TODO
 **/
@Controller
public class UserController {

    @ResponseBody
    @RequestMapping("/")
    public String demo(){
        return "hello" ;
    }
    @Autowired(required=false)
    UserMapper userMapper;

    @RequestMapping("/getJson")
    @ResponseBody
    public Map<String,Object> getJson(@RequestBody JSONObject jsonParam) throws IOException {

        JSONObject jsonObject = new JSONObject();
        List<User> users = userMapper.queryAllUser();
        System.out.println(users);
        String s = JSON.toJSONString(users);
        jsonObject.put("status","200");
        jsonObject.put("method","json");
        jsonObject.put("data",s);
        return jsonObject;
    }

    // 访问操作
    @GetMapping("/user/queryAll")
    @ResponseBody
    public List<User> queryAll(){
        System.out.println("DEBUG : queryAll");
        return userMapper.queryAllUser();
    }
    @GetMapping("/user/queryByNameAndId/{name}/{id}")
    @ResponseBody
    public User queryByNameAndId(@PathVariable("id")Integer id,
                          @PathVariable("name")String name){
        System.out.println("DEBUG : queryByNameAndId");
        return userMapper.queryById(id);
    }
    @GetMapping("/user/queryById/{id}")
    @ResponseBody
    public User queryById(@PathVariable("id")Integer id){
        System.out.println("DEBUG : queryById");
        return userMapper.queryById(id);
    }

    //  更新操作
    @PutMapping("/user/update")
    @ResponseBody
    public boolean update(@RequestBody User user){
        System.out.println("DEBUG : update");
        return userMapper.updateUserById(user);
    }

    // 插入操作
    @PostMapping("/user/insert")
    @ResponseBody
    public boolean insert(@RequestBody User user){
        System.out.println("DEBUG : insert");
            return userMapper.insertUser(user);
    }
    // 删除操作

    @DeleteMapping("/user/deleteById/{id}")
    @ResponseBody
    public boolean deleteById(@PathVariable("id") Integer id){
        System.out.println("DEBUG : deleteById");
        return userMapper.deleteUserById(id);
    }




}
