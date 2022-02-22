package com.srgstart.service;

import com.srgstart.entity.Score;
import com.srgstart.mapper.ScoreMapper;
import com.srgstart.util.BeanMapUtils;
import com.srgstart.util.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    public int create(Score pi) {
        return scoreMapper.create(pi);
    }

    public int create(String sectionIds,String courseIds,Integer studentId) {

        scoreMapper.delete(MapParameter.getInstance().add("stuId",studentId).getMap());

        String[] sectionIdArr = sectionIds.split(",");
        String[] courseIdArr = courseIds.split(",");
        int flag = 0;
        for (int i=0;i<sectionIdArr.length;i++) {
            Score score = new Score();
            score.setSectionId(Integer.parseInt(sectionIdArr[i]));
            score.setCourseId(Integer.parseInt(courseIdArr[i]));
            score.setStuId(studentId);
            flag = scoreMapper.create(score);
        }
        return flag;
    }


    public int delete(Integer id) {
        return scoreMapper.delete(MapParameter.getInstance().addId(id).getMap());
    }

    public int update(Score score) {
        Map<String, Object> map = MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(score)).addId(score.getId()).getMap();
        return scoreMapper.update(map);
    }

    public int update(Integer courseId,Integer sectionId,String stuIds,String scores) {
        String[] stuIdArray = stuIds.split(",");
        String[] scoresArray = scores.split(",");
        int flag = 0;
        for (int i=0;i<stuIdArray.length;i++) {
            Map<String, Object> map = MapParameter.getInstance()
                    .add("courseId", courseId)
                    .add("sectionId", sectionId)
                    .add("stuId", Integer.parseInt(stuIdArray[i]))
                    .add("updateScore", Double.parseDouble(scoresArray[i])).getMap();
            flag = scoreMapper.update(map);
        }
        return flag;
    }

    public List<Score> query(Score score) {
        return scoreMapper.query(BeanMapUtils.beanToMap(score));
    }

    public Score detail(Integer id) {
        return scoreMapper.detail(MapParameter.getInstance().addId(id).getMap());
    }

    public int count(Score score) {
        return scoreMapper.count(BeanMapUtils.beanToMap(score));
    }

    public List<HashMap> queryAvgScoreBySection(){
        return scoreMapper.queryAvgScoreBySection(null);
    }

    public List<HashMap> queryScoreByStudent(Integer studentId){
        Map<String, Object> map = MapParameter.getInstance().add("studentId", studentId).getMap();
        return scoreMapper.queryScoreByStudent(map);
    }

}
