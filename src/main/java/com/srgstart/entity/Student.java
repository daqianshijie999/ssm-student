package com.srgstart.entity;


import com.srgstart.util.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author srgstart
 * @Create 2021/5/24 18:22
 * @Description 学生表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student  extends Entity {


	/**
	 * 学生地址
	 */
	private String addr;
	/**
	 * 学生性别
	 */
	private String gender;
	/**
	 * 出生年月
	 */
	private Date birthday;
	/**
	 * 身份证号
	 */
	private String cardNo;
	/**
	 * 班级号
	 */
	private Integer clazzId;
	/**
	 * 学生记录Id
	 */
	private Integer id;
	/**
	 * 入学时间
	 */
	private Date joinDate;
	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 父母姓名
	 */
	private String pname;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 学生姓名
	 */
	private String stuName;
	/**
	 * 学籍号
	 */
	private String stuNo;
	/**
	 * 登录密码
	 */
	private String stuPwd;
	/**
	 * 专业Id
	 */
	private Integer subjectId;
	/**
	 * 父母联系方式
	 */
	private String telephone;

	/**
	 * 该学生的专业信息
	 */
	private Subject subject;

	/**
	 * 该学生的班级信息
	 */
	private Clazz clazz;

	public static class StatusType{
		public static String type_1 = "正常";
		public static String type_2 = "辍学";
		public static String type_3 = "休学";
		public static String type_4 = "复学";
		public static String type_5 = "转学";
		public static String type_6 = "转入";
		public static String type_7 = "毕业";
	}

}