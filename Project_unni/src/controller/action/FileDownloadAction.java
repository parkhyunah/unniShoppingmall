package controller.action;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileDownloadAction implements Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		// 파일 이름
		String fileName = req.getParameter("filename");

		// 파일 저장폴더 경로
		String downloadPath = "d:\\uploadTemp\\";

		// 파일 전체 경로
		String sFilePath = downloadPath + fileName;

		byte b[] = new byte[4096];
		FileInputStream fis = new FileInputStream(sFilePath);

		// 파일 종류 및 실행 어플리케이션에 대한 설명에 대한 정의
		String sMimeType = req.getSession().getServletContext().getMimeType(sFilePath);

		// MIME : Multipurpose Internet Mail Extensions : 파일 보낼 때 사용
		if (sMimeType == null) {

			// 8비트로 이루어진 데이터
			sMimeType = "application/octet=stream";
		}

		resp.setContentType(sMimeType);

		// 한글 파일명 처리
		String strEncoding = new String(fileName.getBytes("utf-8"), "8859_1");
		resp.setHeader("Content-Disposition", "attachment; filename=" + strEncoding);

		ServletOutputStream servletOutputStream = resp.getOutputStream();

		int readNum = 0;
		while ((readNum = fis.read(b, 0, b.length)) != -1) {
			servletOutputStream.write(b, 0, readNum);
		}
		servletOutputStream.flush();
		servletOutputStream.close();
		fis.close();
	}

}
