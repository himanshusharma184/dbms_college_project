package com.rhsmith.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
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
import com.rhsmith.Wrapper.InstructorWrapper;
import com.rhsmith.Wrapper.StudentWrapper;
import com.rhsmith.Wrapper.SuperWrapper;
import com.rhsmith.html.InstructorLogin;
import com.rhsmith.html.StudentLogin;

public class InputServlet extends HttpServlet {
	String pwd1 = null;
	/**
	 * @author himanshusharma
	 */
	private Map<String, String> userMap = new HashMap<String, String>();
	private static final long serialVersionUID = -6122059961229091405L;
	private Boolean isConnected = false;

	@SuppressWarnings("unchecked")
	public void init() {
		DbConnection connection = DbConnection.getInstance();
		Session session = connection.getSession();

		if (session != null) {
			isConnected = true;
			Query query = session.createQuery("From StudentWrapper");
			List<StudentWrapper> studentList = (List<StudentWrapper>) query
					.list();
			session.flush();
			Query query1 = session.createQuery("From InstructorWrapper");
			List<InstructorWrapper> instructorList = (List<InstructorWrapper>) query1
					.list();
			session.flush();
			if (studentList != null && !studentList.isEmpty())
				try {
					populateUserMap(studentList);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			if (instructorList != null && !instructorList.isEmpty())
				populateUserMap1(instructorList);
			session.close();
		}

	}

	private void populateUserMap1(List<InstructorWrapper> instructorList) {
		for (InstructorWrapper wrapper : instructorList) {
			userMap.put(wrapper.getInstructorID().toUpperCase(),
					wrapper.getPassword());
		}

	}

	private void populateUserMap(List<StudentWrapper> studentList)
			throws UnsupportedEncodingException {
		for (StudentWrapper wrapper : studentList) {
			userMap.put(wrapper.getStudentID().toUpperCase(),
					wrapper.getPassword());
		}

	}

	/**
	 * 
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DbConnection connection = DbConnection.getInstance();
		Session session = connection.getSession();
		if (!isConnected) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<body background=\"smithschool.jpeg\">");

			out.println("<h1>" + "DBMS Group 5 Project" + "</h1>");
			out.println("<br>");
			out.println("<h2>" + "DataBase is down or Connection is Lost"
					+ "</h2>");
			try {
				request.wait(Long.valueOf("20000"));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.exit(1);
		}
		String userId = request.getParameter("user");
		if (StringUtils.isBlank(userId)) {
			registerNewUser(request, response, session);
		} else {
			existingUserLogin(request, response, session, userId);
		}

	}

	private void existingUserLogin(HttpServletRequest request,
			HttpServletResponse response, Session session, String userId)
			throws ServletException, IOException {
		String pwd;
		pwd = request.getParameter("pwd");
		if (StringUtils.isBlank(pwd)) {

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<body style=\"background-color:Beige\">");
			out.println("<h1>" + "password is missing" + "</h1>");
			out.println("<font color=black>You may go to our home page and try again.</font>");

			out.println("<a href=" + getCurrentURL(request) + ">Resubmit Here</a>");
			request.getSession().invalidate();

		}
		else if(!verifyIdentity(userId, getMDSEncryptedPwd(pwd))) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<body style=\"background-color:Beige\">");

			out.println("<h1>" + "DBMS Group 5 Project" + "</h1>");
			out.println("<br>");
			out.println("<font color=red>Either user name or password is wrong .</font>");
			out.println("<font color=black>You may go to our home page and try again.</font>");
			out.println("<a href=" + getCurrentURL(request) + ">Home page</a>");
			request.getSession().invalidate();
		} else {
			userUpdate(request, response, session, userId);
		}
	}

	String getCurrentURL(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + "/" + "dbms-5" + "/";
	}

	protected void userUpdate(HttpServletRequest request,
			HttpServletResponse response, Session session, String userId)
			throws IOException {
		Transaction transaction = session.beginTransaction();
		session.flush();
		Criteria criteria = session.createCriteria(StudentWrapper.class);
		criteria.add(Restrictions.eq("studentID", userId));
		StudentWrapper wrapper = (StudentWrapper) criteria.uniqueResult();
		session.flush();

		if (wrapper == null) {
			Criteria criteria1 = session
					.createCriteria(InstructorWrapper.class);
			criteria1.add(Restrictions.eq("instructorID", userId));
			InstructorWrapper wrapper1 = (InstructorWrapper) criteria1
					.uniqueResult();
			session.flush();
			publishHTML(request, response, session, "instructor", wrapper1,
					transaction, false, request.getRequestURI());
		} else {
			publishHTML(request, response, session, "student", wrapper,
					transaction, false, request.getRequestURI());
		}
	}

	@SuppressWarnings("deprecation")
	private void registerNewUser(HttpServletRequest request,
			HttpServletResponse response, Session session) throws IOException {
		String userName;
		String deparName;
		String email = null;
		String semester;
		String userType;
		String addr = null;
		String dob = null;
		String userID;
		userID = request.getParameter("Nuid");
		userName = request.getParameter("Nuser");
		deparName = request.getParameter("dName");
		pwd1 = request.getParameter("pwd1");
		semester = request.getParameter("sem");
		if (StringUtils.isBlank(userID) || StringUtils.isBlank(userName)
				|| StringUtils.isBlank(pwd1)) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<body style=\"background-color:AliceBlue\">");
			out.println("<font color=black>Informations are missing. Please resubmit Registration form.</font>");
			out.println("<a href=" + getCurrentURL(request) + ">Resubmit Here</a>");
			request.getSession().invalidate();
		}
		userMap.put(userID.toUpperCase(), getMDSEncryptedPwd(pwd1));
		if (StringUtils.isNotBlank(request.getParameter("email")))
			email = request.getParameter("email");
		if (StringUtils.isNotBlank(request.getParameter("addr"))
				&& !request.getParameter("addr").equals("optional"))
			addr = request.getParameter("addr");
		userType = request.getParameter("userType");
		if (request.getParameter("dob") != null)
			dob = request.getParameter("dob").toString();
		Transaction transaction = session.beginTransaction();
		if (userType.equals("student")) {
			StudentWrapper wrapper = new StudentWrapper();
			if (email != null)
				wrapper.setStudentEmail(email);
			wrapper.setUserType(userType);
			wrapper.setStudentAddress(addr);
			wrapper.setStudentName(userName);
			wrapper.setStudentDepartment(deparName);
			if (dob != null)
				wrapper.setStudentBirthDate(new Date(dob));

			wrapper.setPassword(getMDSEncryptedPwd(pwd1));
			wrapper.setStudentID(userID);
			if (StringUtils.isNotBlank(semester))
				wrapper.setStudentMajor(semester.toString());

			session.save(wrapper);
			transaction.commit();
			session.flush();
			publishHTML(request, response, session, userType, wrapper,
					transaction, true, request.getRequestURI());
		} else {
			InstructorWrapper wrapper = new InstructorWrapper();
			wrapper.setInstructorEmail(email);
			wrapper.setInstructorAddress(addr);
			wrapper.setInstructorName(userName.toString());
			wrapper.setInstructorDepartment(deparName);
			wrapper.setInstructorBirthDate(new Date(dob));
			wrapper.setPassword(getMDSEncryptedPwd(pwd1));
			wrapper.setInstructorID(userID);
			session.save(wrapper);
			transaction.commit();
			session.flush();
			publishHTML(request, response, session, userType, wrapper,
					transaction, true, request.getRequestURI());
		}

	}

	@SuppressWarnings("unchecked")
	protected void publishHTML(HttpServletRequest request,
			HttpServletResponse response, Session session, String userType,
			SuperWrapper wrapper, Transaction transaction, boolean newUser,
			String url) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		if (userType.equals("instructor")) {
			InstructorLogin instructorLogin = new InstructorLogin();
			if (newUser)
				instructorLogin.getHTMLPageForInstructor(
						(InstructorWrapper) wrapper, null, out, url,request);
			else {
				CourseWrapper wrapp = new CourseWrapper();
				wrapp.setCourseSupervisor(((InstructorWrapper) wrapper)
						.getInstructorID());
				Example example = Example.create(wrapp);
				Criteria query = session.createCriteria(CourseWrapper.class)
						.add(example);
				List<CourseWrapper> courseList = query.list();
				session.flush();
				instructorLogin.getHTMLPageForInstructor(
						(InstructorWrapper) wrapper, courseList, out, url,request);
			}
			CourseWrapper courseWrapper = new CourseWrapper();
			courseWrapper.setCourseID(request.getParameter("cid"));
			courseWrapper.setCourseSupervisor(request.getParameter("sid"));
			courseWrapper.setCourseName(request.getParameter("cname"));
			if (!transaction.isActive()) {
				transaction = session.beginTransaction();
			}
			session.save(courseWrapper);
			transaction.commit();
			session.flush();

		} else {
			if (!transaction.isActive()) {
				transaction = session.beginTransaction();
			}
			Query query = session.createQuery("From CourseWrapper");
			List<CourseWrapper> courseList = query.list();
			StudentLogin login = new StudentLogin();
			login.getHTMLPageForStudent((StudentWrapper) wrapper, courseList,
					out, url,request);
			if (courseList != null && !courseList.isEmpty()) {
				String courseRegistered = null;
				for (CourseWrapper courseWrapper : courseList) {
					if (request.getParameter(courseWrapper.getCourseName()) != null) {
						courseRegistered = request.getParameter(courseWrapper
								.getCourseName());
						break;
					}
				}
				CourseRegisteredWrapper registeredWrapper = new CourseRegisteredWrapper();
				registeredWrapper.setCourseID(courseRegistered);
				registeredWrapper
						.setStudentID(((CourseRegisteredWrapper) wrapper)
								.getStudentID());
				registeredWrapper.setSemester(request.getParameter("semType"));
				registeredWrapper.setTiming(request.getParameter("sec"));
				session.save(registeredWrapper);
				transaction.commit();
				session.flush();
			}
		}
	}

	private boolean verifyIdentity(String userId, String pwd) {
		if (userMap.containsKey(userId.toUpperCase())
				&& userMap.get(userId.toUpperCase()).equals(pwd)) {
			return true;
		}
		return false;

	}

	private String getMDSEncryptedPwd(String password) {
		byte[] source;
		String outPut = null;
		char hexCharSet[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			source = password.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			source = password.getBytes();

		}
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(source);
			byte temp[] = digest.digest();
			char tempChar[] = new char[16 * 2];
			int k = 0;
			for (int i = 0; i < 16; i++) {
				byte convertedValue = temp[i];
				tempChar[k++] = hexCharSet[convertedValue >>> 4 & 0xf];
				tempChar[k++] = hexCharSet[convertedValue & 0xf];
			}
			outPut = new String(tempChar);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outPut;
	}

	@Override
	protected void doPost(HttpServletRequest servletRequest,
			HttpServletResponse httpServletResponse) throws ServletException,
			IOException {
		doGet(servletRequest, httpServletResponse);

	}

}
