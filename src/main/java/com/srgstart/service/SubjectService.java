package com.srgstart.service;

import com.github.pagehelper.PageHelper;
import com.srgstart.entity.Subject;
import com.srgstart.mapper.SubjectMapper;
import com.srgstart.util.BeanMapUtils;
import com.srgstart.util.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SubjectService {

    @Autowired
    private SubjectMapper subjectMapper;

    public int create(Subject pi) {
        return subjectMapper.create(pi);
    }

    public int delete(Integer id) {
        return subjectMapper.delete(MapParameter.getInstance().addId(id).getMap());
    }
    public int delete(String ids) {
        int flag = 0;
        for (String str : ids.split(",")) {
            flag = subjectMapper.delete(MapParameter.getInstance().addId(Integer.parseInt(str)).getMap());
        }
        return flag;
    }

    public int update(Subject subject) {
        Map<String, Object> map = MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(subject)).addId(subject.getId()).getMap();
        return subjectMapper.update(map);
    }

    public List<Subject> query(Subject subject) {
        //当查询条件有分页信息时，按照分页查询
        if(subject != null && subject.getPage() != null){
            PageHelper.startPage(subject.getPage(),subject.getLimit());
        }
        return subjectMapper.query(BeanMapUtils.beanToMap(subject));
    }

    public Subject detail(Integer id) {
        return subjectMapper.detail(MapParameter.getInstance().addId(id).getMap());
    }

    public int count(Subject subject) {
        return subjectMapper.count(BeanMapUtils.beanToMap(subject));
    }


}
