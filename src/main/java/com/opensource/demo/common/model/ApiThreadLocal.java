package com.opensource.demo.common.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * 上下文，当前接口的信息
 */
public class ApiThreadLocal {

    private static ThreadLocal<ApiInfoDto> apiInfo = new ThreadLocal<>();

    /**
     * 上下文存入接口信息
     *
     * @param api
     */
    public static void set(ApiInfoDto api) {
        apiInfo.set(api);
    }

    /**
     * 获取接口信息
     *
     * @return
     */
    public static ApiInfoDto get() {
        return apiInfo.get();
    }

    /**
     * 清空上下文
     */
    public static void remove() {
        apiInfo.remove();
    }

    @Getter
    @Setter
    public static class ApiInfoDto {
        private long requestTime;
        private String op;
        private String tag;
        private String method;
        private String orgmsg;
        private String level;
        private String res;
        private int businessCode;
        private long cost;

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("op=").append(op).append("||").append("res=").append(res);
            sb.append("||method=").append(method).append("||lev=").append(level);
            sb.append("||businessCode=").append(businessCode).append("||cost=").append(cost);
            if (StringUtils.isNotEmpty(orgmsg)) {
                sb.append("||orgmsg=").append(orgmsg);
            }
            return sb.toString();
        }
    }
}
