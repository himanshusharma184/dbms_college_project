package com.rhsmith.html;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;

import com.rhsmith.Utility.DbConnection;
import com.rhsmith.Wrapper.CourseRegisteredWrapper;
import com.rhsmith.Wrapper.CourseWrapper;
import com.rhsmith.Wrapper.InstructorWrapper;
import com.rhsmith.Wrapper.StudentWrapper;

public  class InstructorLogin implements HTMLWrapper {
	/**
	 * @author himanshusharma
	 * @param url
	 */

	@SuppressWarnings("unchecked")
	public void getHTMLPageForInstructor(InstructorWrapper wrapper,
			List<CourseWrapper> courseWrapperList, PrintWriter out,String url, HttpServletRequest request) {
		out.println("<body style=\"background-color:33CCFF\">");
		out.println("<head>");
		out.println("<style>");
		out.println("#header{");
		out.println("background-color:CadetBlue;");
		out.println("color:white;");
		out.println("text-align:center;");
		out.println("padding:5px;");
		out.println("}");
		out.println("#nav{");
		out.println("line-height:30px;");
		out.println("background-color:#eeeeee;");
		out.println("height:300px;");
		out.println("width:100px;");
		out.println("float:left;");
		out.println("padding:5px;");
		out.println("}");
		out.println("#section {");
		out.println("background-color:grey;");
		out.println("width:350px;");
		out.println("float:right;");
		out.println("padding:10px;");
		out.println("}");
		out.println("#footer {");
		out.println("background-color:grey;");
		out.println("color:orange;");
		out.println("clear:both;");
		out.println("text-align:center;");
		out.println("padding:5px;");
		out.println("}");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div id=\"header\">");
		out.println("<h1>Welcome Prof. " + wrapper.getInstructorName()
				+ "</h1>");
		out.println("<h2>The following Information is Extracted From Our System</h2>");
		out.println("</div>");
		out.println("<div id=\"section\">");
		out.println("<h2>Course Registered</h2>");
		out.println("<table border=\"4\" style=\"width:100%\">");

		out.println("<tr>");
		out.println("<td>CourseID</td>");
		out.println("<td>Course name</td>");
		
		out.println("<td>Semester</td>");
		out.println("<td>Supervisor<td>");
		out.println("</tr>");
		List<CourseRegisteredWrapper> allCourseRegistered = new ArrayList<CourseRegisteredWrapper>();
		DbConnection connection = DbConnection.getInstance();
		Session session = connection.getSession();
		Query query = session.createQuery("From CourseRegisteredWrapper");
		List<CourseRegisteredWrapper> allFetchedData = query.list();
		session.flush();
		if (courseWrapperList != null && !courseWrapperList.isEmpty()) {
			for (CourseWrapper courseWrapper : courseWrapperList) {
				out.println("<tr>");
				out.println("<td>" + courseWrapper.getCourseID() + "</td>");
				out.println("<td>" + courseWrapper.getCourseName() + "</td>");
				
				out.println("<td>spring-2015</td>");
				out.println("<td>" + courseWrapper.getCourseSupervisor()
						+ "</td>");
				out.println("</tr>");
				for (CourseRegisteredWrapper courseRegisteredWrapper : allFetchedData) {
					if (courseRegisteredWrapper.getCourseID().equals(
							courseWrapper.getCourseID())) {
						allCourseRegistered.add(courseRegisteredWrapper);
					}
				}

			}
		}
		out.println("</table>");
		out.println("</div>");
		out.println("<br>");
		out.println("<div id=\"section\">");
		out.println("<h2>Students</h2>");
		out.println("<table border=\"4\" style=\"width:100%\">");
		out.println("<tr>");
		out.println("<td>Student name</td>");
		out.println("<td>course name</td>");
		out.println("<td>Section</td>");
		out.println("<<td>Semester</td>");
		out.println("</tr>");

		for (CourseRegisteredWrapper courseRegisteredWrapper : allCourseRegistered) {
			out.println("<tr>");
			out.println("<td>" + courseRegisteredWrapper.getStudentID()
					+ "</td>");
			out.println("<td>" + courseRegisteredWrapper.getCourseID()
					+ "</td>");
			out.println("<td>" + courseRegisteredWrapper.getTiming() + "</td>");

			out.println("<td>" + courseRegisteredWrapper.getSemester()
					+ "</td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("</div>");
		out.println("<br>");
		out.println("<div id=\"Add/drop\">");
		if (!url.contains("dbms"))
			out.println("<form action=\"dbms/add\" method=\"post\">");
		else
			out.println("<form action=\"add\" method=\"post\">");
		out.println("<h2>Course Add/drop</h2>");
		out.println("<br>");

		out.println("<form>");

		out.println("course name:<br><input type=\"text\" name=\"cname\">");
		out.println("<br>");
		out.println("courseID:<br><input type=\"text\" name=\"cid\"><br>");
		out.println("Schedule 1:   <select name=\"sc1\">");
		out.println("<option value=\"None\">None</option>");
		out.println("<option value=\"spring-2015\">spring-2015</option>");
		out.println("</select>");
		out.println("<br>");
		out.println("Schedule 2:   <select name=\"sc2\">");
		out.println("<option value=\"None\">None</option>");
		out.println("<option value=\"fall-2015\">fall-2015</option>");
		out.println("</select>");
		out.println("<br>");
		out.println("Schedule 3:   <select name=\"sc3\">");
		out.println("<option value=\"None\">None</option>");
		out.println("<option value=\"spring-2016\">spring-2016</option>");
		out.println("</select>");
		out.println("<br>");
		out.println("Schedule 4:   <select name=\"sc4\">");
		out.println("<option value=\"None\">None</option>");
		out.println("<option value=\"fall-2016\">fall-2016</option>");
		out.println("</select>");
		out.println("<br>");
		out.println("<input type=\"hidden\" name=\"sid\" value=\""
				+ wrapper.getInstructorID() + "\">");
		out.println("<input type=\"submit\" value=\"Add/Drop\">");
		out.println("</form>");

		out.println("<br>");

		out.println("<br>");
		out.println("<div id=\"footer\">");
		out.println("&copyDBMS Project group 5");
	
		out.println("<a href="+request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + "/" + "dbms-5" + "/"+">home Page</a>");
		out.println("</div>");

	}

	public void getHTMLPageForStudent(StudentWrapper wrapper,
			List<CourseWrapper> courseWrapper, PrintWriter out,String url, HttpServletRequest request) {
		// TODO Auto-generated method stub

	}

}
