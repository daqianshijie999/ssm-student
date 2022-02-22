package com.srgstart.mapper;

import com.srgstart.entity.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface StudentMapper {
    public int create(Student pi);

    public int delete(Map<String, Object> paramMap);

    public int update(Map<String, Object> paramMap);

    public List<Student> query(Map<String, Object> paramMap);

    public Student detail(Map<String, Object> paramMap);

    public int count(Map<String, Object> paramMap);

    /**
     * 查询选课的学生
     * @param paramMap
     * @return
     */
    public List<HashMap> querySelectStudent(Map<String, Object> paramMap);

    /**
     * 根据老师查询学生信息
     * @param paramMap
     * @return
     */
    public List<Student> queryStudentByTeacher(Map<String, Object> paramMap);
}