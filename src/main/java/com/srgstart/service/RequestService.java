package com.srgstart.service;

import com.github.pagehelper.PageHelper;
import com.srgstart.entity.Request;
import com.srgstart.mapper.RequestMapper;
import com.srgstart.util.BeanMapUtils;
import com.srgstart.util.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestMapper RequestMapper;

    public int create(Request pi) {
        return RequestMapper.create(pi);
    }

    public int delete(Integer id) {
        return RequestMapper.delete(MapParameter.getInstance().addId(id).getMap());
    }

    public int delete(String ids) {
        int flag = 0;
        for (String str : ids.split(",")) {
            flag = RequestMapper.delete(MapParameter.getInstance().addId(Integer.parseInt(str)).getMap());
        }
        return flag;
    }

    public int update(Request request) {
        return RequestMapper.update(MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(request)).addId(request.getId()).getMap());
    }

    public List<Request> query(Request request) {
        if(request != null && request.getPage() != null){
            PageHelper.startPage(request.getPage(),request.getLimit());
        }
        return RequestMapper.query(BeanMapUtils.beanToMap(request));
    }

    public Request detail(Integer id) {
        return RequestMapper.detail(MapParameter.getInstance().addId(id).getMap());
    }

    public int count(Request request) {
        return RequestMapper.count(BeanMapUtils.beanToMap(request));
    }

}
