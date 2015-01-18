package com.rhsmith.html;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.rhsmith.Wrapper.CourseWrapper;
import com.rhsmith.Wrapper.InstructorWrapper;
import com.rhsmith.Wrapper.StudentWrapper;

public interface HTMLWrapper {
	/**
	 * @author himanshusharma
	 * @param wrapper
	 * @param courseWrapper
	 * @param out
	 */

	public void getHTMLPageForStudent(StudentWrapper wrapper,
			List<CourseWrapper> courseWrapper, PrintWriter out,String url, HttpServletRequest request);

	public void getHTMLPageForInstructor(InstructorWrapper wrapper,
			List<CourseWrapper> courseWrapper, PrintWriter out,String url,HttpServletRequest request);

}
