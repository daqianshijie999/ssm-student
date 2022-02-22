package com.srgstart.controller;

import com.srgstart.entity.Teacher;
import com.srgstart.result.Result;
import com.srgstart.service.ClazzService;
import com.srgstart.service.SubjectService;
import com.srgstart.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author srgstart
 * @Create 2021/5/25 10:10
 * @Description 教师-控制器
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {


    private static final String LIST = "teacher/list";
    private static final String ADD = "teacher/add";
    private static final String UPDATE = "teacher/update";

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private ClazzService clazzService;

    /**
     * 跳转到添加教师页面
     * @param modelMap
     * @return
     */
    @GetMapping("/add")
    public String create(ModelMap modelMap){
        return ADD;
    }

    /**
     * 添加教师信息
     * @param teacher
     * @return
     */
    @PostMapping("/create")
    @ResponseBody
    public Map<String,Object> create(@RequestBody Teacher teacher){
        int result = teacherService.create(teacher);
        if(result<=0){
            return Result.getInstance().error().getMap();
        }
        return Result.getInstance().success().getMap();
    }

    /**
     * 根据Id删除指定教师
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    @ResponseBody
    public Map<String,Object> delete(@PathVariable("id") Integer id){
        int result = teacherService.delete(id);
        if(result<=0){
            return Result.getInstance().error().getMap();
        }
        return Result.getInstance().success().getMap();
    }

    /**
     * 批量删除教师信息
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(String ids){
        int result = teacherService.delete(ids);
        if(result<=0){
            return Result.getInstance().error().getMap();
        }
        return Result.getInstance().success().getMap();
    }

    /**
     * 更新教师信息
     * @param teacher
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public Map<String,Object> update(@RequestBody Teacher teacher){
        int result = teacherService.update(teacher);
        if(result<=0){
            return Result.getInstance().error().getMap();
        }
        return Result.getInstance().success().getMap();
    }

    /**
     * 根据Id查询指定教师信息，并跳转到修改页面
     * @param id
     * @param modelMap
     * @return
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap modelMap){
        Teacher teacher = teacherService.detail(id);
        modelMap.addAttribute("teacher",teacher);
        return UPDATE;
    }

    /**
     * 搜索 + 分页 + 查询全部信息
     * @param teacher
     * @return
     */
    @PostMapping("/query")
    @ResponseBody
    public Map<String,Object> query(@RequestBody Teacher teacher){
        List<Teacher> list = teacherService.query(teacher);
        Integer count = teacherService.count(teacher);
        return Result.getInstance().success().page(list,count).getMap();
    }

    /**
     * 跳转到教师列表页面
     * @return
     */
    @GetMapping("/list")
    public String list(){
        return LIST;
    }



}
