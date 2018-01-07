package com.kingsoft.dao;

import com.kingsoft.pojo.YellowBookSection;
import com.kingsoft.pojo.YellowCrawlerError;

import java.util.List;

/**
 * Created by LIUYANMIN on 2018/1/6.
 */
public interface IYellowCrawlerDao {

    // 保存章节信息
    int saveYellowBookSection(YellowBookSection yellowBookSection);

    // 保存爬取异常网页
    int saveYellowCrawlerError(YellowCrawlerError error);

    // 查询出异常数据，查询小于指定个数单词的记录
    List<YellowBookSection> getExceptionData(int wordCount);

    // 更新文章内容
    int updateSection(YellowBookSection section);

}
