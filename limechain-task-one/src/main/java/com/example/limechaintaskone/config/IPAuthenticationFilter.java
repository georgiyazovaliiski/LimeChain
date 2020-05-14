package com.example.limechaintaskone.config;

import com.example.limechaintaskone.responses.Status;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class IPAuthenticationFilter extends GenericFilterBean {
    private static Map<String,Accessor> IPs = new HashMap<>();

    @Override
    public void doFilter(ServletRequest request,
                                    ServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;

        String header = req.getRemoteAddr();

        res.setHeader("x-ratelimit-limit",Long.toString(Accessor.RATELIMIT));

        if(!IPs.containsKey(header)){
            IPs.put(header,new Accessor(header));
        }

        Accessor accessed = IPs.get(header);

        if(accessed.lockOutPeriodOver()){
            accessed.persistRequest();
            res.setHeader("x-ratelimit-remaining",Long.toString(accessed.getRequestsLeft()));
            chain.doFilter(req, res);
        }else {
            Status.TooManyRequests(res);
        }
    }
}
