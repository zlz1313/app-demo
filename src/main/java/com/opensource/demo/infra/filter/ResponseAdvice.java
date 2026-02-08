package com.opensource.demo.infra.filter;

import cn.hutool.json.JSONUtil;
import com.opensource.demo.common.enums.ErrorCodeEnum;
import com.opensource.demo.common.model.ApiThreadLocal;
import com.opensource.demo.common.model.Result;
import com.opensource.demo.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 統一接口調用日志
 */
@Slf4j
@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Result<?>> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return Result.class.isAssignableFrom(returnType.getParameterType()) ;
    }

    @Override
    public Result<?> beforeBodyWrite(Result<?> body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (null == body) {
            return null;
        }
        ApiThreadLocal.ApiInfoDto apiInfo = ApiThreadLocal.get();
        String res = ErrorCodeEnum.SUCCESS.getCode() == body.getCode() ? "success" : "fail";
        if (apiInfo == null) {
            log.info("op={}||res={}||result={}", request.getURI().getPath(), res, RequestUtils.filterSize(JSONUtil.toJsonStr(body)));
        } else {
            apiInfo.setRes(res);
            apiInfo.setBusinessCode(body.getCode());
            apiInfo.setCost(System.currentTimeMillis() - apiInfo.getRequestTime());
            log.info("{}||result={}", apiInfo, RequestUtils.filterSize(JSONUtil.toJsonStr(body)));
        }
        return body;
    }
}
