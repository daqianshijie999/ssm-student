package com.srgstart.controller;

import com.srgstart.entity.Subject;
import com.srgstart.result.Result;
import com.srgstart.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * @Author srgstart
 * @Create 2021/5/24 18:22
 * @Description 专业-控制器
 */
@Controller
@RequestMapping("/subject")
public class SubjectController {

    private static final String LIST = "subject/list";
    private static final String ADD = "subject/add";
    private static final String UPDATE = "subject/update";

    @Autowired
    private SubjectService subjectService;

    /**
     * 跳转到添加页面
     * @return
     */
    @GetMapping("/add")
    public String create(){
        return ADD;
    }

    /**
     * 添加专业信息
     * @param subject
     * @return
     */
    @PostMapping("/create")
    @ResponseBody
    public Map<String,Object> create(@RequestBody Subject subject){
        int result = subjectService.create(subject);
        if(result<=0){
            return Result.getInstance().error().getMap();
        }
        return Result.getInstance().success().getMap();
    }

    /**
     * 根据删除id删除指定专业信息
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    @ResponseBody
    public Map<String,Object> delete(@PathVariable("id") Integer id){
        int result = subjectService.delete(id);
        if(result<=0){
            return Result.getInstance().error().getMap();
        }
        return Result.getInstance().success().getMap();
    }

    /**
     * 批量删除多个专业信息
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(String ids){
        int result = subjectService.delete(ids);
        if(result<=0){
            return Result.getInstance().error().getMap();
        }
        return Result.getInstance().success().getMap();
    }

    /**
     * 更新指定专业信息
     * @param subject
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public Map<String,Object> update(@RequestBody Subject subject){
        int result = subjectService.update(subject);
        if(result<=0){
            return Result.getInstance().error().getMap();
        }
        return Result.getInstance().success().getMap();
    }

    /**
     * 根据Id获取该专业的详细信息
     * @param id
     * @param modelMap
     * @return
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap modelMap){
        Subject subject = subjectService.detail(id);
        modelMap.addAttribute("subject",subject);
        return UPDATE;
    }

    /**
     * 分页查询所有的专业信息
     * @param subject
     * @return
     */
    @PostMapping("/query")
    @ResponseBody
    public Map<String,Object> query(@RequestBody Subject subject){
        List<Subject> list = subjectService.query(subject);
        Integer count = subjectService.count(subject);
        return Result.getInstance().success().page(list,count).getMap();
    }

    /**
     * 跳转到专业列表页面
     * @return
     */
    @GetMapping("/list")
    public String list(){
        return LIST;
    }


}
