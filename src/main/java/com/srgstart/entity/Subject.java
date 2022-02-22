package com.srgstart.entity;

import com.srgstart.util.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author srgstart
 * @Create 2021/5/24 18:22
 * @Description 专业表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject extends Entity {

	/**
	 * 所属院系
	 */
	private String college;
	/**
	 * 专业记录Id
	 */
	private Integer id;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 专业名称
	 */
	private String subjectName;
}