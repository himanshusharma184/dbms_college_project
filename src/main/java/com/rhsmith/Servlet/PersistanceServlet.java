package com.rhsmith.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import com.rhsmith.Utility.DbConnection;
import com.rhsmith.Wrapper.CourseRegisteredWrapper;
import com.rhsmith.Wrapper.CourseWrapper;
import com.rhsmith.Wrapper.SemesterWrapper;

public class PersistanceServlet extends InputServlet {
	/**
	 * @author himanshusharma
	 * 
	 */
	private static final long serialVersionUID = -2562607111627768087L;

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) {
		String user = null;
		Boolean isDeleted = false;
		Session session = DbConnection.getInstance().getSession();
		Transaction transaction = session.beginTransaction();
		if (StringUtils.isNotBlank(request.getParameter("cid"))) {
			CourseWrapper courseWrapper = new CourseWrapper();
			courseWrapper.setCourseID(request.getParameter("cid"));
			courseWrapper.setCourseSupervisor(request.getParameter("sid"));
			user = request.getParameter("sid");
			courseWrapper.setCourseName(request.getParameter("cname"));
			Criteria criteria = session.createCriteria(CourseWrapper.class);
			criteria.add(Restrictions.eq("courseID",
					request.getParameter("cid")));
			if (criteria.uniqueResult() != null) {
				session.delete(criteria.uniqueResult());
				transaction.commit();
				session.flush();
				isDeleted = true;
			} else {

				session.save(courseWrapper);
				session.flush();
				transaction.commit();
				Transaction transaction2 = session.beginTransaction();
				for (int i = 1; i < 5; i++) {
					if (!request.getParameter("sc" + i).equals("None")) {
						SemesterWrapper semesterWrapper = new SemesterWrapper();
						semesterWrapper.setSemID(UUID.randomUUID().toString());
						semesterWrapper
								.setCourseID(request.getParameter("cid"));
						if (i % 2 == 0) {
							semesterWrapper.setSemester("spring");
						} else {
							semesterWrapper.setSemester("fall");
						}
						if (request.getParameter("sc" + i).contains("2015"))
							semesterWrapper.setCalYear(2015);
						else {
							semesterWrapper.setCalYear(2016);
						}
						session.save(semesterWrapper);
						session.flush();
					}
				}

				transaction2.commit();
				session.flush();
				session.close();
			}
		} else {
			CourseRegisteredWrapper registeredWrapper = new CourseRegisteredWrapper();
			registeredWrapper.setStudentID(request.getParameter("uid"));
			user = request.getParameter("uid");
			registeredWrapper.setCourseID(request.getParameter("courseType"));
			Example example = Example.create(registeredWrapper);
			Criteria criteria = session.createCriteria(
					CourseRegisteredWrapper.class).add(example);
			CourseRegisteredWrapper wrapper = getWrapperToPurge(
					criteria.list(), request.getParameter("courseType"));
			if (wrapper != null) {
				Query query = session
						.createQuery("delete from CourseRegisteredWrapper where studentID='"
								+ request.getParameter("uid")
								+ "' and courseID='"
								+ request.getParameter("courseType") + "'");
				query.executeUpdate();
				transaction.commit();
				session.flush();
				isDeleted = true;
			} else {
				registeredWrapper.setCourseID(request
						.getParameter("courseType"));
				registeredWrapper.setTiming(request.getParameter("year"));
				registeredWrapper.setSemester(request.getParameter("sem"));
				session.flush();
				session.save(registeredWrapper);
				transaction.commit();
				session.flush();
				session.close();
			}
		}
		response.setContentType("text/html");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.println("<body style=\"background-color:Tomato\">");

			out.println("<h1>" + "DBMS Group 5 Project" + "</h1>");
			out.println("<br>");
			if (isDeleted) {
				out.println("<font color=black>Sussesfully dropped course Registed.</font>");
			} else
				out.println("<font color=black>Sussesfully Added course.</font>");

			out.println("<form action=\"returning\" method=\"post\">");

			out.println("<input type=\"hidden\" name=\"userForward\" value=\""
					+ user + "\">");
			// out.println("<button type=\"submit\" formaction=\"dbms/returning\">Do you want to return to previous page </button>");
			out.println("<input type=\"submit\" value=\"return\">");
			out.println("</form>");

			out.println("<font color=black>You may go to our home page.</font>");
			out.println("<font color=black>OR return back to add/drop.</font>");
			//
			String[] url = request.getRequestURL().toString().split("add");
			out.println("<a href=" + url[0] + ">home Page</a>");
			request.getSession().invalidate();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private CourseRegisteredWrapper getWrapperToPurge(
			List<CourseRegisteredWrapper> list, String cid) {
		for (CourseRegisteredWrapper wrapper : list) {
			if (wrapper.getCourseID().equals(cid))
				return wrapper;
		}

		return null;
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) {
		doGet(request, response);

	}
}
