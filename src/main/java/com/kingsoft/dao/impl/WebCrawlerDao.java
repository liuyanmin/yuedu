package com.kingsoft.dao.impl;

import com.kingsoft.dao.IWebCrawlerDao;
import com.kingsoft.pojo.*;
import com.kingsoft.pojo.mapper.BookChapterMapper;
import com.kingsoft.pojo.mapper.BookMapper;
import com.kingsoft.pojo.mapper.BookSectionMapper;
import com.kingsoft.pojo.mapper.CrawlerBookTypeMapper;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

/**
 * Created by LIUYANMIN on 2018/1/5.
 */
public class WebCrawlerDao extends SqlSessionDaoSupport implements IWebCrawlerDao {

    private static final String BOOK_PRE = "com.kingsoft.pojo.mapper.BookMapper.";
    private static final String BOOK_CHAPTER_PRE = "com.kingsoft.pojo.mapper.BookChapterMapper.";
    private static final String BOOK_LABEL_PRE = "com.kingsoft.pojo.mapper.BookLabelMapper.";
    private static final String CRAWLER_BOOK_PRE = "com.kingsoft.pojo.mapper.CrawlerBookTypeMapper.";

    @Override
    public int saveCrawlerBookType(CrawlerBookType crawlerBookType) {
        CrawlerBookTypeMapper mapper = this.getSqlSession().getMapper(CrawlerBookTypeMapper.class);
        return mapper.insert(crawlerBookType);
    }

    @Override
    public CrawlerBookType getCrawlerBookType(int type) {
        CrawlerBookTypeExample example = new CrawlerBookTypeExample();
        CrawlerBookTypeExample.Criteria criteria = example.createCriteria();
        criteria.andTypeEqualTo(type);
        return this.getSqlSession().selectOne(CRAWLER_BOOK_PRE + "selectByExample", example);
    }

    @Override
    public List<Book> getBookByTitle(String title) {
        BookExample example = new BookExample();
        BookExample.Criteria criteria = example.createCriteria();
        criteria.andTitleLike("%"+title+"%");
        return this.getSqlSession().selectList(BOOK_PRE + "selectByExample", example);
    }

    @Override
    public int saveBookSection(BookSection section) {
        BookSectionMapper mapper = this.getSqlSession().getMapper(BookSectionMapper.class);
        return mapper.insert(section);
    }

    @Override
    public int updateWordCount(int bookId, int count) {
        Book book = getBookById(bookId);
        book.setWords(count);
        BookMapper mapper = this.getSqlSession().getMapper(BookMapper.class);
        return mapper.updateByPrimaryKey(book);
    }

    @Override
    public int saveBook(Book book) {
        BookMapper mapper = this.getSqlSession().getMapper(BookMapper.class);
        return mapper.insert(book);
    }

    @Override
    public int saveBookChapter(BookChapter bookChapter) {
        BookChapterMapper mapper = this.getSqlSession().getMapper(BookChapterMapper.class);
        return mapper.insert(bookChapter);
    }

    @Override
    public Book getBookById(int id) {
        BookExample example = new BookExample();
        BookExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        Book book = this.getSqlSession().selectOne(BOOK_PRE + "selectByExample", example);
        return book;
    }

}
