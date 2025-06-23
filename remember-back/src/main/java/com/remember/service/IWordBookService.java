package com.remember.service;

import java.util.List;
import com.remember.domain.WordBook;

/**
 * 词汇书Service接口
 * 
 * @author xxxz_left
 * @date 2025-05-15
 */
public interface IWordBookService 
{
    /**
     * 查询词汇书
     * 
     * @param wordBookId 词汇书主键
     * @return 词汇书
     */
    WordBook selectWordBookByWordBookId(String wordBookId);

    /**
     * 查询词汇书列表
     * 
     * @param wordBook 词汇书
     * @return 词汇书集合
     */
    List<WordBook> selectWordBookList(WordBook wordBook);

    /**
     * 新增词汇书
     * 
     * @param wordBook 词汇书
     * @return 结果
     */
    int insertWordBook(WordBook wordBook);

    /**
     * 修改词汇书
     * 
     * @param wordBook 词汇书
     * @return 结果
     */
    int updateWordBook(WordBook wordBook);

    /**
     * 批量删除词汇书
     * 
     * @param wordBookIds 需要删除的词汇书主键集合
     * @return 结果
     */
    int deleteWordBookByWordBookIds(String[] wordBookIds);

    /**
     * 删除词汇书信息
     * 
     * @param wordBookId 词汇书主键
     * @return 结果
     */
    int deleteWordBookByWordBookId(String wordBookId);
}
