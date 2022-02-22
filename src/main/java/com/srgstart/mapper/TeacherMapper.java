package com.srgstart.mapper;

import com.srgstart.entity.Teacher;

import java.util.List;
import java.util.Map;


public interface TeacherMapper {
    public int create(Teacher pi);

    public int delete(Map<String, Object> paramMap);

    public int update(Map<String, Object> paramMap);

    public List<Teacher> query(Map<String, Object> paramMap);

    public Teacher detail(Map<String, Object> paramMap);

    public int count(Map<String, Object> paramMap);
}