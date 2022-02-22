package com.srgstart.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.srgstart.entity.*;
import com.srgstart.result.Result;
import com.srgstart.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author srgstart
 * @Create 2021/5/25 10:10
 * @Description 开课-控制器
 */
@Controller
@RequestMapping("/section")
public class SectionController {

    private static final String LIST = "section/list";
    private static final String ADD = "section/add";
    private static final String UPDATE = "section/update";

    @Autowired
    private SectionService sectionService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private ClazzService clazzService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ScoreService scoreService;

    /**
     *  跳转到添加开课页面，同时携带所有的老师和课程信息。
     * @param modelMap
     * @param clazzId
     * @return
     */
    @GetMapping("/add")
    public String create(ModelMap modelMap,Integer clazzId){
        List<Teacher> teachers = teacherService.query(null);
        List<Course> courses = courseService.query(null);
        modelMap.put("teachers",teachers);
        modelMap.put("courses",courses);
        modelMap.put("clazzId",clazzId);
        return ADD;
    }

    /**
     * 添加开课信息
     * @param section
     * @return
     */
    @PostMapping("/create")
    @ResponseBody
    public Map<String,Object> create(@RequestBody Section section){
        int result = sectionService.create(section);
        if(result<=0){
            return Result.getInstance().error().getMap();
        }
        return Result.getInstance().success().getMap();
    }

    /**
     * 根据id删除指定开课信息
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    @ResponseBody
    public Map<String,Object> delete(@PathVariable("id") Integer id){
        int result = sectionService.delete(id);
        if(result<=0){
            return Result.getInstance().error().getMap();
        }
        return Result.getInstance().success().getMap();
    }

    /**
     * 批量删除多个开课信息
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(String ids){
        int result = sectionService.delete(ids);
        if(result<=0){
            return Result.getInstance().error().getMap();
        }
        return Result.getInstance().success().getMap();
    }

    /**
     * 修改指定的开课信息
     * @param section
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public Map<String,Object> update(@RequestBody Section section){
        int result = sectionService.update(section);
        if(result<=0){
            return Result.getInstance().error().getMap();
        }
        return Result.getInstance().success().getMap();
    }

    /**
     * 根据Id获取指定的开课信息，老师、课程，并跳转到修改页面
     * @param id
     * @param modelMap
     * @return
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap modelMap){
        Section section = sectionService.detail(id);
        modelMap.addAttribute("section",section);
        List<Teacher> teachers = teacherService.query(null);
        List<Course> courses = courseService.query(null);
        modelMap.put("teachers",teachers);
        modelMap.put("courses",courses);
        return UPDATE;
    }

    /**
     * 搜索 + 分页 + 查询全部信息
     * @param section
     * @return
     */
    @PostMapping("/query")
    @ResponseBody
    public Map<String,Object> query(@RequestBody Section section){
        List<Section> list = sectionService.query(section);
        List<Subject> subjects = subjectService.query(null);
        Integer count = sectionService.count(section);
        return Result.getInstance().success().page(list,count).getMap();
    }

    /**
     * 跳转到开课列表页面
     * @param modelMap
     * @return
     */
    @GetMapping("/list")
    public String list(ModelMap modelMap){
        return LIST;
    }

    /**
     * 为前端的ztree，设置父-子关系的map数据，用户渲染ztree
     * @return
     */
    @PostMapping("/tree")
    @ResponseBody
    public List<Map> tree(){
        // 查询所有的专业和班级
        List<Subject> subjects = subjectService.query(null);
        List<Clazz> clazzes = clazzService.query(null);
        List<Map> list = new ArrayList<>();
        // 遍历每个专业，为每个专业添加一个班级集合。此操作再map中完成
        subjects.forEach(subject -> {
            Map<String,Object> map = new HashMap<>();
            map.put("id",subject.getId());
            map.put("name",subject.getSubjectName());
            map.put("parentId",0);
            List<Map<String,Object>> childrenList = new ArrayList<>();
            clazzes.forEach(clazz -> {
                if(subject.getId() == clazz.getSubjectId()){
                    Map<String,Object> children = new HashMap<>();
                    children.put("id",clazz.getId());
                    children.put("name",clazz.getClazzName());
                    children.put("parentId",subject.getId());
                    childrenList.add(children);
                }
            });
            map.put("children",childrenList);
            list.add(map);
        });
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return list;
    }

    @GetMapping("/student_section")
    public String student_section(){
        return "section/student_section";
    }

    @PostMapping("/query_student_section")
    @ResponseBody
    public Map<String,Object> query_student_select(HttpSession session){
        //获取Student
        Student param = (Student) session.getAttribute("user");
        //按照学生ID查询班级设置课程信息
        List<Section> sections = sectionService.queryByStudent(param.getId());
        List<Teacher> teachers = teacherService.query(null);
        List<Course> courses = courseService.query(null);
        List<Clazz> clazzes = clazzService.query(null);
        //设置关联对象
        sections.forEach(section -> {
            teachers.forEach(teacher -> {
                if(section.getTeacherId() == teacher.getId().intValue()){
                    section.setTeacher(teacher);
                }
            });
            courses.forEach(course -> {
                if(section.getCourseId() == course.getId().intValue()){
                    section.setCourse(course);
                }
            });
            clazzes.forEach(clazz -> {
                if(section.getClazzId() == clazz.getId().intValue()){
                    section.setClazz(clazz);
                }
            });
        });
        return  Result.getInstance().success().add("data",sections).getMap();
    }


    @GetMapping("/teacher_section")
    public String teacher_section(){
        return "section/teacher_section";
    }

    @PostMapping("/query_teacher_section")
    @ResponseBody
    public Map<String,Object> query_teacher_section(HttpSession session){
        //获取Teacher
        Teacher param = (Teacher) session.getAttribute("user");
        //按照学生ID查询班级设置课程信息
        List<Section> sections = sectionService.queryByTeacher(param.getId());
        List<Course> courses = courseService.query(null);
        List<Clazz> clazzes = clazzService.query(null);
        //设置关联对象
        sections.forEach(section -> {
            courses.forEach(course -> {
                if(section.getCourseId() == course.getId().intValue()){
                    section.setCourse(course);
                }
            });
            clazzes.forEach(clazz -> {
                if(section.getClazzId() == clazz.getId().intValue()){
                    section.setClazz(clazz);
                }
            });
        });
        return  Result.getInstance().success().add("data",sections).getMap();
    }

    /**
     * 老师评分
     * @return
     */
    @GetMapping("/teacher_student_score")
    public String teacher_student_score(Integer courseId,Integer sectionId,ModelMap modelMap){
        List<HashMap> list = studentService.querySelectStudent(courseId, sectionId);
        modelMap.put("list",list);
        modelMap.put("sectionId",sectionId);
        modelMap.put("courseId",courseId);
        return "section/teacher_student_score";
    }

    @PostMapping("/teacher_student_score")
    @ResponseBody
    public Map<String,Object> teacher_student_score(Integer courseId,Integer sectionId,String stuIds,String scores){
        int flag = scoreService.update(courseId, sectionId, stuIds, scores);
        if(flag<=0){
            return Result.getInstance().error().getMap();
        }
        return Result.getInstance().success().getMap();
    }


}
