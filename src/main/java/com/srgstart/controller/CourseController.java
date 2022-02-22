package com.srgstart.controller;
;
import com.srgstart.entity.Course;
import com.srgstart.result.Result;
import com.srgstart.service.ClazzService;
import com.srgstart.service.CourseService;
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
 * @Description 课程-控制器
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    private static final String LIST = "course/list";
    private static final String ADD = "course/add";
    private static final String UPDATE = "course/update";

    @Autowired
    private CourseService courseService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private ClazzService clazzService;

    /**
     * 跳转到添加课程页面
     * @param modelMap
     * @return
     */
    @GetMapping("/add")
    public String create(ModelMap modelMap){
        return ADD;
    }

    /**
     * 添加课程信息
     * @param course
     * @return
     */
    @PostMapping("/create")
    @ResponseBody
    public Map<String,Object> create(@RequestBody Course course){
        int result = courseService.create(course);
        if(result<=0){
            return Result.getInstance().error().getMap();
        }
        return Result.getInstance().success().getMap();
    }

    /**
     * 根据Id删除指定课程信息
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    @ResponseBody
    public Map<String,Object> delete(@PathVariable("id") Integer id){
        int result = courseService.delete(id);
        if(result<=0){
            return Result.getInstance().error().getMap();
        }
        return Result.getInstance().success().getMap();
    }

    /**
     * 批量删除多个课程信息
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(String ids){
        int result = courseService.delete(ids);
        if(result<=0){
            return Result.getInstance().error().getMap();
        }
        return Result.getInstance().success().getMap();
    }

    /**
     * 修改课程信息
     * @param course
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public Map<String,Object> update(@RequestBody Course course){
        int result = courseService.update(course);
        if(result<=0){
            return Result.getInstance().error().getMap();
        }
        return Result.getInstance().success().getMap();
    }

    /**
     * 根据Id获取指定课程信息，并跳转到修改课程页面
     * @param id
     * @param modelMap
     * @return
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap modelMap){
        Course course = courseService.detail(id);
        modelMap.addAttribute("course",course);
        return UPDATE;
    }

    /**
     * 搜索 + 分页 + 查询全部信息
     * @param course
     * @return
     */
    @PostMapping("/query")
    @ResponseBody
    public Map<String,Object> query(@RequestBody Course course){
        List<Course> list = courseService.query(course);
        Integer count = courseService.count(course);
        return Result.getInstance().success().page(list,count).getMap();
    }

    /**
     * 跳转到课程列表页面
     * @return
     */
    @GetMapping("/list")
    public String list(){
        return LIST;
    }

}
