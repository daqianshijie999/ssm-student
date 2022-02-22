package com.srgstart.mapper;


import com.srgstart.entity.Request;

import java.util.List;
import java.util.Map;

public interface RequestMapper {
    public int create(Request pi);

    public int delete(Map<String, Object> paramMap);

    public int update(Map<String, Object> paramMap);

    public List<Request> query(Map<String, Object> paramMap);

    public Request detail(Map<String, Object> paramMap);

    public int count(Map<String, Object> paramMap);
}