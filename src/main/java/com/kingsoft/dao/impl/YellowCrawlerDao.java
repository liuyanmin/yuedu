package com.kingsoft.dao.impl;

import com.kingsoft.dao.IYellowCrawlerDao;
import com.kingsoft.pojo.YellowBookSection;
import com.kingsoft.pojo.YellowBookSectionExample;
import com.kingsoft.pojo.YellowCrawlerError;
import com.kingsoft.pojo.mapper.YellowBookSectionMapper;
import com.kingsoft.pojo.mapper.YellowCrawlerErrorMapper;
import org.apache.log4j.Logger;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

/**
 * Created by LIUYANMIN on 2018/1/6.
 */
public class YellowCrawlerDao extends SqlSessionDaoSupport implements IYellowCrawlerDao {
    private static final Logger logger = Logger.getLogger(YellowCrawlerDao.class);
    private static final String YELLOW_BOOK_LABEL = "com.kingsoft.pojo.mapper.YellowBookLabelMapper.";
    private static final String YELLOW_BOOK_SECTION = "com.kingsoft.pojo.mapper.YellowBookSectionMapper.";

    @Override
    public int updateSection(YellowBookSection section) {
        YellowBookSectionMapper mapper = this.getSqlSession().getMapper(YellowBookSectionMapper.class);
        return mapper.updateByPrimaryKeyWithBLOBs(section);
    }

    @Override
    public List<YellowBookSection> getExceptionData(int wordCount) {
        YellowBookSectionExample example = new YellowBookSectionExample();
        YellowBookSectionExample.Criteria criteria = example.createCriteria();
//        criteria.andWordsLessThan(wordCount);
        return this.getSqlSession().selectList(YELLOW_BOOK_SECTION + "selectByExampleWithBLOBs", example);
    }

    @Override
    public int saveYellowCrawlerError(YellowCrawlerError error) {
        YellowCrawlerErrorMapper mapper = this.getSqlSession().getMapper(YellowCrawlerErrorMapper.class);
        return mapper.insert(error);
    }

    @Override
    public int saveYellowBookSection(YellowBookSection yellowBookSection) {
        YellowBookSectionMapper mapper = this.getSqlSession().getMapper(YellowBookSectionMapper.class);
        return mapper.insert(yellowBookSection);
    }

}
