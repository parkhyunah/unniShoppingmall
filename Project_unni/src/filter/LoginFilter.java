package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// 요청을 받으면 Session의 쿠키에 memberId와 adminId를 검사해서 없으면 0
		// memberId가 있으면 1
		// adminID가 있으면 2
		// req.setAttribute("login", 0); >> 이런 식으로 login 상태값을 요청에 전달한다.

		HttpServletRequest httpRequest = (HttpServletRequest) req;
		HttpServletResponse httpResponse = (HttpServletResponse) resp;

		Cookie[] cookies = httpRequest.getCookies();

		if (cookies == null) {
			 // 처음으로 접속했을 때 cookie가 없기 때문에 쿠키가 없으면 login을 0으로 만들어준다.
			Cookie cookie = new Cookie("login", "0");
			httpResponse.addCookie(cookie);
			httpRequest.setAttribute("login", 0);
			
		} else {
			for (Cookie c : cookies) {
				if (c.getName().equals("memberId")) {
					httpRequest.setAttribute("login", 1);
					System.out.println("회원로그인중");
					break;

				} else if (c.getName().equals("adminId")) {
					httpRequest.setAttribute("login", 2);
					System.out.println("관리자로그인");
					break;

				} else {
					httpRequest.setAttribute("login", 0);
				}
			}
		}
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
