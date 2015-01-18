package com.rhsmith.Wrapper;

import java.io.Serializable;
import java.util.Date;

public class InstructorWrapper implements Serializable,SuperWrapper {
	/**
	 * @author himanshusharma
	 */
	private static final long serialVersionUID = -277647481269314087L;
	private String instructorID;
	private String instructorName;
	private String instructorAddress;
	private String instructorOffice;
	private String instructorDepartment;
	private Date instructorBirthDate;
	private String instructorEmail;
	private String password;
	
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getInstructorID() {
		return instructorID;
	}

	public void setInstructorID(String instructorID) {
		this.instructorID = instructorID;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	public String getInstructorAddress() {
		return instructorAddress;
	}

	public void setInstructorAddress(String instructorAddress) {
		this.instructorAddress = instructorAddress;
	}

	public String getInstructorOffice() {
		return instructorOffice;
	}

	public void setInstructorOffice(String instructorOffice) {
		this.instructorOffice = instructorOffice;
	}

	public String getInstructorDepartment() {
		return instructorDepartment;
	}

	public void setInstructorDepartment(String instructorDepartment) {
		this.instructorDepartment = instructorDepartment;
	}

	public Date getInstructorBirthDate() {
		return instructorBirthDate;
	}

	public void setInstructorBirthDate(Date date) {
		this.instructorBirthDate = date;
	}

	public String getInstructorEmail() {
		return instructorEmail;
	}

	public void setInstructorEmail(String instructorEmail) {
		this.instructorEmail = instructorEmail;
	}

}
