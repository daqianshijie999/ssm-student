package com.srgstart.mapper;

import com.srgstart.entity.Clazz;

import java.util.List;
import java.util.Map;


public interface ClazzMapper {
    public int create(Clazz pi);

    public int delete(Map<String, Object> paramMap);

    public int update(Map<String, Object> paramMap);

    public List<Clazz> query(Map<String, Object> paramMap);

    public Clazz detail(Map<String, Object> paramMap);

    public int count(Map<String, Object> paramMap);
}