package com.rhsmith.Servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.rhsmith.Utility.DbConnection;

public class ReturningUserServlet extends InputServlet {
	/**
	 * @author himanshusharma
	 */
	private static final long serialVersionUID = 4554305471209386808L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) {
		doGet(request, response);

	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) {
		String user = request.getParameter("userForward");
		Session session = DbConnection.getInstance().getSession();
		try {
			userUpdate(request, response, session, user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
