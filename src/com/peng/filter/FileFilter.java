package com.peng.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;



import com.peng.model.AuctionUser;
import com.peng.util.StringUtil;

public class FileFilter implements Filter {

	final Logger logger = Logger.getLogger(getClass());

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// FilterChain 是过滤器用来控制走向
		// 当WEB容器中没有过滤器 它会访问用户的目标文件 否则 访问
		// 将ServletRequest ServletResponse 转为httpServletRequest
		// HTTPServletResponse
		HttpServletRequest httpServletRequest = (HttpServletRequest) arg0;
		HttpServletResponse httpServletResponse = (HttpServletResponse) arg1;
		// 获取用户访问的文件地址
		String fileURI = httpServletRequest.getRequestURI();

		// 获取到用户访问的文件地址后 再截取 我们想要 截取的文件名
		// indexOf 是搜索 字符串中的关键字
		fileURI = fileURI.substring(fileURI.indexOf("/", 1), fileURI.length());
		// 排除掉不能拦截的文件
		if (fileURI.contains("/login.jsp") || fileURI.contains("/Number.jsp")
				|| fileURI.contains("/AuctionLoginServlet")
				|| fileURI.contains("/images") || fileURI.contains("/css")
				|| fileURI.contains("/js")) {
			try {
				arg2.doFilter(httpServletRequest, httpServletResponse);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// 判断用户是否登录
			AuctionUser auctionUser = (AuctionUser) httpServletRequest
					.getSession().getAttribute("user");
			if (auctionUser == null) {
				// 进入到这代表用户没有登录
				try {
					httpServletResponse.sendRedirect("login.jsp");

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				try {
					String usArgsArray = null;
					// 获取请求用户的Id
					String usHost = httpServletRequest.getLocalAddr();
					// 获取用户请求的方法
					String usMethod = httpServletRequest.getMethod();
					// 获取用户的提交参数
					if (StringUtil
							.notEmpty(httpServletRequest.getQueryString())) {
						usArgsArray = httpServletRequest.getQueryString();
					}
					logger.info(auctionUser.getUserName() + "用户的ip地址为: "
							+ usHost + ",请求的方法是: " + usMethod + ",访问的文件名是: "
							+ fileURI + "," + "用户的输入参数为"
							+ Arrays.toString(usArgsArray.split("%")) + "");

					arg2.doFilter(httpServletRequest, httpServletResponse);

				} catch (Exception e) {
					e.printStackTrace();
					logger.error("错误为：" + e.getMessage() + "");
				}
			}
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
