package com.itzhai.demo.pub.logger.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Component
public class LoggingContextFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;

        String traceId = Optional.ofNullable(request.getHeader("X-Trace-Id"))
                .orElse(UUID.randomUUID().toString());
        String userId = Optional.ofNullable(request.getHeader("X-User-Id"))
                .orElse("anonymous");
        String clientIp = Optional.ofNullable(request.getHeader("X-Forwarded-For"))
                .orElse(request.getRemoteAddr());

        MDC.put("traceId", traceId);
        MDC.put("userId", userId);
        MDC.put("clientIp", clientIp);
        try {
            chain.doFilter(req, res);
        } finally {
            MDC.clear();
        }
    }
}
