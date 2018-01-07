package com.kingsoft.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by LUGUANGRUI on 2016/9/6.
 */

import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@Component
public class SimpleCORSFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        List<String> originList = new ArrayList<String>() {
            {
                add("http://wx.iciba.com");
                add("https://wx.iciba.com");
                add("http://wx.iciba.com:8080");
                add("https://wx.iciba.com:8080");
            }
        };
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        if (request.getHeader("Origin") != null && !request.getHeader("Origin").equals("")) {
            String[] origins = request.getHeader("Origin").split("/");
            if(origins.length>=3){
                String origin = origins[0]+"//"+origins[2];
                if(originList.contains(origin)){
                    response.setHeader("Access-Control-Allow-Origin", origin);
                }else {
                    response.setHeader("Access-Control-Allow-Origin", originList.get(0));
                }
            }else {
                response.setHeader("Access-Control-Allow-Origin", originList.get(0));
            }
        }else {
            response.setHeader("Access-Control-Allow-Origin", originList.get(0));
        }
        response.addHeader("Access-Control-Allow-Methods", "HEAD,GET,POST,PUT,DELETE,OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type,Origin,Accept");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        chain.doFilter(req, res);
    }
    public void init(FilterConfig filterConfig) {}
    public void destroy() {}
}
