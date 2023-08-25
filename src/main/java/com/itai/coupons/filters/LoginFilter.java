package com.itai.coupons.filters;

import com.itai.coupons.enums.ErrorType;
import com.itai.coupons.enums.UserType;
import com.itai.coupons.exceptions.ApplicationException;
import com.itai.coupons.utils.JWTUtils;
import com.sun.xml.bind.v2.TODO;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@Component
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String methodType = request.getMethod().toLowerCase();
        if (methodType.equals("options")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String url = request.getRequestURI().toLowerCase();
        String token = request.getHeader("Authorization");

        //todo check if try is line 37 or 41

        if (isRequestAWhiteListed(methodType, url, token)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        try {
            JWTUtils.validateToken(token);
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            response.setStatus(401);
        }

    }

    private boolean isRequestAWhiteListed(String methodType, String url, String token){

        if (methodType.equals("post") && url.endsWith("users/login")) {
            return true;
        }
        if (methodType.equals("post") && url.endsWith("/users")) {
            return true;
        }

        if (methodType.equals("get") && url.endsWith("/coupons")) {
            return true;
        }
        if (methodType.equals("post") && url.endsWith("/coupons")) {
            return true;
        }

        //TODO THIS IS FOR DEMONSTARTION
        if(methodType.equals("get")|| methodType.equals("delete")|| methodType.equals("put")|| methodType.equals("post") ) {
            return true;
        }

        return false;
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
