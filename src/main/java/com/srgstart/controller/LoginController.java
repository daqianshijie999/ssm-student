package com.srgstart.controller;

import com.github.pagehelper.util.StringUtil;
import com.srgstart.entity.Student;
import com.srgstart.entity.Teacher;
import com.srgstart.entity.User;
import com.srgstart.service.StudentService;
import com.srgstart.service.TeacherService;
import com.srgstart.service.UserService;
import com.srgstart.util.MD5Utils;
import com.srgstart.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author srgstart
 * @Create 2021/5/24 18:22
 * @Description 登录控制器
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    /**
     * 登录页面跳转
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 进入主页面
     * @return
     */
    @GetMapping("/index")
    public String index() {
        return "index";
    }


    /**
     * 用户登录
     * @param username
     * @param password
     * @param type
     * @param captcha
     * @param session
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public Map<String,Object> login(String username, String password, String type, String captcha, HttpSession session) {

        // 1、参数校验
        if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password) || StringUtil.isEmpty(type)) {
            return Result.getInstance().error("用户名或者密码不能为空！").getMap();
        }

        // 2、校验验证码
        if(StringUtils.isEmpty(captcha)){
            return Result.getInstance().error("验证码不能为空").getMap();
        }
        String _captcha = (String) session.getAttribute("captcha");
        if (!captcha.equals(_captcha)) {
            return Result.getInstance().error("验证码错误！").getMap();
        }

        // 3、判断给用户是以哪种身份登陆的
        if ("1".equals(type)) { // 管理员验证登录
            User user = userService.login(username, MD5Utils.getMD5(password));
            if (user != null) {
                session.setAttribute("user",user);
                session.setAttribute("type",1);
                return Result.getInstance().success().add("data",user).getMap();
            } else {
                return Result.getInstance().error("用户或者密码错误！").getMap();
            }
        }

        if ("2".equals(type)) { // 老师验证登录
            Teacher teacher = teacherService.login(username, MD5Utils.getMD5(password));
            if (teacher != null) {
                session.setAttribute("user",teacher);
                session.setAttribute("type",2);
                return Result.getInstance().success().add("data",teacher).getMap();
            } else {
                return Result.getInstance().error("用户或者密码错误！").getMap();
            }
        }

        if ("3".equals(type)) { // 学生验证登录
            Student student = studentService.login(username, MD5Utils.getMD5(password));
            if (student != null) {
                session.setAttribute("user",student);
                session.setAttribute("type",3);
                return Result.getInstance().success().add("data",student).getMap();
            } else {
                return Result.getInstance().error("用户或者密码错误！").getMap();
            }
        }
        return Result.getInstance().error("用户或者密码错误！").getMap();
    }

    /**
     * 退出账户
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:login";
    }
}
