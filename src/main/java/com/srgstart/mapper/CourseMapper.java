package com.srgstart.mapper;

import com.srgstart.entity.Course;

import java.util.List;
import java.util.Map;



public interface CourseMapper {

    public int create(Course pi);

    public int delete(Map<String, Object> paramMap);

    public int update(Map<String, Object> paramMap);

    public List<Course> query(Map<String, Object> paramMap);

    public Course detail(Map<String, Object> paramMap);

    public int count(Map<String, Object> paramMap);
}