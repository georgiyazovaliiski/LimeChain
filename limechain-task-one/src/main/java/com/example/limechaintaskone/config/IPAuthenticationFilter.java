package com.example.limechaintaskone.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class IPAuthenticationFilter extends BasicAuthenticationFilter {
    private static Map<String,Accessor> IPs = new HashMap<>();

    public IPAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        super.onUnsuccessfulAuthentication(request, response, failed);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
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
            res.setStatus(429);
            res.setHeader("x-ratelimit-remaining", Long.toString(0l));
            res.setHeader("Location", "/error");
            res.sendError(429, "Too many requests, try again after " + accessed.getLockOutTime());
        }
    }
}
