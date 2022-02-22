package com.srgstart.service;


import com.srgstart.entity.User;
import com.srgstart.mapper.UserMapper;
import com.srgstart.util.BeanMapUtils;
import com.srgstart.util.MD5Utils;
import com.srgstart.util.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 添加管理员
     * @param pi
     * @return
     */
    public int create(User pi) {
        pi.setUserPwd(MD5Utils.getMD5(pi.getUserPwd()));
        return userMapper.create(pi);
    }

    /**
     * 根据id 删除指定管理员
     * @param id
     * @return
     */
    public int delete(Integer id) {
        return userMapper.delete(MapParameter.getInstance().addId(id).getMap());
    }

    /**
     * 根据id集合批量删除多个管理员
     * @param ids
     * @return
     */
    public int delete(String ids) {
        int flag = 0;
        for (String str : ids.split(",")) {
            flag = userMapper.delete(MapParameter.getInstance().addId(Integer.parseInt(str)).getMap());
        }
        return flag;
    }

    /**
     * 更新管理员信息
     * @param user
     * @return
     */
    public int update(User user) {
        Map<String, Object> map = MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(user)).addId(user.getId()).getMap();
        return userMapper.update(map);
    }

    /**
     * 条件查询管理员信息
     * @param user
     * @return
     */
    public List<User> query(User user) {
        return userMapper.query(BeanMapUtils.beanToMap(user));
    }

    /**
     * id查询指定管理员信息
     * @param id
     * @return
     */
    public User detail(Integer id) {
        return userMapper.detail(MapParameter.getInstance().addId(id).getMap());
    }

    /**
     * 计算管理员数量
     * @param user
     * @return
     */
    public int count(User user) {
        return userMapper.count(BeanMapUtils.beanToMap(user));
    }

    /**
     * 管理员登录方法
     * @param userName
     * @param password
     * @return
     */
    public User login(String userName,String password){
        Map<String, Object> map = MapParameter.getInstance()
                .add("userName", userName)
                .add("userPwd", password)
                .getMap();
        return userMapper.detail(map);
    }

}
