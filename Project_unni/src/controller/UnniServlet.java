package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.action.Action;

public class UnniServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProc(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProc(req,resp);
	}
	
	public void doProc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("utf-8");
		String command = req.getParameter("command");
		System.out.println("UnniServlet에서 요청받음 :" + command);
		
		ActionFactory af = ActionFactory.getInstance();
		Action action = af.getAction(command);
		
		if(action!=null) {
			action.execute(req, resp);
		}
	}
	
}
