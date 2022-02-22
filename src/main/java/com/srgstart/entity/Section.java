package com.srgstart.entity;


import com.srgstart.util.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author srgstart
 * @Create 2021/5/24 18:22
 * @Description 学期表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Section extends Entity {

	/**
	 * 班级id
	 */
	private Integer clazzId;
	/**
	 * 课程Id
	 */
	private Integer courseId;
	/**
	 * 学期表记录Id
	 */
	private Integer id;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 老师Id
	 */
	private Integer teacherId;
	/**
	 * 类型（春季/秋季）
	 */
	private String type;
	/**
	 * 年度
	 */
	private Integer year;

	/**
	 * 当前学期对应的课程
	 */
	private Course course;

	/**
	 * 当前学期对应的老师
	 */
	private Teacher teacher;

	/**
	 * 当前学期对应的班级
	 */
	private Clazz clazz;

	/**
	 * 是否为当前学期
	 */
	private  Integer selected;
}