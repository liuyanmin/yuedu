package com.kingsoft.service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by LIUYANMIN on 2018/1/6.
 */
public interface IYellowCrawlerService {

    // 总入口
    HashMap<String, Object> webCrawler(HttpServletRequest request) throws Exception;

    // 修复数据
    public HashMap<String, Object> updateData(HttpServletRequest request) throws Exception;
}
