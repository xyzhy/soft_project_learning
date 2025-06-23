package com.remember.mapper;

import java.util.List;
import com.remember.domain.Vocabulary;

/**
 * 这是词, 和词汇做链接Mapper接口
 * 
 * @author ruoyi
 * @date 2025-05-26
 */
public interface VocabularyMapper 
{
    /**
     * 批量插入词汇
     * @param list 等待插入的词汇
     * @return 操作成功的条数
     */
    public int insertBatchVocabulary(List<Vocabulary> list);

    /**
     * 批量修改词汇
     * @param list 等待修改的词汇
     * @return 操作成功的条数
     */

    public int updateBatchVocabulary(List<Vocabulary> list);
    /**
     * 查询这是词, 和词汇做链接
     * 
     * @param vocabularyId 这是词, 和词汇做链接主键
     * @return 这是词, 和词汇做链接
     */
    public Vocabulary selectVocabularyByVocabularyId(String vocabularyId);

    /**
     * 查询这是词, 和词汇做链接列表
     * 
     * @param vocabulary 这是词, 和词汇做链接
     * @return 这是词, 和词汇做链接集合
     */
    public List<Vocabulary> selectVocabularyList(Vocabulary vocabulary);

    /**
     * 新增这是词, 和词汇做链接
     * 
     * @param vocabulary 这是词, 和词汇做链接
     * @return 结果
     */
    public int insertVocabulary(Vocabulary vocabulary);

    /**
     * 修改这是词, 和词汇做链接
     * 
     * @param vocabulary 这是词, 和词汇做链接
     * @return 结果
     */
    public int updateVocabulary(Vocabulary vocabulary);

    /**
     * 删除这是词, 和词汇做链接
     * 
     * @param vocabularyId 这是词, 和词汇做链接主键
     * @return 结果
     */
    public int deleteVocabularyByVocabularyId(String vocabularyId);

    /**
     * 批量删除这是词, 和词汇做链接
     * 
     * @param vocabularyIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteVocabularyByVocabularyIds(String[] vocabularyIds);
}
