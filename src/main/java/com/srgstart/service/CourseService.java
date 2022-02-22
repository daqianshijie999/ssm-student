package com.srgstart.service;

import com.github.pagehelper.PageHelper;
import com.srgstart.entity.Course;
import com.srgstart.mapper.CourseMapper;
import com.srgstart.util.BeanMapUtils;
import com.srgstart.util.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CourseService {

    @Autowired
    private CourseMapper courseMapper;

    public int create(Course pi) {
        return courseMapper.create(pi);
    }

    public int delete(Integer id) {
        return courseMapper.delete(MapParameter.getInstance().addId(id).getMap());
    }

    public int delete(String ids) {
        int flag = 0;
        for (String str : ids.split(",")) {
            flag = courseMapper.delete(MapParameter.getInstance().addId(Integer.parseInt(str)).getMap());
        }
        return flag;
    }

    public int update(Course course) {
        Map<String, Object> map = MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(course)).addId(course.getId()).getMap();
        return courseMapper.update(map);
    }

    public List<Course> query(Course course) {
        if(course != null && course.getPage() != null){
            PageHelper.startPage(course.getPage(),course.getLimit());
        }
        return courseMapper.query(BeanMapUtils.beanToMap(course));
    }

    public Course detail(Integer id) {
        return courseMapper.detail(MapParameter.getInstance().addId(id).getMap());
    }

    public int count(Course course) {
        return courseMapper.count(BeanMapUtils.beanToMap(course));
    }

}
