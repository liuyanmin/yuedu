package com.kingsoft.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by LIUYANMIN on 2018/1/6.
 */
public class RequestParamUtils {

    /**
     * 获取Int类型的请求参数
     * @param request
     * @param param
     * @return
     */
    public static int getIntParameter(HttpServletRequest request, String param) {
        return Integer.valueOf(request.getParameter(param));
    }
}
