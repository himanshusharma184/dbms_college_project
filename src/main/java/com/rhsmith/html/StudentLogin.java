package com.rhsmith.html;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import com.rhsmith.Utility.DbConnection;
import com.rhsmith.Wrapper.CourseRegisteredWrapper;
import com.rhsmith.Wrapper.CourseWrapper;
import com.rhsmith.Wrapper.InstructorWrapper;
import com.rhsmith.Wrapper.StudentWrapper;

public class StudentLogin implements HTMLWrapper {
	/**
	 * @author himanshusharmaS
	 */

	@SuppressWarnings("unchecked")
	public void getHTMLPageForStudent(StudentWrapper wrapper,
			List<CourseWrapper> courseWrapperList, PrintWriter out, String url,HttpServletRequest request) {
		out.println("<body style=\"background-color:CC3300\">");
		out.println("<head>");
		out.println("<style>");
		out.println("#header{");
		out.println("background-color:DarkKhaki;");
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
		out.println("background-color:orange;");
		out.println("width:350px;");
		out.println("float:right;");
		out.println("padding:10px;");
		out.println("}");
		out.println("#footer {");
		out.println("background-color:black;");
		out.println("color:white;");
		out.println("clear:both;");
		out.println("text-align:center;");
		out.println("padding:5px;");
		out.println("}");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div id=\"header\">");
		out.println("<h1>Welcome " + wrapper.getStudentName() + "</h1>");
		out.println("<h2>The following Information is Extracted From Our System</h2>");
		out.println("</div>");
		out.println("<div id=\"section\">");
		out.println("<h2>Course Registered</h2>");
		out.println("<table border=\"4\" style=\"width:100%\">");

		out.println("<tr>");
		out.println("<td>CourseID</td>");
		
		
		out.println("<td>Semester</td>");
		out.println("<td>Year</td> ");
		out.println("</tr>");
		DbConnection connection = DbConnection.getInstance();
		Session session = connection.getSession();
		List<CourseRegisteredWrapper> allCourseRegistered = null;
		if (session != null) {
			CourseRegisteredWrapper wrap = new CourseRegisteredWrapper();
			wrap.setStudentID(wrapper.getStudentID());
			Example example = Example.create(wrap);
			Criteria criteria = session.createCriteria(
					CourseRegisteredWrapper.class).add(example);
			allCourseRegistered = criteria.list();
			session.flush();

		}
		if (courseWrapperList != null && !courseWrapperList.isEmpty()) {
			for (CourseRegisteredWrapper courseWrapper : allCourseRegistered) {

				out.println("<tr>");
				out.println("<td>" + courseWrapper.getCourseID() + "</td>");
				
				
				out.println("<td>" + courseWrapper.getSemester() + "</td>");
				out.println("<td>" + courseWrapper.getTiming() + "</td>");
				out.println("</tr>");
			}
		}
		out.println("</table>");
		out.println("</div>");
		out.println("<br>");
		if (!url.contains("dbms"))
			out.println("<form action=\"dbms/add\" method=\"post\">");
		else
			out.println("<form action=\"add\" method=\"post\">");
		out.println("<title>Course Registration Page</title>");
		out.println("<h2>Course Add/drop</h2>");
		out.println("<br>");

		if (courseWrapperList != null && !courseWrapperList.isEmpty()) {
			out.println("Course :<select name=\"courseType\">");
			for (CourseWrapper courseWrapper : courseWrapperList) {
				out.println("<option value=\"" + courseWrapper.getCourseID()
						+ "\">" + courseWrapper.getCourseID() + "</option>");
			}
			out.println("</select>");
			out.println("<br>");
			out.println("Semester :<select name=\"sem\">");
			out.println("<option value=\"spring\">spring</option>");
			out.println("</select>");
			
			out.println("<br>");
			out.println("Year :<select name=\"year\">");
			out.println("<option value=\"2015\">2015</option>");
			out.println("</select>");
			out.println("<br>");

			out.println("Section :<select name=\"sec\">");
			out.println("<option value=\"0501\">0501</option>");
			out.println("<option value=\"0502\">0502</option>");
			out.println("</select>");
			out.println("<br>");
			out.println("<input type=\"hidden\" name=\"uid\" value=\""
					+ wrapper.getStudentID() + "\">");
			out.println(" <input type=\"submit\" value=\"Add/Drop\">");
			out.println("<br>");
			out.println("<div id=\"footer\">");
			out.println("&copyDBMS Project group 5");
			out.println("<a href="+request.getScheme() + "://" + request.getServerName() + ":"
					+ request.getServerPort() + "/" + "dbms-5" + "/"+">home Page</a>");
			out.println("</div>");

		} else {

			out.println("<h2>" + "No active Course was found" + "</h2>");
			out.println("<br>");
			out.println("<font color=black>Wait for course to be added .</font>");
		}
	}

	public void getHTMLPageForInstructor(InstructorWrapper wrapper,
			List<CourseWrapper> courseWrapper, PrintWriter out, String url,HttpServletRequest request) {
		// Not Required

	}
}
