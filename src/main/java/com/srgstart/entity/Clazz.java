package com.srgstart.entity;

import com.srgstart.util.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author srgstart
 * @Create 2021/5/24 18:22
 * @Description 班级表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clazz extends Entity {

	/**
	 * 班级名称
	 */
	private String clazzName;
	/**
	 * 班级编号
	 */
	private Integer id;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 专业Id
	 */
	private Integer subjectId;

	/**
	 * 当前班级所属专业
	 */
	private Subject subject;
}