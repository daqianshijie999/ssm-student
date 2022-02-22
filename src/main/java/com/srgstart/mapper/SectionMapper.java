package com.srgstart.mapper;

import com.srgstart.entity.Section;

import java.util.List;
import java.util.Map;


public interface SectionMapper {
    public int create(Section pi);

    public int delete(Map<String, Object> paramMap);

    public int update(Map<String, Object> paramMap);

    public List<Section> query(Map<String, Object> paramMap);

    public Section detail(Map<String, Object> paramMap);

    public int count(Map<String, Object> paramMap);

    public List<Section> queryByStudent(Map<String, Object> paramMap);

    public List<Section> queryByTeacher(Map<String, Object> paramMap);
}