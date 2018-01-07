package com.kingsoft.controller;

import com.kingsoft.service.IWebCrawlerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * 南书网爬虫
 * http://www.nansf.net/
 * Created by LIUYANMIN on 2018/1/5.
 */
@Controller
@RequestMapping("/crawler/")
public class WebCrawlerController {
    private static Logger logger = Logger.getLogger(WebCrawlerController.class);
    @Autowired
    private IWebCrawlerService webCrawlerService;

    @ResponseBody
    @RequestMapping("first")
    public Object getFirstCrawler(HttpServletRequest request) {
        HashMap<String, Object> map = webCrawlerService.getFirstCrawler();
        return map;
    }

    /**
     * 爬虫入口-总控制器
     * 注: 所有爬虫的入口，具体爬取内容根据参数来分
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("enter")
    public Object toCrawler(HttpServletRequest request) {
        HashMap<String, Object> returnMap = null;
        try {
            returnMap = webCrawlerService.webCrawler(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnMap;
    }

}
