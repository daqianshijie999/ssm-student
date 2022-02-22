package com.srgstart.mapper;

import com.srgstart.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @Author srgstart
 * @Create 2021/5/24 16:54
 * @Description TODO
 */
public interface UserMapper {

    public int create(User pi);

    public int delete(Map<String, Object> paramMap);

    public int update(Map<String, Object> paramMap);

    public List<User> query(Map<String, Object> paramMap);

    public User detail(Map<String, Object> paramMap);

    public int count(Map<String, Object> paramMap);
}
