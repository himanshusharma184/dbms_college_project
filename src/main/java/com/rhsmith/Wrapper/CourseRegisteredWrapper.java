package com.rhsmith.Wrapper;

import java.io.Serializable;

public class CourseRegisteredWrapper implements Serializable {
	/**
	 * @author himanshusharma
	 */
	private static final long serialVersionUID = -1391187444554043055L;
	private String courseID;
	private String studentID;
	private String semester;
	private String timing;

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getTiming() {
		return timing;
	}

	public void setTiming(String timing) {
		this.timing = timing;
	}

}
