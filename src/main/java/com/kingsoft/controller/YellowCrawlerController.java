package com.kingsoft.controller;

import com.kingsoft.service.IYellowCrawlerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * 黄网爬虫
 * https://www.aaa62.com
 * Created by LIUYANMIN on 2018/1/6.
 */
@Controller
@RequestMapping("/yellow/")
public class YellowCrawlerController {
    private static final Logger logger = Logger.getLogger(YellowCrawlerController.class);
    @Autowired
    private IYellowCrawlerService yellowCrawlerService;

    /**
     * 爬虫总入口
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("enter")
    public Object enter(HttpServletRequest request) {
        HashMap<String, Object> returnMap = null;
        try {
            returnMap = yellowCrawlerService.webCrawler(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnMap;
    }

    /**
     * 修复数据
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("update")
    public Object updateData(HttpServletRequest request) {
        HashMap<String, Object> returnMap = null;
        try {
            returnMap = yellowCrawlerService.updateData(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnMap;
    }
}
