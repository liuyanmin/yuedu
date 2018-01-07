package com.kingsoft.pojo;

import java.util.Date;

public class BookChapter {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_book_chapter.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_book_chapter.book_id
     *
     * @mbggenerated
     */
    private Integer bookId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_book_chapter.chapter_title
     *
     * @mbggenerated
     */
    private String chapterTitle;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_book_chapter.position
     *
     * @mbggenerated
     */
    private Integer position;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_book_chapter.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_book_chapter.id
     *
     * @return the value of tb_book_chapter.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_book_chapter.id
     *
     * @param id the value for tb_book_chapter.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_book_chapter.book_id
     *
     * @return the value of tb_book_chapter.book_id
     *
     * @mbggenerated
     */
    public Integer getBookId() {
        return bookId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_book_chapter.book_id
     *
     * @param bookId the value for tb_book_chapter.book_id
     *
     * @mbggenerated
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_book_chapter.chapter_title
     *
     * @return the value of tb_book_chapter.chapter_title
     *
     * @mbggenerated
     */
    public String getChapterTitle() {
        return chapterTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_book_chapter.chapter_title
     *
     * @param chapterTitle the value for tb_book_chapter.chapter_title
     *
     * @mbggenerated
     */
    public void setChapterTitle(String chapterTitle) {
        this.chapterTitle = chapterTitle == null ? null : chapterTitle.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_book_chapter.position
     *
     * @return the value of tb_book_chapter.position
     *
     * @mbggenerated
     */
    public Integer getPosition() {
        return position;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_book_chapter.position
     *
     * @param position the value for tb_book_chapter.position
     *
     * @mbggenerated
     */
    public void setPosition(Integer position) {
        this.position = position;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_book_chapter.update_time
     *
     * @return the value of tb_book_chapter.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_book_chapter.update_time
     *
     * @param updateTime the value for tb_book_chapter.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}