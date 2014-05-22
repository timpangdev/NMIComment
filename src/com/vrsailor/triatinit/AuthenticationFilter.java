package com.vrsailor.triatinit;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import org.apache.log4j.Logger;
 
public class AuthenticationFilter implements Filter {
 
    private Logger logger = Logger.getLogger(AuthenticationFilter.class);
     
    public void init(FilterConfig fConfig) throws ServletException {
        logger.info("AuthenticationFilter initialized");
    }
     
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
 
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
         
        String uri = req.getRequestURI();
        logger.info("Requested Resource::"+uri);
         
        HttpSession session = req.getSession(false);
         
        if(session == null && !(uri.endsWith("jsp"))){
            logger.error("Unauthorized access request");
            res.sendRedirect("index.jsp");
        }else{
            // pass the request along the filter chain
            chain.doFilter(request, response);
        }
    }
 
    public void destroy() {
        //close any resources here
    }
}
