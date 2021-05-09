package top.xiongmingcai.bbs.filter;

import top.xiongmingcai.bbs.common.Constant;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

//@WebFilter(filterName = "sessionFilter", urlPatterns = {"/*"})
public class SessionFilter implements Filter {


    //免登录就可访问的路径(比如:注册,登录,注册页面上的一些获取数据等)
    private String[] includeUrls = new String[]{"/register", "/login"};


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        //当前请求的url
        String uri = request.getRequestURI();

        System.out.println("filter url:" + uri);
        //判断url是否需要过滤
        boolean needFilter = isNeedFilter(uri);

        if (!needFilter) { //不需要过滤直接传给下一个过滤器
            filterChain.doFilter(servletRequest, servletResponse);
        } else { //需要过滤器
            // session中包含user对象,则是登录状态
            if (session != null && session.getAttribute(Constant.loginUser) != null) {
                filterChain.doFilter(request, response);
            } else {
                PrintWriter out = new HttpServletResponseWrapper(
                        (HttpServletResponse) servletResponse).getWriter();
                response.setContentType("application/json; charset=utf-8");
                out.write("{\n" +
                        "    \"code\": -1,\n" +
                        "    \"data\": null,\n" +
                        "    \"msg\": \"您还未登录,请先登录！！！\"\n" +
                        "}");
                out.flush();
                out.close();
            }
        }
    }


    /**
     * 是否放行
     *
     * @param uri
     * @return
     */
    public boolean isNeedFilter(String uri) {

        for (String includeUrl : includeUrls) {
            if (includeUrl.equals(uri)) {
                return false;
            }
        }

        return true;
    }

}
