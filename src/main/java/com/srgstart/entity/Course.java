package com.srgstart.entity;



import com.srgstart.util.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author srgstart
 * @Create 2021/5/24 18:22
 * @Description 课程表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course extends Entity {

	/**
	 * 课程名称
	 */
	private String courseName;
	/**
	 * 课程编号
	 */
	private Integer id;
	/**
	 * 备注
	 */
	private String remark;
}