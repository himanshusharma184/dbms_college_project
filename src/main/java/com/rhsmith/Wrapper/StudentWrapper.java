package com.rhsmith.Wrapper;

import java.io.Serializable;
import java.util.Date;
/*
 * Student WrapperClass
 */
public class StudentWrapper implements Serializable,SuperWrapper{
	/**
	 * @author himanshusharma
	 */
	private static final long serialVersionUID = -6915682277019095798L;
	private String studentID;
    private String studentName;
    private String studentAddress;
    private String studentEmail;
    private String studentDepartment;
    private Date studentBirthDate;
    private String studentMajor;
    private String password;
    private String userType;
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentAddress() {
		return studentAddress;
	}
	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public String getStudentDepartment() {
		return studentDepartment;
	}
	public void setStudentDepartment(String studentDepartment) {
		this.studentDepartment = studentDepartment;
	}
	public Date getStudentBirthDate() {
		return studentBirthDate;
	}
	public void setStudentBirthDate(Date studentBirthDate) {
		this.studentBirthDate = studentBirthDate;
	}
	public String getStudentMajor() {
		return studentMajor;
	}
	public void setStudentMajor(String studentMajor) {
		this.studentMajor = studentMajor;
	}


}
