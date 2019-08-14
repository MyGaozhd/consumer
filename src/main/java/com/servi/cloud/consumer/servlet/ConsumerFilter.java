package com.servi.cloud.consumer.servlet;

import com.servi.cloud.consumer.util.log.ServiLogger;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebFilter(filterName = "consumerfilter", urlPatterns = "/consumer")
public class ConsumerFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("123");
        System.out.println(request.getRemoteHost());

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        ServiLogger.log(req.getRequestURI());
        HttpServletMapping mapping = req.getHttpServletMapping();
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
