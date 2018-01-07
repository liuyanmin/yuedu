package com.kingsoft.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingsoft.dao.IYellowCrawlerDao;
import com.kingsoft.pojo.YellowBookSection;
import com.kingsoft.pojo.YellowCrawlerError;
import com.kingsoft.service.IYellowCrawlerService;
import com.kingsoft.utils.NumberUtils;
import com.kingsoft.utils.RequestParamUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by LIUYANMIN on 2018/1/6.
 */
public class YellowCrawlerService implements IYellowCrawlerService {

    private static final Logger logger = Logger.getLogger(YellowCrawlerService.class);
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private IYellowCrawlerDao yellowCrawlerDao;

    /**
     * 全量爬取
     * @param request
     * @return
     */
    @Override
    public HashMap<String, Object> webCrawler(HttpServletRequest request) throws Exception {
        HashMap<String, Object> returnMap = new HashMap<>();
        int label = RequestParamUtils.getIntParameter(request, "label");
        int startPage = RequestParamUtils.getIntParameter(request, "startPage");// 起始页码
        String url = "https://www.343qq.com/htm/novellist1/";
        int totalPage = 1;// 总页数
        switch (label) {
            case 1:// 情感小说
                url = "https://www.343qq.com/htm/novellist1/";
                totalPage = 225;
                break;
            case 2:// 校园春色
                url = "https://www.343qq.com/htm/novellist2/";
                totalPage = 195;
                break;
            case 3:// 人妻女友
                url = "https://www.343qq.com/htm/novellist10/";
                totalPage = 251;
                break;
            case 4:// 武侠古典
                url = "https://www.343qq.com/htm/novellist4/";
                totalPage = 188;
                break;
            case 5:// 家庭乱伦
                url = "https://www.343qq.com/htm/novellist5/";
                totalPage = 234;
                break;
            case 6:// 另类小说
                url = "https://www.343qq.com/htm/novellist6/";
                totalPage = 165;
                break;
            case 7:// 性爱技巧
                url = "https://www.343qq.com/htm/novellist8/";
                totalPage = 171;
                break;
            case 8:// 情色笑话
                url = "https://www.343qq.com/htm/novellist9/";
                totalPage = 146;
                break;
            default:
                break;
        }
        returnMap = crawler(request, url, startPage, totalPage);
        return returnMap;
    }

    /**
     * 修复数据库中的异常数据
     * @param request
     * @return
     */
    @Override
    public HashMap<String, Object> updateData(HttpServletRequest request) throws Exception {
        HashMap<String, Object> returnMap = new HashMap<>();
        List<YellowBookSection> bookSections = yellowCrawlerDao.getExceptionData(1000);
        List<YellowBookSection> updateSections = new ArrayList<>();// 待修复的数据
        for (YellowBookSection section : bookSections) {
            if (StringUtils.isBlank(section.getSectionContent())) {
                updateSections.add(section);
                continue;
            }
            List<String> contentList = objectMapper.readValue(section.getSectionContent(), new TypeReference<List<String>>(){});
            if (contentList == null || contentList.size() < 2) {
                updateSections.add(section);
            }
        }
        for (YellowBookSection section : updateSections) {
            logger.info("正在修复id=" + section.getId() + ", 链接: " + section.getSectionUrl());
            System.out.println("正在修复id=" + section.getId() + ", 链接: " + section.getSectionUrl());
            // 爬取内容
            Document doc = Jsoup.connect(section.getSectionUrl()).get();
            Elements pElements = doc.body().select("div.pic_text").get(0).select("div.content").get(0).select("p");
            List<String> contentList = new ArrayList<>();
            for (Element p : pElements) {
                List<Node> nodes = p.childNodes();// 内容节点
                for (Node node : nodes) {
                    if (node instanceof TextNode) {
                        String text = ((TextNode) node).text();
                        if (StringUtils.isNotBlank(text.trim())) {
                            contentList.add(text.trim());
                        }
                    }
                }
            }
            String content = objectMapper.writeValueAsString(contentList);
            int totalCount = NumberUtils.chineseWordCount(content);// 总字数
            section.setSectionContent(content);
            section.setWords(totalCount);
            yellowCrawlerDao.updateSection(section);
        }

        returnMap.put("status", 1);
        returnMap.put("message", "success");
        return returnMap;
    }

    /**
     * 爬虫核心(全量跑)
     * @param request
     * @param url
     * @return
     */
    private HashMap<String, Object> crawler(HttpServletRequest request, String url, int startPage, int totalPage) throws Exception {
        String baseUrl = "https://www.343qq.com";
        int label = RequestParamUtils.getIntParameter(request, "label");
        HashMap<String, Object> returnMap = new HashMap<>();
        for (int i = startPage;i <= totalPage; i++) {
            String pageUrl = url + i + ".htm";
            logger.info("爬取第" + i + "页，链接: " + pageUrl);
            System.out.println("爬取第" + i + "页，链接: " + pageUrl);

            /************************* for循环3次是为了防止IO异常导致程序停止运行 ********************/
            Document document = null;
            for (int n = 0;n < 3; n++) {
                try {
                    document = Jsoup.connect(pageUrl).get();
                    if (document != null) break;
                } catch (Exception e) {
                    logger.error("打开目录列表失败，错误: " + e.getMessage());
                }
            }

            Element div = document.body().select("div.channel").get(0);
            Elements liList = div.select("li");// 找出所有li标签
            for (int j = 0; j < liList.size(); j++) {
                String publishDate = liList.get(j).select("span").get(0).text();
                Element a = liList.get(j).select("a").get(0);
                String sectionUrl = a.attr("href");
                String title = "无标题";
                if (a.childNodeSize() > 1) {
                    title = a.childNodes().get(1).toString();
                }

                // 爬取内容
                String contentUrl = baseUrl+sectionUrl;
                /************************* for循环3次是为了防止IO异常导致程序停止运行 ********************/
                Document doc = null;
                for (int k = 0; k < 3; k++) {
                    try {
                        doc = Jsoup.connect(contentUrl).get();
                        if (doc != null) break;
                    } catch (Exception e) {
                        logger.error("获取小说内容失败，错误: " + e.getMessage());
                    }
                }

                Elements pElements = doc.body().select("div.pic_text").get(0).select("div.content").get(0).select("p");
                List<String> contentList = new ArrayList<>();
                for (Element p : pElements) {
                    List<Node> nodes = p.childNodes();// 内容节点
                    for (Node node : nodes) {
                        if (node instanceof TextNode) {
                            String text = ((TextNode) node).text();
                            if (StringUtils.isNotBlank(text.trim())) {
                                contentList.add(text.trim());
                            }
                        }
                    }
                }
                String content = objectMapper.writeValueAsString(contentList);
                if (StringUtils.isBlank(content)) {// 爬取异常
                    YellowCrawlerError error = new YellowCrawlerError();
                    error.setPublishDate(publishDate);
                    error.setUrl(contentUrl);
                    yellowCrawlerDao.saveYellowCrawlerError(error);
                }
                int totalCount = NumberUtils.chineseWordCount(content);// 总字数
                YellowBookSection yellowBookSection = new YellowBookSection();
                yellowBookSection.setPublishDate(publishDate);
                yellowBookSection.setSectionTitle(title);
                yellowBookSection.setSectionUrl(contentUrl);
                yellowBookSection.setLabel(label);
                yellowBookSection.setSectionContent(content);
                yellowBookSection.setWords(totalCount);
                yellowCrawlerDao.saveYellowBookSection(yellowBookSection);
            }
        }
        return returnMap;
    }

}
