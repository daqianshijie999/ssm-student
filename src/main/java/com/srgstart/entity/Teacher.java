package com.srgstart.entity;

import com.srgstart.util.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author srgstart
 * @Create 2021/5/24 18:22
 * @Description 老师表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher extends Entity {

	/**
	 * 教师记录Id
	 */
	private Integer id;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 教师登录密码
	 */
	private String teacherPwd;
	/**
	 * 教师登录名称
	 */
	private String teacherName;
	/**
	 * 教师姓名
	 */
	private String name;

}