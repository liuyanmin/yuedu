package com.kingsoft.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingsoft.dao.IWebCrawlerDao;
import com.kingsoft.pojo.Book;
import com.kingsoft.pojo.BookChapter;
import com.kingsoft.pojo.BookSection;
import com.kingsoft.pojo.CrawlerBookType;
import com.kingsoft.service.IWebCrawlerService;
import com.kingsoft.utils.NumberUtils;
import com.kingsoft.utils.RequestParamUtils;
import com.kingsoft.utils.TimeUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by LIUYANMIN on 2018/1/5.
 */
public class WebCrawlerService implements IWebCrawlerService {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private IWebCrawlerDao webCrawlerDao;

    @Override
    public HashMap<String, Object> getFirstCrawler() {
        Book book = webCrawlerDao.getBookById(1);
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "crawler");
        map.put("book", book);
        map.put("status", 1);
        return map;
    }

    @Override
    public HashMap<String, Object> webCrawler(HttpServletRequest request) throws  Exception {
        HashMap<String, Object> returnMap = new HashMap<>();
        int type = RequestParamUtils.getIntParameter(request, "type");
        switch (type) {
            case 1:// 东北往事之黑道风云20年(1-6部)
                returnMap = crawlerDBWS(request, type);
                break;
            case 2:// 黑道悲情(1-2部)
                returnMap = crawlerHDBQ(request, type);
                break;
            case 3:// 无疆
                returnMap = crawlerWJ(request, type);
                break;
            case 4:// 斗破苍穹
                returnMap = crawlerDPCQ(request, type);
                break;
            case 5:// 全职高手
                returnMap = crawlerQZGS(request, type);
                break;
            case 6:// 侯海洋基层风云
                returnMap = crawlerHHYJCFY(request, type);
                break;
            case 7:// 静州往事
                returnMap = crawlerJZWS(request, type);
                break;
            case 8:// 锦衣夜行
                returnMap = crawlerJYYX(request, type);
                break;
            case 9:// 在难搞的日志笑出声来
                returnMap = crawlerZNGDRZXCSL(request, type);
                break;
            case 10:// 别样的江湖
                returnMap = crawlerBYDJH(request, type);
                break;
            case 11:// 亵渎
                returnMap = crawlerSD(request, type);
                break;
            case 12:// 莽荒纪
                returnMap = crawlerMHJ(request, type);
                break;
            case 13:// 圣王
                returnMap = crawlerSW(request, type);
                break;
            case 14:// 侯卫东官场笔记
                returnMap = crawlerHWDGCBJ(request, type);
                break;
            case 15:// 武极天下
                returnMap = crawlerWJTX(request, type);
                break;
            case 16:// 医道官途
                returnMap = crawlerYDGT(request, type);
                break;
            case 17:// 遮天
                returnMap = crawlerZT(request, type);
                break;
            default:
                break;
        }
        return returnMap;
    }

    /**
     * 《遮天》
     * URL: http://www.nansf.net/yuedu_133/
     * @date 2018-01-06 19:24:00
     * @param request
     * @param type
     * @return
     * @throws Exception
     */
    private HashMap<String, Object> crawlerZT(HttpServletRequest request, int type) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", "http://www.nansf.net/yuedu_133/");
        params.put("type", type);
        params.put("bookName", "遮天");
        params.put("authorName", "辰东");
        params.put("description", "冰冷与黑暗并存的宇宙深处，九具庞大的龙尸拉着一口青铜古棺，亘古长存。这是太空探测器在枯寂的宇宙中捕捉到的一幅极其震撼的画面。九龙拉棺，究竟是回到了上古，还是来到了星空的彼岸？一个浩...");
        params.put("label", "12");
        params.put("isChapter", true);
        return crawler(request,params);
    }

    /**
     * 《医道官途》
     * URL: http://www.nansf.net/yuedu_144/
     * @date 2018-01-06 19:22:00
     * @param request
     * @param type
     * @return
     * @throws Exception
     */
    private HashMap<String, Object> crawlerYDGT(HttpServletRequest request, int type) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", "http://www.nansf.net/yuedu_144/");
        params.put("type", type);
        params.put("bookName", "医道官途");
        params.put("authorName", "石章鱼");
        params.put("description", "隋末第一圣手张一针，医术高超，武功已臻化境的他先是喝下隋炀帝的毒酒，然后被一千名御林军乱箭攒心，起因却是他不计报酬的救活了隋炀帝难产的贵妃，他满怀怨气穿越时空，来到了现代，他特立独...");
        params.put("label", "13");
        params.put("isChapter", true);
        return crawler(request,params);
    }

    /**
     * 《武极天下》
     * URL: http://www.nansf.net/yuedu_27/
     * @date 2018-01-06 19:16:00
     * @param request
     * @param type
     * @return
     * @throws Exception
     */
    private HashMap<String, Object> crawlerWJTX(HttpServletRequest request, int type) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", "http://www.nansf.net/yuedu_27/");
        params.put("type", type);
        params.put("bookName", "武极天下");
        params.put("authorName", "蚕茧里的牛");
        params.put("description", "一个梦想进入武府圣地的普通少年，立志追求极致武学。然而面对竞争激烈的考核，又有世家子弟的借势压人，小小平凡少年如何立足？宗门传承严格保密，核心功法概不外传，在功法传承如此难得天衍大...");
        params.put("label", "2");
        params.put("isChapter", true);
        return crawler(request,params);
    }

    /**
     * 《侯卫东官场笔记》
     * URL: http://www.nansf.net/yuedu_4083/
     * @date 2018-01-06 19:16:00
     * @param request
     * @param type
     * @return
     * @throws Exception
     */
    private HashMap<String, Object> crawlerHWDGCBJ(HttpServletRequest request, int type) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", "http://www.nansf.net/yuedu_4083/");
        params.put("type", type);
        params.put("bookName", "侯卫东官场笔记");
        params.put("authorName", "小桥老树");
        params.put("description", "原名《官路风流》。一部逐层讲透村、镇、县、市、省官场现状的自传体小说！被网上誉为“中国官场通俗教科书”。...");
        params.put("label", "1");
        params.put("isChapter", true);
        return crawler(request,params);
    }

    /**
     * 《圣王》
     * URL: http://www.nansf.net/yuedu_681/
     * @date 2018-01-06 19:14:00
     * @param request
     * @param type
     * @return
     * @throws Exception
     */
    private HashMap<String, Object> crawlerSW(HttpServletRequest request, int type) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", "http://www.nansf.net/yuedu_681/");
        params.put("type", type);
        params.put("bookName", "圣王");
        params.put("authorName", "梦入神机");
        params.put("description", "圣者以脊梁撑起天堂中的诸神，王者以力量镇压地狱中的群魔，天地之间，唯有圣王...");
        params.put("label", "2");
        params.put("isChapter", true);
        return crawler(request,params);
    }

    /**
     * 《莽荒纪》
     * URL: http://www.nansf.net/yuedu_132/
     * @date 2018-01-06 19:10:00
     * @param request
     * @param type
     * @return
     * @throws Exception
     */
    private HashMap<String, Object> crawlerMHJ(HttpServletRequest request, int type) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", "http://www.nansf.net/yuedu_132/");
        params.put("type", type);
        params.put("bookName", "莽荒纪");
        params.put("authorName", "我吃西红柿");
        params.put("description", "纪宁死后来到阴曹地府，经判官审前生判来世，投胎到了部族纪氏。这里，有夸父逐日……有后羿射金乌……更有为了逍遥长生，历三灾九劫，纵死无悔的无数修仙者……纪宁也成为了一名修仙者，开始了...");
        params.put("label", "12");
        params.put("isChapter", true);
        return crawler(request,params);
    }

    /**
     * 《亵渎》
     * URL: http://www.nansf.net/yuedu_673/
     * @date 2018-01-06 19:05:00
     * @param request
     * @param type
     * @return
     * @throws Exception
     */
    private HashMap<String, Object> crawlerSD(HttpServletRequest request, int type) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", "http://www.nansf.net/yuedu_673/");
        params.put("type", type);
        params.put("bookName", "亵渎");
        params.put("authorName", "烟雨江南");
        params.put("description", "睡觉睡到自然醒，数钱数到手抽筋，这就是罗格的幸福生活...");
        params.put("label", "2");
        params.put("isChapter", true);
        return crawler(request,params);
    }

    /**
     * 《别样的江湖》
     * URL: http://www.nansf.net/yuedu_2928/
     * @date 2018-01-06 19:03:00
     * @param request
     * @param type
     * @return
     * @throws Exception
     */
    private HashMap<String, Object> crawlerBYDJH(HttpServletRequest request, int type) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", "http://www.nansf.net/yuedu_2928/");
        params.put("type", type);
        params.put("bookName", "别样的江湖");
        params.put("authorName", "孔二狗");
        params.put("description", "本文的主人公叫肖开元，他毕业于上海名校，职业是咨询顾问，高薪白领。但被2006-2007年疯狂的股市冲昏了头脑，对金钱过度的追求，最后误入赌海，负债百万。但肖开元没有沉沦，他顽强地振作了起来...");
        params.put("label", "1");
        params.put("isChapter", false);
        return crawler(request,params);
    }

    /**
     * 《在难搞的日志笑出声来》
     * URL: http://www.nansf.net/yuedu_2926/
     * @date 2018-01-06 18:59:00
     * @param request
     * @param type
     * @return
     * @throws Exception
     */
    private HashMap<String, Object> crawlerZNGDRZXCSL(HttpServletRequest request, int type) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", "http://www.nansf.net/yuedu_2926/");
        params.put("type", type);
        params.put("bookName", "在难搞的日子笑出声来");
        params.put("authorName", "大鹏");
        params.put("description", "大鹏全面讲述奋斗与成长，命运转折的关键时刻，无一不记录在此。从家乡小城集安到塘沽，他曾在码头的煤堆里工作，看着天上和地下都是一片漆黑，他问自己，就算是脚下的煤，都有被装上轮船运走的...");
        params.put("label", "1");
        params.put("isChapter", true);
        return crawler(request,params);
    }

    /**
     * 《锦衣夜行》
     * URL: http://www.nansf.net/yuedu_381/
     * @date 2018-01-06 18:51:00
     * @param request
     * @param type
     * @return
     * @throws Exception
     */
    private HashMap<String, Object> crawlerJYYX(HttpServletRequest request, int type) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", "http://www.nansf.net/yuedu_381/");
        params.put("type", type);
        params.put("bookName", "锦衣夜行");
        params.put("authorName", "月关");
        params.put("description", "靖难削藩，迁都修典，五征蒙古，七下南洋，我无处不在，却无人知道我在，乾坤入袖，锦衣夜行，低调！低调才是王道。曲折的生命传承，延续的被动命运，成就了他的崛起和反攻。看似没有主动权的冒...");
        params.put("label", "13");
        params.put("isChapter", true);
        return crawler(request,params);
    }

    /**
     * 《静州往事》
     * URL: www.nansf.net/yuedu_3665/
     * @date 2018-01-06 18:39:00
     * @param request
     * @param type
     * @return
     * @throws Exception
     */
    private HashMap<String, Object> crawlerJZWS(HttpServletRequest request, int type) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", "http://www.nansf.net/yuedu_3665/");
        params.put("type", type);
        params.put("bookName", "静州往事");
        params.put("authorName", "小桥老树");
        params.put("description", "这是《侯海洋基层风云》网络版，一个青年人奋斗和成长的热血故事，很好看。...");
        params.put("label", "1");
        params.put("isChapter", false);
        return crawler(request,params);
    }

    /**
     * 《侯海洋基层风云》
     * URL: http://www.nansf.net/yuedu_906/
     * @date 2018-01-06 18:25:00
     * @param request
     * @param type
     * @return
     */
    private HashMap<String, Object> crawlerHHYJCFY(HttpServletRequest request, int type) throws Exception {
        HashMap<String, Object> returnMap = new HashMap<>();
        String url = "http://www.nansf.net/yuedu_906/";

        // 检查该书是否已存在
        if (bookIsExists(type)) {
            returnMap.put("message", "该书已存在");
            returnMap.put("status", 2);
            return returnMap;
        }

        // 爬取1-5部所有章节的url
        Document doc = Jsoup.connect(url).get();// 获取页面DOM文档
        Element element = doc.body().select("div.book_list").get(0);// 筛选出想要的元素，类似jquery选择器
        Elements liList = element.select("li");// 找出所有li标签
        String subTitle = "";// 副标题
        int bookId = 0;// 书ID
        int chapterId = 0;// 章节ID
        String bookName = "侯海洋基层风云";
        int chapterIndex = 0;// 每部的章节索引
        int sectionIndex = 0;// 每章节的段落索引
        int countWords = 0;

        // 保存书籍信息
        Book book = new Book();
        book.setAuthorName("小桥老树");
        book.setChannel(1);
        book.setDescription("小桥老树最新力作《侯海洋基层风云》，侯海洋和侯卫东走的是完全不同的人生路线，相比较之下，侯海洋更像一个倒霉蛋，人生之路充满了跌宕起伏...");
        book.setLabel("1");
        book.setPublishDate(TimeUtils.formatDateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        book.setPublisher("lym");
        book.setSubTitle(subTitle);
        book.setTitle(bookName);
        book.setWords(0);
        book.setType(0);
        book.setState(0);
        book.setSeriesId(1);
        book.setUpdateState(1);
        book.setReadCount(0);
        webCrawlerDao.saveBook(book);
        bookId = book.getId();

        for (Element li : liList) {
            String style = li.attr("style");
            if (StringUtils.isNotBlank(style)) {// 副标题
                Element b = li.selectFirst("b");
                subTitle = b.text();

                BookChapter chapter = new BookChapter();
                chapter.setBookId(bookId);
                chapter.setChapterTitle(subTitle);
                chapter.setPosition(chapterIndex);
                webCrawlerDao.saveBookChapter(chapter);
                chapterId = chapter.getId();
                sectionIndex = 0;
                chapterIndex++;
            } else {
                Element a = li.selectFirst("a");
                String sectionURL = a.attr("href");
                String sectionTitle = a.attr("title");

                // 爬取每一章节的内容
                Document sectionDoc = Jsoup.connect(sectionURL).get();
                Element contentDiv = sectionDoc.body().selectFirst("div.contentbox");// 内容原文div
                List<Node> nodes = contentDiv.childNodes();
                List<String> sectionContentList = new ArrayList<>();// 存储章节中每段原文
                for (Node node : nodes) {
                    if (node instanceof TextNode) {
                        String text = ((TextNode) node).text().trim();
                        if (text.indexOf("南书房") == 0) {
                            continue;
                        }
                        if (text.indexOf("请阅读") == 0) {
                            break;
                        }
                        if (StringUtils.isNotBlank(text)) {
                            sectionContentList.add(text);
                        }
                    }
                }
                String sectionContent = objectMapper.writeValueAsString(sectionContentList);
                int count = NumberUtils.chineseWordCount(sectionContent);
                BookSection section = new BookSection();
                section.setBookId(bookId);
                section.setChapterId(chapterId);
                section.setPosition(sectionIndex);
                section.setSectionContent(sectionContent);
                section.setSectionTitle(sectionTitle);
                section.setSectionUrl(sectionURL);
                section.setWords(count);
                webCrawlerDao.saveBookSection(section);

                countWords += count;
                sectionIndex++;
            }
        }

        if (bookId != 0) {
            webCrawlerDao.updateWordCount(bookId, countWords);
        }

        // 插入tb_crwler_book_type表
        CrawlerBookType crawlerBookType = new CrawlerBookType();
        crawlerBookType.setType(type);
        webCrawlerDao.saveCrawlerBookType(crawlerBookType);

        returnMap.put("message", "success");
        returnMap.put("status", 1);
        return returnMap;
    }

    /**
     * 《全职高手》
     * URL: http://www.nansf.net/yuedu_466/
     * @date 2018-01-06 18:12:00
     * @param request
     * @param type
     * @return
     */
    private HashMap<String, Object> crawlerQZGS(HttpServletRequest request, int type) throws Exception {
        HashMap<String, Object> returnMap = new HashMap<>();
        String url = "http://www.nansf.net/yuedu_466/";

        // 检查该书是否已存在
        if (bookIsExists(type)) {
            returnMap.put("message", "该书已存在");
            returnMap.put("status", 2);
            return returnMap;
        }

        // 爬取1-5部所有章节的url
        Document doc = Jsoup.connect(url).get();// 获取页面DOM文档
        Element element = doc.body().select("div.book_list").get(0);// 筛选出想要的元素，类似jquery选择器
        Elements liList = element.select("li");// 找出所有li标签
        String subTitle = "";// 副标题
        int bookId = 0;// 书ID
        int chapterId = 0;// 章节ID
        String bookName = "全职高手";
        int chapterIndex = 0;// 每部的章节索引
        int sectionIndex = 0;// 每章节的段落索引
        int countWords = 0;

        // 保存书籍信息
        Book book = new Book();
        book.setAuthorName("蝴蝶蓝");
        book.setChannel(1);
        book.setDescription("网游荣耀中被誉为教科书级别的顶尖高手，因为种种原因遭到俱乐部的驱逐，离开职业圈的他寄身于一家网吧成了一个小小的网管，但是，拥有十年游戏经验的他，在荣耀新开的第十区重新投入了游戏，带...");
        book.setLabel("6");
        book.setPublishDate(TimeUtils.formatDateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        book.setPublisher("lym");
        book.setSubTitle(subTitle);
        book.setTitle(bookName);
        book.setWords(0);
        book.setType(0);
        book.setState(0);
        book.setSeriesId(1);
        book.setUpdateState(1);
        book.setReadCount(0);
        webCrawlerDao.saveBook(book);
        bookId = book.getId();

        for (Element li : liList) {
            String style = li.attr("style");
            if (StringUtils.isNotBlank(style)) {// 副标题
                Element b = li.selectFirst("b");
                subTitle = b.text();

                BookChapter chapter = new BookChapter();
                chapter.setBookId(bookId);
                chapter.setChapterTitle(subTitle);
                chapter.setPosition(chapterIndex);
                webCrawlerDao.saveBookChapter(chapter);
                chapterId = chapter.getId();
                sectionIndex = 0;
                chapterIndex++;
            } else {
                Element a = li.selectFirst("a");
                String sectionURL = a.attr("href");
                String sectionTitle = a.attr("title");

                // 爬取每一章节的内容
                Document sectionDoc = Jsoup.connect(sectionURL).get();
                Element contentDiv = sectionDoc.body().selectFirst("div.contentbox");// 内容原文div
                List<Node> nodes = contentDiv.childNodes();
                List<String> sectionContentList = new ArrayList<>();// 存储章节中每段原文
                for (Node node : nodes) {
                    if (node instanceof TextNode) {
                        String text = ((TextNode) node).text().trim();
                        if (text.indexOf("南书房") == 0) {
                            continue;
                        }
                        if (text.indexOf("请阅读") == 0) {
                            break;
                        }
                        if (StringUtils.isNotBlank(text)) {
                            sectionContentList.add(text);
                        }
                    }
                }
                String sectionContent = objectMapper.writeValueAsString(sectionContentList);
                int count = NumberUtils.chineseWordCount(sectionContent);
                BookSection section = new BookSection();
                section.setBookId(bookId);
                section.setChapterId(chapterId);
                section.setPosition(sectionIndex);
                section.setSectionContent(sectionContent);
                section.setSectionTitle(sectionTitle);
                section.setSectionUrl(sectionURL);
                section.setWords(count);
                webCrawlerDao.saveBookSection(section);

                countWords += count;
                sectionIndex++;
            }
        }

        if (bookId != 0) {
            webCrawlerDao.updateWordCount(bookId, countWords);
        }

        // 插入tb_crwler_book_type表
        CrawlerBookType crawlerBookType = new CrawlerBookType();
        crawlerBookType.setType(type);
        webCrawlerDao.saveCrawlerBookType(crawlerBookType);

        returnMap.put("message", "success");
        returnMap.put("status", 1);
        return returnMap;
    }

    /**
     * 《斗破苍穹》
     * URL: http://www.nansf.net/yuedu_602/
     * @date 2018-01-06 18:03:00
     * @param request
     * @param type
     * @return
     */
    private HashMap<String, Object> crawlerDPCQ(HttpServletRequest request, int type) throws Exception {
        HashMap<String, Object> returnMap = new HashMap<>();
        String url = "http://www.nansf.net/yuedu_602/";

        // 检查该书是否已存在
        if (bookIsExists(type)) {
            returnMap.put("message", "该书已存在");
            returnMap.put("status", 2);
            return returnMap;
        }

        // 爬取1-5部所有章节的url
        Document doc = Jsoup.connect(url).get();// 获取页面DOM文档
        Element element = doc.body().select("div.book_list").get(0);// 筛选出想要的元素，类似jquery选择器
        Elements liList = element.select("li");// 找出所有li标签
        String subTitle = "";// 副标题
        int bookId = 0;// 书ID
        int chapterId = 0;// 章节ID
        String bookName = "斗破苍穹";
        int chapterIndex = 0;// 每部的章节索引
        int sectionIndex = 0;// 每章节的段落索引
        int countWords = 0;

        // 保存书籍信息
        Book book = new Book();
        book.setAuthorName("天蚕土豆");
        book.setChannel(1);
        book.setDescription("这里是属于斗气的世界，没有花俏艳丽的魔法，有的，仅仅是繁衍到巅峰的斗气！等级制度：斗者，斗师，大斗师，斗灵，斗王，斗皇，斗宗，斗尊，斗圣，斗帝。...");
        book.setLabel("2");
        book.setPublishDate(TimeUtils.formatDateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        book.setPublisher("lym");
        book.setSubTitle(subTitle);
        book.setTitle(bookName);
        book.setWords(0);
        book.setType(0);
        book.setState(0);
        book.setSeriesId(1);
        book.setUpdateState(1);
        book.setReadCount(0);
        webCrawlerDao.saveBook(book);
        bookId = book.getId();

        for (Element li : liList) {
            String style = li.attr("style");
            if (StringUtils.isNotBlank(style)) {// 副标题
                Element b = li.selectFirst("b");
                subTitle = b.text();

                BookChapter chapter = new BookChapter();
                chapter.setBookId(bookId);
                chapter.setChapterTitle(subTitle);
                chapter.setPosition(chapterIndex);
                webCrawlerDao.saveBookChapter(chapter);
                chapterId = chapter.getId();
                sectionIndex = 0;
                chapterIndex++;
            } else {
                Element a = li.selectFirst("a");
                String sectionURL = a.attr("href");
                String sectionTitle = a.attr("title");

                // 爬取每一章节的内容
                Document sectionDoc = Jsoup.connect(sectionURL).get();
                Element contentDiv = sectionDoc.body().selectFirst("div.contentbox");// 内容原文div
                List<Node> nodes = contentDiv.childNodes();
                List<String> sectionContentList = new ArrayList<>();// 存储章节中每段原文
                for (Node node : nodes) {
                    if (node instanceof TextNode) {
                        String text = ((TextNode) node).text().trim();
                        if (text.indexOf("南书房") == 0) {
                            continue;
                        }
                        if (text.indexOf("请阅读") == 0) {
                            break;
                        }
                        if (StringUtils.isNotBlank(text)) {
                            sectionContentList.add(text);
                        }
                    }
                }
                String sectionContent = objectMapper.writeValueAsString(sectionContentList);
                int count = NumberUtils.chineseWordCount(sectionContent);
                BookSection section = new BookSection();
                section.setBookId(bookId);
                section.setChapterId(chapterId);
                section.setPosition(sectionIndex);
                section.setSectionContent(sectionContent);
                section.setSectionTitle(sectionTitle);
                section.setSectionUrl(sectionURL);
                section.setWords(count);
                webCrawlerDao.saveBookSection(section);

                countWords += count;
                sectionIndex++;
            }
        }

        if (bookId != 0) {
            webCrawlerDao.updateWordCount(bookId, countWords);
        }

        // 插入tb_crwler_book_type表
        CrawlerBookType crawlerBookType = new CrawlerBookType();
        crawlerBookType.setType(type);
        webCrawlerDao.saveCrawlerBookType(crawlerBookType);

        returnMap.put("message", "success");
        returnMap.put("status", 1);
        return returnMap;
    }

    /**
     * 《无疆》连载中...
     * 已爬取到第二百七十一章 不能答应你
     * URL: http://www.nansf.net/yuedu_9468/
     * @date 2018-01-06 12:41:00
     * @param request
     * @return
     * @throws Exception
     */
    private HashMap<String, Object> crawlerWJ(HttpServletRequest request, int type) throws Exception {
        HashMap<String, Object> returnMap = new HashMap<>();
        String url = "http://www.nansf.net/yuedu_9468/";

        // 检查该书是否已存在
        if (bookIsExists(type)) {
            returnMap.put("message", "该书已存在");
            returnMap.put("status", 2);
            return returnMap;
        }

        Document doc = Jsoup.connect(url).get();// 获取页面DOM文档
        Element element = doc.body().select("div.book_list").get(0);// 筛选出想要的元素，类似jquery选择器
        Elements liList = element.select("li");// 找出所有li标签
        String subTitle = "";// 副标题
        int bookId = 0;// 书ID
        int chapterId = 0;// 章节ID
        String bookName = "无疆";
        int chapterIndex = 0;// 每部的章节索引
        int sectionIndex = 0;// 每章节的段落索引
        int countWords = 0;

        // 保存书籍信息
        Book book = new Book();
        book.setAuthorName("孔二狗");
        book.setChannel(1);
        book.setDescription("鹰击长空，鱼跃龙门，熊咆虎啸，万物皆有灵。末法之极，磁极轮转，世界变迁。曾经那个熟悉的世界，已经变得面目全非。　　当神话变成现实，当传说不再神秘，世界无疆，热血永恒。...");
        book.setLabel("12");
        book.setPublishDate(TimeUtils.formatDateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        book.setPublisher("lym");
        book.setSubTitle(subTitle);
        book.setTitle(bookName);
        book.setWords(0);
        book.setType(0);
        book.setState(0);
        book.setSeriesId(1);
        book.setUpdateState(2);
        book.setReadCount(0);
        webCrawlerDao.saveBook(book);
        bookId = book.getId();

        // 保存章节信息
        BookChapter chapter = new BookChapter();
        chapter.setBookId(bookId);
        chapter.setChapterTitle(subTitle);
        chapter.setPosition(chapterIndex);
        webCrawlerDao.saveBookChapter(chapter);
        chapterId = chapter.getId();

        for (Element li : liList) {
            Element a = li.selectFirst("a");
            String sectionURL = a.attr("href");
            String sectionTitle = a.attr("title");

            // 爬取每一章节的内容
            Document sectionDoc = Jsoup.connect(sectionURL).get();
            Element contentDiv = sectionDoc.body().selectFirst("div.contentbox");// 内容原文div
            List<Node> nodes = contentDiv.childNodes();
            List<String> sectionContentList = new ArrayList<>();// 存储章节中每段原文
            for (Node node : nodes) {
                if (node instanceof TextNode) {
                    String text = ((TextNode) node).text().trim();
                    if (text.indexOf("南书房") == 0) {
                        continue;
                    }
                    if (text.indexOf("请阅读") == 0) {
                        break;
                    }
                    if (StringUtils.isNotBlank(text)) {
                        sectionContentList.add(text);
                    }
                }
            }
            String sectionContent = objectMapper.writeValueAsString(sectionContentList);
            int count = NumberUtils.chineseWordCount(sectionContent);
            BookSection section = new BookSection();
            section.setBookId(bookId);
            section.setChapterId(chapterId);
            section.setPosition(sectionIndex);
            section.setSectionContent(sectionContent);
            section.setSectionTitle(sectionTitle);
            section.setSectionUrl(sectionURL);
            section.setWords(count);
            webCrawlerDao.saveBookSection(section);

            countWords += count;
            sectionIndex++;
        }

        if (bookId != 0) {
            webCrawlerDao.updateWordCount(bookId, countWords);
        }

        // 插入tb_crwler_book_type表
        CrawlerBookType crawlerBookType = new CrawlerBookType();
        crawlerBookType.setType(type);
        webCrawlerDao.saveCrawlerBookType(crawlerBookType);

        returnMap.put("message", "success");
        returnMap.put("status", 1);
        return returnMap;
    }

    /**
     * 《黑道悲情(1-2部)》
     * URL: http://www.nansf.net/yuedu_2924/  第一部
     *      http://www.nansf.net/yuedu_2925/  第二部
     * @date 2018-01-06 12:17:00
     * @param request
     * @return
     */
    private HashMap<String, Object> crawlerHDBQ(HttpServletRequest request, int type) throws Exception {
        HashMap<String, Object> returnMap = new HashMap<>();

        // 检查该书是否已存在
        if (bookIsExists(type)) {
            returnMap.put("message", "该书已存在");
            returnMap.put("status", 2);
            return returnMap;
        }

        String[] urls = {"http://www.nansf.net/yuedu_2924/", "http://www.nansf.net/yuedu_2925/"};
        int index = 1;// 小说第几部索引
        for (String url : urls) {
            // 爬取1-5部所有章节的url
            Document doc = Jsoup.connect(url).get();// 获取页面DOM文档
            Element element = doc.body().select("div.book_list").get(0);// 筛选出想要的元素，类似jquery选择器
            Elements liList = element.select("li");// 找出所有li标签
            String subTitle = "";// 副标题
            int bookId = 0;// 书ID
            int chapterId = 0;// 章节ID
            String bookName = "黑道悲情";
            int chapterIndex = 0;// 每部的章节索引
            int sectionIndex = 0;// 每章节的段落索引
            int countWords = 0;

            // 保存书籍信息
            Book book = new Book();
            book.setAuthorName("孔二狗");
            book.setChannel(1);
            if (index == 1) {
                book.setDescription("《东北往事：黑道风云20年》前传，讲述在1982年那个特殊的年代，刘海柱和东霸天等在江湖中迅速崛起，品尝着道义和爱情带给他们的酸甜苦辣。我市的刘海柱孤身一人，威震段家屯，一战成名。“黄老邪”不屑于冯朦胧的装腔作势，群架斗殴后，引来的确实东北江湖80年代新一批老大地盘的你争我夺……江湖上的恩怨此起彼伏，厌倦了的东霸天，看透黑道，与人人唾弃的邻家女白鸽结为夫妻…… 　　黑道男人们一身匪气，但重义讲道，虽然在生活的迷茫中靠拳脚解决苦闷，却依旧寻找不到人生的真谛。一场场流血斗争之后，狭义的忠诚升华成人生的忠孝礼义，那些有勇无谋的汉子们开始学会用头脑、用心去生活……");
            } else {
                book.setDescription("《东北往事：黑道风云20年》的前传，讲述在1982年那个特殊的年代，刘海柱和东霸天等在江湖中的传奇。　　本书作为《黑道悲情》第2部，讲述了东霸天死后“我市”的各帮派割据中发生的爱恨情仇故事。情节分两条线展开，一条线是逃亡的刘海柱的传奇经历；另一条线是亡兄的冯朦胧从一个懦弱怕事的小诗人成长为带有神经病气质的心狠手辣的“冯二子”的过程。　　一手遮天的东霸天含恨离世后，各路草莽蠢蠢欲动。不得不从荒山上走回人间的刘海柱面对公安局的追捕、与周萌的感情纠葛该做何选择？丧兄之痛，使这世界上再也没有了稚嫩且懦弱的冯朦胧，却多了一个心黑手毒的冯二子。猛农过江的西霸天李灿然，又将在江湖中掀起什么波澜？……");
            }
            book.setLabel("1");
            book.setPublishDate(TimeUtils.formatDateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
            book.setPublisher("lym");
            book.setSubTitle(subTitle);
            book.setTitle(bookName + index);
            book.setWords(0);
            book.setType(0);
            book.setState(0);
            book.setSeriesId(1);
            book.setUpdateState(1);
            book.setReadCount(0);
            webCrawlerDao.saveBook(book);
            bookId = book.getId();

            for (Element li : liList) {
                String style = li.attr("style");
                if (StringUtils.isNotBlank(style)) {// 副标题
                    // 更新每本书的总字数
                    if (bookId != 0) {
                        webCrawlerDao.updateWordCount(bookId, countWords);
                        countWords = 0;
                    }
                    Element b = li.selectFirst("b");
                    subTitle = b.text();

                    BookChapter chapter = new BookChapter();
                    chapter.setBookId(bookId);
                    chapter.setChapterTitle(subTitle);
                    chapter.setPosition(chapterIndex);
                    webCrawlerDao.saveBookChapter(chapter);
                    chapterId = chapter.getId();
                    sectionIndex = 0;
                    chapterIndex++;
                } else {
                    Element a = li.selectFirst("a");
                    String sectionURL = a.attr("href");
                    String sectionTitle = a.attr("title");

                    // 爬取每一章节的内容
                    Document sectionDoc = Jsoup.connect(sectionURL).get();
                    Element contentDiv = sectionDoc.body().selectFirst("div.contentbox");// 内容原文div
                    List<Node> nodes = contentDiv.childNodes();
                    List<String> sectionContentList = new ArrayList<>();// 存储章节中每段原文
                    for (Node node : nodes) {
                        if (node instanceof TextNode) {
                            String text = ((TextNode) node).text().trim();
                            if (text.indexOf("南书房") == 0) {
                                continue;
                            }
                            if (text.indexOf("请阅读") == 0) {
                                break;
                            }
                            if (StringUtils.isNotBlank(text)) {
                                sectionContentList.add(text);
                            }
                        }
                    }
                    String sectionContent = objectMapper.writeValueAsString(sectionContentList);
                    int count = NumberUtils.chineseWordCount(sectionContent);
                    BookSection section = new BookSection();
                    section.setBookId(bookId);
                    section.setChapterId(chapterId);
                    section.setPosition(sectionIndex);
                    section.setSectionContent(sectionContent);
                    section.setSectionTitle(sectionTitle);
                    section.setSectionUrl(sectionURL);
                    section.setWords(count);
                    webCrawlerDao.saveBookSection(section);

                    countWords += count;
                    sectionIndex++;
                }
            }

            if (bookId != 0) {
                webCrawlerDao.updateWordCount(bookId, countWords);
            }
            index++;
        }

        // 插入tb_crwler_book_type表
        CrawlerBookType crawlerBookType = new CrawlerBookType();
        crawlerBookType.setType(type);
        webCrawlerDao.saveCrawlerBookType(crawlerBookType);

        returnMap.put("message", "success");
        returnMap.put("status", 1);
        return returnMap;
    }

    /**
     * 《东北往事之风云黑道20年(1-6部)》
     * URL: http://www.nansf.net/yuedu_879/
     * @date 2018-01-06 07:58:00
     * @param request
     * @return
     */
    private HashMap<String, Object> crawlerDBWS(HttpServletRequest request, int type) throws Exception {
        HashMap<String, Object> returnMap = new HashMap<>();

        // 检查该书是否已存在
        if (bookIsExists(type)) {
            returnMap.put("message", "该书已存在");
            returnMap.put("status", 2);
            return returnMap;
        }

        String url = "http://www.nansf.net/yuedu_879/";
        // 爬取1-5部所有章节的url
        Document doc = Jsoup.connect(url).get();// 获取页面DOM文档
        Element element = doc.body().select("div.book_list").get(0);// 筛选出想要的元素，类似jquery选择器
        Elements liList = element.select("li");// 找出所有li标签
        String subTitle = "";// 副标题
        int bookId = 0;// 书ID
        int chapterId = 0;// 章节ID
        String bookName = "东北往事之黑道风云二十年";
        int index = 1;// 小说第几部索引
        int chapterIndex = 0;// 每部的章节索引
        int sectionIndex = 0;// 每章节的段落索引
        int countWords = 0;
        for (Element li : liList) {
            String style = li.attr("style");
            if (StringUtils.isNotBlank(style)) {// 副标题
                // 更新每本书的总字数
                if (bookId != 0) {
                    webCrawlerDao.updateWordCount(bookId, countWords);
                    countWords = 0;
                }
                Element b = li.selectFirst("b");
                subTitle = b.text();
                if (subTitle.indexOf("部") > 0) {// 保存书籍信息
                    // 清除索引
                    chapterIndex = 0;
                    sectionIndex = 0;

                    // 保存书籍信息
                    Book book = new Book();
                    book.setAuthorName("孔二狗");
                    book.setChannel(1);
                    book.setDescription("从八十年代古典流氓的街头火拼，到九十年代拜金流氓的金钱战争，再到如今的官商勾结，整个流氓组织的演变过程，光怪陆离、惊心动魄。经过20年的血腥洗礼，多少活泼的生命灰飞烟灭之后，剩下的个...");
                    book.setLabel("1");
                    book.setPublishDate(TimeUtils.formatDateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
                    book.setPublisher("lym");
                    book.setSubTitle(subTitle);
                    book.setTitle(bookName+index);
                    book.setWords(0);
                    book.setType(0);
                    book.setState(0);
                    book.setSeriesId(1);
                    book.setUpdateState(1);
                    book.setReadCount(0);
                    webCrawlerDao.saveBook(book);
                    bookId = book.getId();
                    index++;

                    // 1-4部保存章节信息
                    if ("第一部".equals(subTitle.trim().substring(0,3)) || "第二部".equals(subTitle.trim().substring(0,3)) ||
                        "第三部".equals(subTitle.trim().substring(0,3)) || "第四部".equals(subTitle.trim().substring(0,3))) {
                        BookChapter chapter = new BookChapter();
                        chapter.setBookId(bookId);
                        chapter.setChapterTitle(subTitle);
                        chapter.setPosition(chapterIndex);
                        webCrawlerDao.saveBookChapter(chapter);
                        chapterId = chapter.getId();
                    }
                } else {// 保存章节信息
                    BookChapter chapter = new BookChapter();
                    chapter.setBookId(bookId);
                    chapter.setChapterTitle(subTitle);
                    chapter.setPosition(chapterIndex);
                    webCrawlerDao.saveBookChapter(chapter);
                    chapterId = chapter.getId();
                    sectionIndex = 0;
                    chapterIndex++;
                }
            } else {
                Element a = li.selectFirst("a");
                String sectionURL = a.attr("href");
                String sectionTitle = a.attr("title");
                if ("引子--二狗故事".equals(sectionTitle)) {
                    continue;
                }

                // 爬取每一章节的内容
                Document sectionDoc = Jsoup.connect(sectionURL).get();
                Element contentDiv = sectionDoc.body().selectFirst("div.contentbox");// 内容原文div
                List<Node> nodes = contentDiv.childNodes();
                List<String> sectionContentList = new ArrayList<>();// 存储章节中每段原文
                for (Node node : nodes) {
                    if (node instanceof TextNode) {
                        String text = ((TextNode) node).text().trim();
                        if (text.indexOf("南书房") == 0) {
                            continue;
                        }
                        if (text.indexOf("请阅读") == 0) {
                            break;
                        }
                        if (StringUtils.isNotBlank(text)) {
                            sectionContentList.add(text);
                        }
                    }
                }
                String sectionContent = objectMapper.writeValueAsString(sectionContentList);
                int count = NumberUtils.chineseWordCount(sectionContent);
                BookSection section = new BookSection();
                section.setBookId(bookId);
                section.setChapterId(chapterId);
                section.setPosition(sectionIndex);
                section.setSectionContent(sectionContent);
                section.setSectionTitle(sectionTitle);
                section.setSectionUrl(sectionURL);
                section.setWords(count);
                webCrawlerDao.saveBookSection(section);

                countWords += count;
                sectionIndex++;
            }
        }

        if (bookId != 0) {
            webCrawlerDao.updateWordCount(bookId, countWords);
        }

        // 插入tb_crwler_book_type表
        CrawlerBookType crawlerBookType = new CrawlerBookType();
        crawlerBookType.setType(type);
        webCrawlerDao.saveCrawlerBookType(crawlerBookType);

        returnMap.put("message", "success");
        returnMap.put("status", 1);
        return returnMap;
    }

    /**
     * 判断请求爬取的类型是否已存在，防止重复爬取同一本书
     * @param type
     * @return true: 已存在  false: 不存在
     */
    private boolean bookIsExists(int type) {
        CrawlerBookType crawlerBookType = webCrawlerDao.getCrawlerBookType(type);
        if (crawlerBookType != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 南书房爬虫模板
     * @param request
     * @param params
     * @return
     * @throws Exception
     */
    private HashMap<String, Object> crawler(HttpServletRequest request, HashMap<String, Object> params) throws Exception {
        HashMap<String, Object> returnMap = new HashMap<>();
        String url = (String) params.get("url");
        int type = (int) params.get("type");
        String bookName = (String) params.get("bookName");
        String authorName = (String) params.get("authorName");
        String description = (String) params.get("description");
        String label = (String) params.get("label");
        boolean isChapter = (boolean) params.get("isChapter");

        // 检查该书是否已存在
        if (bookIsExists(type)) {
            returnMap.put("message", "该书已存在");
            returnMap.put("status", 2);
            return returnMap;
        }

        // 爬取1-5部所有章节的url
        Document doc = Jsoup.connect(url).get();// 获取页面DOM文档
        Element element = doc.body().select("div.book_list").get(0);// 筛选出想要的元素，类似jquery选择器
        Elements liList = element.select("li");// 找出所有li标签
        String subTitle = "";// 副标题
        int bookId = 0;// 书ID
        int chapterId = 0;// 章节ID
        int chapterIndex = 0;// 每部的章节索引
        int sectionIndex = 0;// 每章节的段落索引
        int countWords = 0;

        // 保存书籍信息
        Book book = new Book();
        book.setAuthorName(authorName);
        book.setChannel(1);
        book.setDescription(description);
        book.setLabel(label);
        book.setPublishDate(TimeUtils.formatDateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        book.setPublisher("lym");
        book.setSubTitle(subTitle);
        book.setTitle(bookName);
        book.setWords(0);
        book.setType(0);
        book.setState(0);
        book.setSeriesId(1);
        book.setUpdateState(1);
        book.setReadCount(0);
        webCrawlerDao.saveBook(book);
        bookId = book.getId();

        // 没有章节信息时执行此代码
        if (!isChapter) {
            BookChapter chapter = new BookChapter();
            chapter.setBookId(bookId);
            chapter.setChapterTitle(subTitle);
            chapter.setPosition(chapterIndex);
            webCrawlerDao.saveBookChapter(chapter);
            chapterId = chapter.getId();
        }

        for (Element li : liList) {
            String style = li.attr("style");
            if (StringUtils.isNotBlank(style)) {// 副标题
                Element b = li.selectFirst("b");
                subTitle = b.text();

                BookChapter chapter = new BookChapter();
                chapter.setBookId(bookId);
                chapter.setChapterTitle(subTitle);
                chapter.setPosition(chapterIndex);
                webCrawlerDao.saveBookChapter(chapter);
                chapterId = chapter.getId();
                sectionIndex = 0;
                chapterIndex++;
            } else {
                Element a = li.selectFirst("a");
                String sectionURL = a.attr("href");
                String sectionTitle = a.attr("title");

                // 爬取每一章节的内容
                Document sectionDoc = Jsoup.connect(sectionURL).get();
                Element contentDiv = sectionDoc.body().selectFirst("div.contentbox");// 内容原文div
                List<Node> nodes = contentDiv.childNodes();
                List<String> sectionContentList = new ArrayList<>();// 存储章节中每段原文
                for (Node node : nodes) {
                    if (node instanceof TextNode) {
                        String text = ((TextNode) node).text().trim();
                        if (text.indexOf("南书房") == 0) {
                            continue;
                        }
                        if (text.indexOf("请阅读") == 0) {
                            break;
                        }
                        if (StringUtils.isNotBlank(text)) {
                            sectionContentList.add(text);
                        }
                    }
                }
                String sectionContent = objectMapper.writeValueAsString(sectionContentList);
                int count = NumberUtils.chineseWordCount(sectionContent);
                BookSection section = new BookSection();
                section.setBookId(bookId);
                section.setChapterId(chapterId);
                section.setPosition(sectionIndex);
                section.setSectionContent(sectionContent);
                section.setSectionTitle(sectionTitle);
                section.setSectionUrl(sectionURL);
                section.setWords(count);
                webCrawlerDao.saveBookSection(section);

                countWords += count;
                sectionIndex++;
            }
        }

        if (bookId != 0) {
            webCrawlerDao.updateWordCount(bookId, countWords);
        }

        // 插入tb_crwler_book_type表
        CrawlerBookType crawlerBookType = new CrawlerBookType();
        crawlerBookType.setType(type);
        webCrawlerDao.saveCrawlerBookType(crawlerBookType);

        returnMap.put("message", "success");
        returnMap.put("status", 1);
        return returnMap;
    }

}
