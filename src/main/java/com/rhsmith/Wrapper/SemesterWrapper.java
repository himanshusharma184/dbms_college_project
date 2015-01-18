package com.rhsmith.Wrapper;

import java.io.Serializable;

public class SemesterWrapper implements Serializable {

	/**
	 * @author himanshusharma
	 */
	private static final long serialVersionUID = -6787166103051320596L;
	private Integer CalYear;
	private String Semester;
	private String courseID;
	private String semID;

	public String getSemID() {
		return semID;
	}

	public void setSemID(String semID) {
		this.semID = semID;
	}

	public Integer getCalYear() {
		return CalYear;
	}

	public void setCalYear(Integer calYear) {
		CalYear = calYear;
	}

	public String getSemester() {
		return Semester;
	}

	public void setSemester(String semester) {
		Semester = semester;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

}
