package com.srgstart.mapper;

import com.srgstart.entity.Job;

import java.util.List;
import java.util.Map;


public interface JobMapper {
    public int create(Job pi);

    public int delete(Map<String, Object> paramMap);

    public int update(Map<String, Object> paramMap);

    public List<Job> query(Map<String, Object> paramMap);

    public Job detail(Map<String, Object> paramMap);

    public int count(Map<String, Object> paramMap);
}