package com.remember.mapper;

import java.util.List;
import com.remember.domain.BookToVocabulary;

/**
 * 这个记录了每一个词汇书中对应的词Mapper接口
 *
 * @author ruoyi
 * @date 2025-05-26
 */
public interface BookToVocabularyMapper
{
    /**
     * 查询这个记录了每一个词汇书中对应的词
     *
     * @param bookToVocabularyId 这个记录了每一个词汇书中对应的词主键
     * @return 这个记录了每一个词汇书中对应的词
     */
    public BookToVocabulary selectBookToVocabularyByBookToVocabularyId(String bookToVocabularyId);

    /**
     * 查询这个记录了每一个词汇书中对应的词列表
     *
     * @param bookToVocabulary 这个记录了每一个词汇书中对应的词
     * @return 这个记录了每一个词汇书中对应的词集合
     */
    public List<BookToVocabulary> selectBookToVocabularyList(BookToVocabulary bookToVocabulary);

    /**
     * 新增这个记录了每一个词汇书中对应的词
     *
     * @param bookToVocabulary 这个记录了每一个词汇书中对应的词
     * @return 结果
     */
    public int insertBookToVocabulary(BookToVocabulary bookToVocabulary);

    /**
     * 修改这个记录了每一个词汇书中对应的词
     *
     * @param bookToVocabulary 这个记录了每一个词汇书中对应的词
     * @return 结果
     */
    public int updateBookToVocabulary(BookToVocabulary bookToVocabulary);

    /**
     * 删除这个记录了每一个词汇书中对应的词
     *
     * @param bookToVocabularyId 这个记录了每一个词汇书中对应的词主键
     * @return 结果
     */
    public int deleteBookToVocabularyByBookToVocabularyId(String bookToVocabularyId);

    /**
     * 批量删除这个记录了每一个词汇书中对应的词
     *
     * @param bookToVocabularyIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBookToVocabularyByBookToVocabularyIds(String[] bookToVocabularyIds);
}