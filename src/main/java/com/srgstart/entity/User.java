package com.srgstart.entity;


import com.srgstart.util.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author srgstart
 * @Create 2021/5/24 18:22
 * @Description 管理员表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends Entity {

	/**
	 * 用户记录Id
	 */
	private Integer id;
	/**
	 * 用户姓名
	 */
	private String name;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 用户登录名
	 */
	private String userName;
	/**
	 * 用户密码
	 */
	private String userPwd;
}