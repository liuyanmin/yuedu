package com.kingsoft.pojo.mapper;

import com.kingsoft.pojo.YellowBookLabel;
import com.kingsoft.pojo.YellowBookLabelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YellowBookLabelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_yellow_book_label
     *
     * @mbggenerated
     */
    int countByExample(YellowBookLabelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_yellow_book_label
     *
     * @mbggenerated
     */
    int deleteByExample(YellowBookLabelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_yellow_book_label
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_yellow_book_label
     *
     * @mbggenerated
     */
    int insert(YellowBookLabel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_yellow_book_label
     *
     * @mbggenerated
     */
    int insertSelective(YellowBookLabel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_yellow_book_label
     *
     * @mbggenerated
     */
    List<YellowBookLabel> selectByExample(YellowBookLabelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_yellow_book_label
     *
     * @mbggenerated
     */
    YellowBookLabel selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_yellow_book_label
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") YellowBookLabel record, @Param("example") YellowBookLabelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_yellow_book_label
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") YellowBookLabel record, @Param("example") YellowBookLabelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_yellow_book_label
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(YellowBookLabel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_yellow_book_label
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(YellowBookLabel record);
}