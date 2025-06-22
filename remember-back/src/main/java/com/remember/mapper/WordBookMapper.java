package com.remember.mapper;

import java.util.List;
import com.remember.domain.WordBook;

/**
 * 词汇书Mapper接口
 * 
 * @author xxxz_left
 * @date 2025-05-15
 */
public interface WordBookMapper 
{
    /**
     * 查询词汇书
     * 
     * @param wordBookId 词汇书主键
     * @return 词汇书
     */
    public WordBook selectWordBookByWordBookId(String wordBookId);

    /**
     * 查询词汇书列表
     * 
     * @param wordBook 词汇书
     * @return 词汇书集合
     */
    public List<WordBook> selectWordBookList(WordBook wordBook);

    /**
     * 新增词汇书
     * 
     * @param wordBook 词汇书
     * @return 结果
     */
    public int insertWordBook(WordBook wordBook);

    /**
     * 修改词汇书
     * 
     * @param wordBook 词汇书
     * @return 结果
     */
    public int updateWordBook(WordBook wordBook);

    /**
     * 删除词汇书
     * 
     * @param wordBookId 词汇书主键
     * @return 结果
     */
    public int deleteWordBookByWordBookId(String wordBookId);

    /**
     * 批量删除词汇书
     * 
     * @param wordBookIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWordBookByWordBookIds(String[] wordBookIds);
}
