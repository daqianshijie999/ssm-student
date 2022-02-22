package com.srgstart.service;

import com.srgstart.entity.Job;
import com.srgstart.mapper.JobMapper;
import com.srgstart.util.BeanMapUtils;
import com.srgstart.util.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JobService {

    @Autowired
    private JobMapper jobMapper;

    public int create(Job pi) {
        return jobMapper.create(pi);
    }

    public int delete(Integer id) {
        return jobMapper.delete(MapParameter.getInstance().addId(id).getMap());
    }

    public int update(Job job) {
        Map<String, Object> map = MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(job)).addId(job.getId()).getMap();
        return jobMapper.update(map);
    }

    public List<Job> query(Job job) {
        return jobMapper.query(BeanMapUtils.beanToMap(job));
    }

    public Job detail(Integer id) {
        return jobMapper.detail(MapParameter.getInstance().addId(id).getMap());
    }

    public Job detailByStudent(Integer stuId) {
        return jobMapper.detail(MapParameter.getInstance().add("stuId",stuId).getMap());
    }

    public int count(Job job) {
        return jobMapper.count(BeanMapUtils.beanToMap(job));
    }

}
