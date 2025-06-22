package com.remember.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DateOperation;
import com.ruoyi.common.annotation.WaitFillId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.remember.mapper.WordBookMapper;
import com.remember.domain.WordBook;
import com.remember.service.IWordBookService;

/**
 * 词汇书Service业务层处理
 * 
 * @author xxxz_left
 * @date 2025-05-15
 */
@Service
public class WordBookServiceImpl implements IWordBookService 
{
    @Autowired
    private WordBookMapper wordBookMapper;

    /**
     * 查询词汇书
     * 
     * @param wordBookId 词汇书主键
     * @return 词汇书
     */
    @Override
    public WordBook selectWordBookByWordBookId(String wordBookId)
    {
        return wordBookMapper.selectWordBookByWordBookId(wordBookId);
    }

    /**
     * 查询词汇书列表
     * 
     * @param wordBook 词汇书
     * @return 词汇书
     */
    @Override
    public List<WordBook> selectWordBookList(WordBook wordBook)
    {
        return wordBookMapper.selectWordBookList(wordBook);
    }

    /**
     * 新增词汇书
     * 
     * @param wordBook 词汇书
     * @return 结果
     */
    @Override
    @WaitFillId
    @DateOperation
    public int insertWordBook(WordBook wordBook)
    {
        return wordBookMapper.insertWordBook(wordBook);
    }

    /**
     * 修改词汇书
     * 
     * @param wordBook 词汇书
     * @return 结果
     */
    @Override
    @DateOperation
    public int updateWordBook(WordBook wordBook)
    {
        return wordBookMapper.updateWordBook(wordBook);
    }

    /**
     * 批量删除词汇书
     * 
     * @param wordBookIds 需要删除的词汇书主键
     * @return 结果
     */
    @Override
    public int deleteWordBookByWordBookIds(String[] wordBookIds)
    {
        return wordBookMapper.deleteWordBookByWordBookIds(wordBookIds);
    }

    /**
     * 删除词汇书信息
     * 
     * @param wordBookId 词汇书主键
     * @return 结果
     */
    @Override
    public int deleteWordBookByWordBookId(String wordBookId)
    {
        return wordBookMapper.deleteWordBookByWordBookId(wordBookId);
    }
}
