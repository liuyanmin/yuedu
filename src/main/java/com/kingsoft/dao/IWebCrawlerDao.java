package com.kingsoft.dao;

import com.kingsoft.pojo.Book;
import com.kingsoft.pojo.BookChapter;
import com.kingsoft.pojo.BookSection;
import com.kingsoft.pojo.CrawlerBookType;

import java.util.List;

/**
 * Created by LIUYANMIN on 2018/1/5.
 */
public interface IWebCrawlerDao {

    // 根据书ID查找
    Book getBookById(int id);

    // 根据书名模糊查询
    List<Book> getBookByTitle(String title);

    // 查询已爬取的小说类型
    CrawlerBookType getCrawlerBookType(int type);

    // 保存已爬取到的type
    int saveCrawlerBookType(CrawlerBookType crawlerBookType);

    // 保存书籍信息，并返回自增主键
    int saveBook(Book book);

    // 保存章节信息，并返回自增主键
    int saveBookChapter(BookChapter bookChapter);

    // 更新书籍总字数
    int updateWordCount(int bookId, int count);

    // 保存段落信息
    int saveBookSection(BookSection section);
}
