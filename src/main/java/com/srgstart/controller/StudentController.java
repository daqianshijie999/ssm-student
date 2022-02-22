package com.srgstart.controller;

import com.srgstart.entity.Clazz;
import com.srgstart.entity.Student;
import com.srgstart.entity.Subject;
import com.srgstart.entity.Teacher;
import com.srgstart.result.Result;
import com.srgstart.service.ClazzService;
import com.srgstart.service.StudentService;
import com.srgstart.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @Author srgstart
 * @Create 2021/5/25 10:10
 * @Description 学生-控制器
 */
@Controller
@RequestMapping("/student")
public class StudentController {


    private static final String LIST = "student/list";
    private static final String ADD = "student/add";
    private static final String UPDATE = "student/update";

    @Autowired
    private StudentService studentService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private ClazzService clazzService;

    /**
     * 跳转到学生添加页面，并携带所有专业信息
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
     * 添加学生信息
     * @param student
     * @return
     */
    @PostMapping("/create")
    @ResponseBody
    public Map<String,Object> create(@RequestBody Student student){
        student.setStatus(Student.StatusType.type_1);
        int result = studentService.create(student);
        if(result<=0){
            return Result.getInstance().error().getMap();
        }
        return Result.getInstance().success().getMap();
    }

    /**
     * 根据Id删除指定学生
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    @ResponseBody
    public Map<String,Object> delete(@PathVariable("id") Integer id){
        int result = studentService.delete(id);
        if(result<=0){
            return Result.getInstance().error().getMap();
        }
        return Result.getInstance().success().getMap();
    }

    /**
     * 批量删除多个学生
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(String ids){
        int result = studentService.delete(ids);
        if(result<=0){
            return Result.getInstance().error().getMap();
        }
        return Result.getInstance().success().getMap();
    }

    /**
     * 更新学生信息
     * @param student
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public Map<String,Object> update(@RequestBody Student student){
        int result = studentService.update(student);
        if(result<=0){
            return Result.getInstance().error().getMap();
        }
        return Result.getInstance().success().getMap();
    }

    /**
     * 根据Id获取指定学生信息和专业，并跳转到修改修改页面
     * @param id
     * @param modelMap
     * @return
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap modelMap){
        Student student = studentService.detail(id);
        List<Subject> subjects = subjectService.query(null);
        modelMap.addAttribute("student",student);
        modelMap.addAttribute("subjects",subjects);
        return UPDATE;
    }

    /**
     * 搜索 + 分页 + 查询全部信息
     * @param student
     * @return
     */
    @PostMapping("/query")
    @ResponseBody
    public Map<String,Object> query(@RequestBody Student student){
        List<Student> list = studentService.query(student);
        List<Subject> subjects = subjectService.query(null);
        List<Clazz> clazzes = clazzService.query(null);
        //设置关联关系
        list.forEach(entity->{
            subjects.forEach(subject -> {
                if(subject.getId() == entity.getSubjectId()){
                    entity.setSubject(subject);
                }
            });
            clazzes.forEach(clazz -> {
                if(clazz.getId() == entity.getClazzId()){
                    entity.setClazz(clazz);
                }
            });
        });
        Integer count = studentService.count(student);
        return Result.getInstance().success().page(list,count).getMap();
    }

    /**
     * 跳转到学生列表页面
     * @return
     */
    @GetMapping("/list")
    public String list(){
        return LIST;
    }


    /**
     * 获取登录的学生信息，并跳转到个人信息页面
     * @param session
     * @param modelMap
     * @return
     */
    @GetMapping("/info")
    public String info(HttpSession session,ModelMap modelMap){
        //获取Student
        Student param = (Student) session.getAttribute("user");
        Student student = studentService.detail(param.getId());
        modelMap.put("student",student);
        return "student/info";
    }

    /**
     * 查询所有班级，专业信息，并跳转到teacher_student 页面
     * @param session
     * @param modelMap
     * @return
     */
    @GetMapping("/teacher_student")
    public String teacher_student(HttpSession session,ModelMap modelMap){
        Teacher teacher = (Teacher) session.getAttribute("user");
        List<Clazz> clazzes = clazzService.query(null);
        List<Subject> subjects = subjectService.query(null);
        modelMap.addAttribute("clazzes",clazzes);
        modelMap.addAttribute("subjects",subjects);
        modelMap.addAttribute("teacherId",teacher.getId());
        return "student/teacher_student";
    }

    /**
     * 查询当前登录老师的所有学生
     * @param teacherId
     * @param clazzId
     * @param subjectId
     * @return
     */
    @PostMapping("/teacher_student")
    @ResponseBody
    public Map<String,Object> teacher_student(Integer teacherId,Integer clazzId,Integer subjectId){
        List<Student> students = studentService.queryStudentByTeacher(teacherId, clazzId, subjectId);
        List<Subject> subjects = subjectService.query(null);
        List<Clazz> clazzes = clazzService.query(null);
        //设置关联关系
        students.forEach(entity->{
            subjects.forEach(subject -> {
                if(subject.getId() == entity.getSubjectId()){
                    entity.setSubject(subject);
                }
            });
            clazzes.forEach(clazz -> {
                if(clazz.getId() == entity.getClazzId()){
                    entity.setClazz(clazz);
                }
            });
        });
        return Result.getInstance().success().add("data",students).getMap();
    }



}
