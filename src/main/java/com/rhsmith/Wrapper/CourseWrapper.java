package com.rhsmith.Wrapper;

import java.io.Serializable;

public class CourseWrapper implements Serializable{

	/**
	 * @author himanshusharma
	 */
	private static final long serialVersionUID = 4016442325649657847L;
	private String courseID=null;
    private String courseName=null;
    private String courseLimit=null;
    private String courseSupervisor=null;
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseLimit() {
		return courseLimit;
	}
	public void setCourseLimit(String courseLimit) {
		this.courseLimit = courseLimit;
	}
	public String getCourseSupervisor() {
		return courseSupervisor;
	}
	public void setCourseSupervisor(String courseSupervisor) {
		this.courseSupervisor = courseSupervisor;
	}

}
