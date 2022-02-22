package com.srgstart.service;

import com.github.pagehelper.PageHelper;

import com.srgstart.entity.Clazz;
import com.srgstart.mapper.ClazzMapper;
import com.srgstart.util.BeanMapUtils;
import com.srgstart.util.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    public int create(Clazz pi) {
        return clazzMapper.create(pi);
    }

    public int delete(Integer id) {
        return clazzMapper.delete(MapParameter.getInstance().addId(id).getMap());
    }

    public int delete(String ids) {
        int flag = 0;
        for (String str : ids.split(",")) {
            flag = clazzMapper.delete(MapParameter.getInstance().addId(Integer.parseInt(str)).getMap());
        }
        return flag;
    }

    public int update(Clazz clazz) {
        return clazzMapper.update(MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(clazz)).addId(clazz.getId()).getMap());
    }

    public List<Clazz> query(Clazz clazz) {
        if(clazz != null && clazz.getPage() != null){
            PageHelper.startPage(clazz.getPage(),clazz.getLimit());
        }
        return clazzMapper.query(BeanMapUtils.beanToMap(clazz));
    }

    public Clazz detail(Integer id) {
        return clazzMapper.detail(MapParameter.getInstance().addId(id).getMap());
    }

    public int count(Clazz clazz) {
        return clazzMapper.count(BeanMapUtils.beanToMap(clazz));
    }

}
