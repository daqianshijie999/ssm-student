package com.srgstart.controller;

import com.srgstart.entity.Clazz;
import com.srgstart.entity.Subject;
import com.srgstart.result.Result;
import com.srgstart.service.ClazzService;
import com.srgstart.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author srgstart
 * @Create 2021/5/25 10:10
 * @Description 班级-控制器
 */
@Controller
@RequestMapping("/clazz")
public class ClazzController {

    private static final String LIST = "clazz/list";
    private static final String ADD = "clazz/add";
    private static final String UPDATE = "clazz/update";

    @Autowired
    private ClazzService clazzService;
    @Autowired
    private SubjectService subjectService;

    /**
     * 携带所有专业信息到 添加页面
     * @param modelMap
     * @return
     */
    @GetMapping("/add")
    public String create(ModelMap modelMap){
        List<Subject> subjects = subjectService.query(null);
        modelMap.addAttribute("subjects",subjects);
        return ADD;
    }

    /**
     * 添加班级信息
     * @param clazz
     * @return
     */
    @PostMapping("/create")
    @ResponseBody
    public Map<String,Object> create(@RequestBody Clazz clazz){
        int result = clazzService.create(clazz);
        if(result<=0){
            return Result.getInstance().error().getMap();
        }
        return Result.getInstance().success().getMap();
    }

    /**
     * 根据id删除指定班级信息
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    @ResponseBody
    public Map<String,Object> delete(@PathVariable("id") Integer id){
        int result = clazzService.delete(id);
        if(result<=0){
            return Result.getInstance().error().getMap();
        }
        return Result.getInstance().success().getMap();
    }

    /**
     * 批量删除多个班级信息
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(String ids){
        int result = clazzService.delete(ids);
        if(result<=0){
            return Result.getInstance().error().getMap();
        }
        return Result.getInstance().success().getMap();
    }

    /**
     * 修改班级信息
     * @param clazz
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public Map<String,Object> update(@RequestBody Clazz clazz){
        int result = clazzService.update(clazz);
        if(result<=0){
            return Result.getInstance().error().getMap();
        }
        return Result.getInstance().success().getMap();
    }

    /**
     * 根据Id获取指定班级的详细信息,同时写到所有专业信息到修改页面
     * @param id
     * @param modelMap
     * @return
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap modelMap){
        Clazz clazz = clazzService.detail(id);
        List<Subject> subjects = subjectService.query(null);
        modelMap.addAttribute("clazz",clazz);
        modelMap.addAttribute("subjects",subjects);
        return UPDATE;
    }

    /**
     * 搜索 + 分页 + 查询全部信息
     * @param clazz 搜索的条件
     * @return
     */
    @PostMapping("/query")
    @ResponseBody
    public Map<String,Object> query(@RequestBody Clazz clazz){
        List<Clazz> list = clazzService.query(clazz);
        List<Subject> subjects = subjectService.query(null);
        //设置subject
        list.forEach(entity->{
            subjects.forEach(subject -> {
                // 查询出该班级对应的专业
                if(entity.getSubjectId() == subject.getId()){
                    entity.setSubject(subject);
                }
            });
        });
        Integer count = clazzService.count(clazz);
        return Result.getInstance().success().page(list,count).getMap();
    }

    /**
     * 跳转到班级列表页面
     * @return
     */
    @GetMapping("/list")
    public String list(){
        return LIST;
    }

}
