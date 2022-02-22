package com.srgstart.service;


import com.srgstart.entity.Student;
import com.srgstart.mapper.StudentMapper;
import com.srgstart.util.BeanMapUtils;
import com.srgstart.util.MD5Utils;
import com.srgstart.util.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    public int create(Student pi) {
        pi.setStuPwd(MD5Utils.getMD5(pi.getStuPwd()));
        return studentMapper.create(pi);
    }

    public int delete(Integer id) {
        return studentMapper.delete(MapParameter.getInstance().addId(id).getMap());
    }
    public int delete(String ids) {
        int flag = 0;
        for (String str : ids.split(",")) {
            flag = studentMapper.delete(MapParameter.getInstance().addId(Integer.parseInt(str)).getMap());
        }
        return flag;
    }

    public int update(Student student) {
        Map<String, Object> map = MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(student)).addId(student.getId()).getMap();
        return studentMapper.update(map);
    }

    public List<Student> query(Student student) {
        return studentMapper.query(BeanMapUtils.beanToMap(student));
    }

    public Student detail(Integer id) {
        return studentMapper.detail(MapParameter.getInstance().addId(id).getMap());
    }

    public int count(Student student) {
        return studentMapper.count(BeanMapUtils.beanToMap(student));
    }


    public Student login(String userName, String password){
        Map<String, Object> map = MapParameter.getInstance()
                .add("stuNo", userName)
                .add("stuPwd", password)
                .getMap();
        return studentMapper.detail(map);
    }

    public List<HashMap> querySelectStudent(Integer courseId, Integer sectionId){
        Map<String, Object> map = MapParameter.getInstance()
                .add("courseId", courseId)
                .add("sectionId", sectionId)
                .getMap();
        return studentMapper.querySelectStudent(map);
    }

    public List<Student> queryStudentByTeacher(Integer teacherId,Integer clazzId,Integer subjectId){
        Map<String, Object> map = MapParameter.getInstance()
                .add("teacherId", teacherId)
                .add("clazzId", clazzId)
                .add("subjectId", subjectId)
                .getMap();
        return studentMapper.queryStudentByTeacher(map);
    }

}
