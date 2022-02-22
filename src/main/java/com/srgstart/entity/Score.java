package com.srgstart.entity;

import com.srgstart.util.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author srgstart
 * @Create 2021/5/24 18:22
 * @Description 分数表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score extends Entity {

	/**
	 * 课程Id
	 */
	private Integer courseId;
	/**
	 * 成绩表记录Id
	 */
	private Integer id;
	/**
	 * 分数
	 */
	private Double score;
	/**
	 * 开课（开学）Id
	 */
	private Integer sectionId;
	/**
	 * 学生Id
	 */
	private Integer stuId;
}