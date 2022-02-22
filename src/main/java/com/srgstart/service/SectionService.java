package com.srgstart.service;

import com.srgstart.entity.Section;
import com.srgstart.mapper.SectionMapper;
import com.srgstart.util.BeanMapUtils;
import com.srgstart.util.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SectionService {

    @Autowired
    private SectionMapper sectionMapper;

    public int create(Section pi) {
        return sectionMapper.create(pi);
    }

    public int delete(Integer id) {
        return sectionMapper.delete(MapParameter.getInstance().addId(id).getMap());
    }

    public int delete(String ids) {
        int flag = 0;
        for (String str : ids.split(",")) {
            flag = sectionMapper.delete(MapParameter.getInstance().addId(Integer.parseInt(str)).getMap());
        }
        return flag;
    }

    public int update(Section section) {
        Map<String, Object> map = MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(section)).addId(section.getId()).getMap();
        return sectionMapper.update(map);
    }

    public List<Section> query(Section section) {
        return sectionMapper.query(BeanMapUtils.beanToMap(section));
    }

    public Section detail(Integer id) {
        return sectionMapper.detail(MapParameter.getInstance().addId(id).getMap());
    }

    public int count(Section section) {
        return sectionMapper.count(BeanMapUtils.beanToMap(section));
    }

    public List<Section> queryByStudent(Integer studentId){
        Map<String, Object> map = MapParameter.getInstance().add("studentId", studentId).getMap();
        return sectionMapper.queryByStudent(map);
    }

    public List<Section> queryByTeacher(Integer teacherId){
        Map<String, Object> map = MapParameter.getInstance().add("teacherId", teacherId).getMap();
        return sectionMapper.queryByTeacher(map);
    }

}
