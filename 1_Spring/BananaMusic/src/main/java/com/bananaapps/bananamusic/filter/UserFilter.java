package com.bananaapps.bananamusic.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = {"/user"})
public class UserFilter implements Filter {


    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain)
            throws IOException, ServletException {

        HttpServletResponse respo = (HttpServletResponse) response;
        respo.setStatus(403);
    }

    @Override
    public void init(FilterConfig filterconfig) throws ServletException {
    }
}