package com.example.limechaintaskone.responses;

import com.example.limechaintaskone.config.Accessor;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Status {
    public static void TooManyRequests(HttpServletResponse res) throws IOException {
        res.setStatus(429);
        res.setHeader("x-ratelimit-remaining", Long.toString(0l));
        res.setHeader("Location", "/error");
        res.sendError(429, "Too many requests, try again after " + Accessor.LOCKOUTTIME);
    }
}
