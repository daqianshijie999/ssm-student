package com.srgstart.entity;



import com.srgstart.util.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author srgstart
 * @Create 2021/5/24 18:22
 * @Description 就业表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job extends Entity {

	/**
	 * 就业企业
	 */
	private String compName;
	/**
	 * 就业信息编号
	 */
	private Integer id;
	/**
	 * 就业时间
	 */
	private String jobDate;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 薪资
	 */
	private Integer salay;
	/**
	 * 学生Id
	 */
	private Integer stuId;
}