package com.srgstart.service;

import com.github.pagehelper.PageHelper;
import com.srgstart.entity.Teacher;
import com.srgstart.mapper.TeacherMapper;
import com.srgstart.util.BeanMapUtils;
import com.srgstart.util.MD5Utils;
import com.srgstart.util.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    public int create(Teacher pi) {
        pi.setTeacherPwd(MD5Utils.getMD5(pi.getTeacherPwd()));
        return teacherMapper.create(pi);
    }

    public int delete(Integer id) {
        return teacherMapper.delete(MapParameter.getInstance().addId(id).getMap());
    }

    public int delete(String ids) {
        int flag = 0;
        for (String str : ids.split(",")) {
            flag = teacherMapper.delete(MapParameter.getInstance().addId(Integer.parseInt(str)).getMap());
        }
        return flag;
    }

    public int update(Teacher teacher) {
        Map<String, Object> map = MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(teacher)).addId(teacher.getId()).getMap();
        return teacherMapper.update(map);
    }

    public List<Teacher> query(Teacher teacher) {
        if(teacher != null && teacher.getPage() != null){
            PageHelper.startPage(teacher.getPage(),teacher.getLimit());
        }
        return teacherMapper.query(BeanMapUtils.beanToMap(teacher));
    }

    public Teacher detail(Integer id) {
        return teacherMapper.detail(MapParameter.getInstance().addId(id).getMap());
    }

    public int count(Teacher teacher) {
        return teacherMapper.count(BeanMapUtils.beanToMap(teacher));
    }

    public Teacher login(String userName, String password){
        Map<String, Object> map = MapParameter.getInstance()
                .add("teacherName", userName)
                .add("teacherPwd", password)
                .getMap();
        return teacherMapper.detail(map);
    }


}
