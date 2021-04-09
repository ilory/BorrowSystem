package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "loginFilter",
    urlPatterns = "/*",
    initParams = {
        @WebInitParam(name="login",value="login")
    })
public class LoginFilter implements Filter {
    private FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config =filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(true);
        request.setCharacterEncoding("UTF-8");
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
        String userName = (String)session.getAttribute("userName");
        String uri = request.getRequestURI();
        String login = config.getInitParameter("login");

        System.out.println(uri);
        if(uri.contains(login)||uri.endsWith("css")||uri.equals("js")||uri.endsWith("ico")
            ||uri.endsWith("jpg")||uri.endsWith("png")||uri.endsWith("jpeg")||uri.endsWith("woff")){
            filterChain.doFilter(request,response);
            System.out.println("放行进入登录");
        }else{
            System.out.println(userName);
            if(userName == null || "".equals(userName)){
                response.setHeader("Cache-Control", "no-store");
                response.setDateHeader("Expires", 0);
                response.setHeader("Prama", "no-cache");
                System.out.println(basePath);
                System.out.println("<--------->");
                response.sendRedirect(basePath+"/login.html");
            }else{
                System.out.println("放行");
                filterChain.doFilter(request,response);
            }
        }

    }
}
