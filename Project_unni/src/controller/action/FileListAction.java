package controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UnniDao;

public class FileListAction implements Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		UnniDao dao = UnniDao.getInstance();
		List<String> list = dao.selectAllfile();
		
		req.setAttribute("list", list);
		req.getRequestDispatcher("fileList.jsp").forward(req, resp);
		
	}

}
