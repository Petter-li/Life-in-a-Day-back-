package com.example.life.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.example.life.validation.ValidateLogin;
import com.example.life.validation.ValidateSign;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class User {
	@Id
	@GeneratedValue
	private Integer id;
	
	@NotNull (message = "请输入用户名", groups = {ValidateSign.class, ValidateLogin.class})
	private String name;
	
	@NotNull (message = "请输入密码", groups = {ValidateSign.class, ValidateLogin.class})
	private String password;
	
	@NotNull (message = "请输入出生日期", groups = {ValidateSign.class})
	private Date birthDay;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", birthDay=" + birthDay + "]";
	}
	
	
}
