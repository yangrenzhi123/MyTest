package com.yang.test.java.json;

import java.util.Map;

/**
 * 学生
 */
public class Student {

	private int age;// 年龄
	private String gender;// 性别，male/female
	private String grades;// 班级
	private String name;// 姓名
	private Map<String, Integer> score;// 各科分数
	private float weight;// 体重

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGrades() {
		return grades;
	}

	public void setGrades(String grades) {
		this.grades = grades;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Integer> getScore() {
		return score;
	}

	public void setScore(Map<String, Integer> score) {
		this.score = score;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}
}