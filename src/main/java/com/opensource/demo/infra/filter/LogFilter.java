package com.opensource.demo.infra.filter;

import cn.hutool.json.JSONUtil;
import com.opensource.demo.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = request.getRequestURI();
        String method = request.getMethod();
        String params = "{}";
        try {
            params = JSONUtil.toJsonStr(extractParams(request));
        } catch (Exception ignore) {}

        long nanoTime = System.nanoTime();
        filterChain.doFilter(servletRequest, servletResponse);
        double costTime = (System.nanoTime() - nanoTime) * 1d / 1000000;
        log.info("op={}||uri={}||param={}||status={}||cost={}ms", method, path, params, response.getStatus(), costTime);
    }

    private Map<String, Object> extractParams(HttpServletRequest request) {
        String method = request.getMethod();
        Map<String, Object> paramsMap = new HashMap<>();
        if (HttpMethod.POST.name().equals(method) || HttpMethod.PUT.name().equals(method)) {
            if (request instanceof BaseRequestWrapper) {
                paramsMap.putAll(RequestUtils.getBodyMap(request));
            }
        }
        Map<String, String[]> paramsInRequest = request.getParameterMap();
        for (String key : paramsInRequest.keySet()) {
            if ("sign".equals(key) || "appkey".equals(key) || "timestamp".equals(key)) {
                continue;
            }
            String[] values = paramsInRequest.get(key);
            if (values.length == 1) {
                paramsMap.put(key, values[0]);
            } else {
                paramsMap.put(key, Arrays.asList(values));
            }
        }
        return paramsMap;
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
