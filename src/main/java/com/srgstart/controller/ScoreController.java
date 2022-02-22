package com.srgstart.controller;

import com.srgstart.entity.Score;
import com.srgstart.entity.Student;
import com.srgstart.result.Result;
import com.srgstart.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @PostMapping("/create")
    @ResponseBody
    public Map<String,Object> create(String sectionIds, String courseIds, HttpSession session){
        Student student = (Student) session.getAttribute("user");
        int result = scoreService.create(sectionIds,courseIds,student.getId());
        if(result<=0){
            return Result.getInstance().error().getMap();
        }
        return Result.getInstance().success().getMap();
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public Map<String,Object> delete(@PathVariable("id") Integer id){
        int result = scoreService.delete(id);
        if(result<=0){
            return Result.getInstance().error().getMap();
        }
        return Result.getInstance().success().getMap();
    }

    /**
     * 根据条件修改学生的分数
     * @param score
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public Map<String,Object> update(Score score){
        int result = scoreService.update(score);
        if(result<=0){
            return Result.getInstance().error().getMap();
        }
        return Result.getInstance().success().getMap();
    }

    /**
     * 根据Id获取指定学生的分数
     * @param id
     * @return
     */
    @PostMapping("/detail/{id}")
    @ResponseBody
    public Map<String,Object> detail(@PathVariable("id") Integer id){
        Score score = scoreService.detail(id);
        if(score == null){
            return Result.getInstance().nodata().getMap();
        }
        return Result.getInstance().success().put("data",score).getMap();
    }

    /**
     * 搜索 + 查询全部信息
     * @param score
     * @return
     */
    @PostMapping("/query")
    @ResponseBody
    public Map<String,Object> query(Score score){
        List<Score> list = scoreService.query(score);
        return Result.getInstance().success().put("data",list).getMap();
    }

    /**
     * 跳转到学生成绩页面
     * @return
     */
    @GetMapping("/student_score")
    public String student_score(){
        return "score/student_score";
    }

    /**
     * 获取当前登录的学生成绩列表
     * @param score
     * @param session
     * @return
     */
    @PostMapping("/student_score")
    @ResponseBody
    public Map<String,Object> student_score(Score score,HttpSession session){
        Student student = (Student) session.getAttribute("user");
        List<HashMap> mapList = scoreService.queryScoreByStudent(student.getId());
        return Result.getInstance().success().put("data",mapList).getMap();
    }


}
