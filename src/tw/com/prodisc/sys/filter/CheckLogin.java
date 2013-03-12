package tw.com.prodisc.sys.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckLogin extends HttpServlet implements Filter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1717771792831867638L;
	String[] uriPass = {
			"/index.jsp",
			"/sys/blank.jsp",
			"/err01.jsp"
	};
	
	
	private boolean chk01(ServletRequest request,ServletResponse response)
			throws IOException, ServletException{
		HttpServletRequest  req = (HttpServletRequest)request;
		
		String uri = req.getServletPath();
		String cc  = uri.substring(uri.lastIndexOf("."),uri.length());
		String u01 = req.getRemoteUser();
		if (u01.lastIndexOf("\\")!= -1)
		 	   u01 = u01.substring(u01.lastIndexOf("\\")+1,u01.length());
		
		boolean bb = true;
		if (!cc.equals(".jsp") && !cc.equals(".action")) bb = false;
		for (int i=0;i<uriPass.length;i++)
			if ( uri.equals(uriPass[i]) ) bb = false;
		
		// if (u01.equals("86407")) bb = false;
		// u01 = "81021";
		return bb;
	}
	
	private boolean chk02(ServletRequest request,ServletResponse response)
			throws IOException, ServletException{
		
		
		boolean bb = false;
		return bb;
	}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest  req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		request.setCharacterEncoding("utf-8");
		if (chk01(request,response)) { // 檢查是否合法進入
			System.out.printf(req.getServletPath());
			res.sendRedirect("/BPM/err01.jsp");
		}
		
		if (chk02(request,response)) { // 檢查是否有權簽核此表單
			System.out.printf(req.getRemoteUser());
			res.sendRedirect("/BPM/err02.jsp");
		}
		chain.doFilter(request,response);
	}
	public void init(FilterConfig filterConfig) throws ServletException {}
}
