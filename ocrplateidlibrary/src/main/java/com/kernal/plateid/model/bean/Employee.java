package com.kernal.plateid.model.bean;

public class Employee {
	private int id;
	private int card_num;
	private String name;
	private String department;
	private String gender;



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCard_num() {
		return card_num;
	}
	public void setCard_num(int card_num) {
		this.card_num = card_num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Employee{" +
				"id=" + id +
				", card_num=" + card_num +
				", name='" + name + '\'' +
				", department='" + department + '\'' +
				", gender='" + gender + '\'' +
				'}';
	}

	
	

}
