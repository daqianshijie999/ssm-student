package com.srgstart.result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author srgstart
 * @Create 2021/5/24 18:22
 * @Description TODO
 */
public class Result {

    //目标对象
    private Map<String,Object> paramMap = new HashMap<>();

    //私有构造
    private Result(){

    }

    public static Result getInstance(){
        return new Result();
    }

    public Result add(String key, Object value){
        paramMap.put(key,value);
        return this;
    }

    public Result success(){
        paramMap.put("code",Code.SUCCESS.getCode());
        paramMap.put("msg",Code.SUCCESS.getMsg());
        return this;
    }
    public Result error(){
        paramMap.put("code",Code.ERROR.getCode());
        paramMap.put("msg",Code.ERROR.getMsg());
        return this;
    }

    public Result error(String msg){
        paramMap.put("code",Code.ERROR.getCode());
        paramMap.put("msg",msg);
        return this;
    }

    public Result notEmpty(){
        paramMap.put("code",Code.NOT_EMPTY.getCode());
        paramMap.put("msg",Code.NOT_EMPTY.getMsg());
        return this;
    }
    public Result nodata(){
        paramMap.put("code",Code.NODATA.getCode());
        paramMap.put("msg",Code.NODATA.getMsg());
        return this;
    }

    public Result page(List<?> list,Integer count){
        paramMap.put("data",list);
        paramMap.put("count",count);
        return this;
    }

    public Result put(String key, Object value){
        this.add(key,value);
        return this;
    }

    public Result addId(Object value){
        paramMap.put("id",value);
        return this;
    }

    public Result add(Map<String,Object> map){
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            paramMap.put(entry.getKey(),entry.getValue());
        }
        return this;
    }

    public Result put(Map<String,Object> map){
        return this.add(map);
    }

    public Map<String,Object> getMap(){
        return paramMap;
    }




}
