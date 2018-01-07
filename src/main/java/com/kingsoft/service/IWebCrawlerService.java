package com.kingsoft.service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by LIUYANMIN on 2018/1/5.
 */
public interface IWebCrawlerService {

    HashMap<String, Object> getFirstCrawler();

    HashMap<String, Object> webCrawler(HttpServletRequest request) throws Exception;
}
