package com.srgstart.controller;

import com.srgstart.entity.User;
import com.srgstart.service.ClazzService;
import com.srgstart.service.SubjectService;
import com.srgstart.service.UserService;
import com.srgstart.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author srgstart
 * @Create 2021/5/25 10:10
 * @Description 管理员-控制器
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final String LIST = "user/list";
    private static final String ADD = "user/add";
    private static final String UPDATE = "user/update";

    @Autowired
    private UserService userService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private ClazzService clazzService;

    /**
     * 跳转到添加用户页面
     * @param modelMap
     * @return
     */
    @GetMapping("/add")
    public String create(ModelMap modelMap){
        return ADD;
    }

    /**
     * 添加用户信息
     * @param user
     * @return
     */
    @PostMapping("/create")
    @ResponseBody
    public Map<String,Object> create(@RequestBody User user){
        int result = userService.create(user);
        if(result<=0){
            return Result.getInstance().error().getMap();
        }
        return Result.getInstance().success().getMap();
    }

    /**
     * 根据Id删除指定的用户信息
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    @ResponseBody
    public Map<String,Object> delete(@PathVariable("id") Integer id){
        int result = userService.delete(id);
        if(result<=0){
            return Result.getInstance().error().getMap();
        }
        return Result.getInstance().success().getMap();
    }

    /**
     * 批量删除多个用户信息
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(String ids){
        int result = userService.delete(ids);
        if(result<=0){
            return Result.getInstance().error().getMap();
        }
        return Result.getInstance().success().getMap();
    }

    /**
     * 根据条件修改指定用户信息
     * @param user
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public Map<String,Object> update(@RequestBody User user){
        int result = userService.update(user);
        if(result<=0){
            return Result.getInstance().error().getMap();
        }
        return Result.getInstance().success().getMap();
    }

    /**
     * 根据Id获取指定用户的信息，并跳转到修改页面
     * @param id
     * @param modelMap
     * @return
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap modelMap){
        User user = userService.detail(id);
        modelMap.addAttribute("user",user);
        return UPDATE;
    }

    /**
     * 搜索 + 分页 + 查询全部信息
     * @param user
     * @return
     */
    @PostMapping("/query")
    @ResponseBody
    public Map<String,Object> query(@RequestBody User user){
        List<User> list = userService.query(user);
        Integer count = userService.count(user);
        return Result.getInstance().success().page(list,count).getMap();
    }

    /**
     * 跳转到用户列表页面
     * @return
     */
    @GetMapping("/list")
    public String list(){
        return LIST;
    }

}
