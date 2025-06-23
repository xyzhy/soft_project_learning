package com.remember.mapper;

import java.util.List;
import com.remember.domain.Vocabulary;
import org.apache.ibatis.annotations.Param;

/**
 * 这是词, 和词汇做链接Mapper接口
 * 
 * @author ruoyi
 * @date 2025-05-26
 */
public interface VocabularyMapper 
{

    /**
     * 检查词汇中是否拥有这个单词
     * @param vocabulary 词汇
     * @return null或者是这个单词
     */
    Vocabulary selectVocabularyByEnglish(Vocabulary vocabulary);
    /**
     * 根据词汇书中的单词以查询词汇
     * @param list 词汇
     * @param wordBookId 词汇书
     * @return 词汇中拥有这些词汇
     */
    List<Vocabulary> selectVocabularyListByEnglish(@Param("list") List<Vocabulary> list, @Param("wordBookId") String wordBookId);
    /**
     * 批量插入词汇
     * @param list 等待插入的词汇
     * @return 操作成功的条数
     */
    int insertBatchVocabulary(List<Vocabulary> list);

    /**
     * 批量修改词汇
     * @param list 等待修改的词汇
     * @return 操作成功的条数
     */

    int updateBatchVocabulary(List<Vocabulary> list);
    /**
     * 查询这是词, 和词汇做链接
     * 
     * @param vocabularyId 这是词, 和词汇做链接主键
     * @return 这是词, 和词汇做链接
     */
    Vocabulary selectVocabularyByVocabularyId(String vocabularyId);

    /**
     * 查询这是词, 和词汇做链接列表
     * 
     * @param vocabulary 这是词, 和词汇做链接
     * @return 这是词, 和词汇做链接集合
     */
    List<Vocabulary> selectVocabularyList(Vocabulary vocabulary);

    /**
     * 新增这是词, 和词汇做链接
     * 
     * @param vocabulary 这是词, 和词汇做链接
     * @return 结果
     */
    int insertVocabulary(Vocabulary vocabulary);

    /**
     * 修改这是词, 和词汇做链接
     * 
     * @param vocabulary 这是词, 和词汇做链接
     * @return 结果
     */
    int updateVocabulary(Vocabulary vocabulary);

    /**
     * 删除这是词, 和词汇做链接
     * 
     * @param vocabularyId 这是词, 和词汇做链接主键
     * @return 结果
     */
    int deleteVocabularyByVocabularyId(String vocabularyId);

    /**
     * 批量删除这是词, 和词汇做链接
     * 
     * @param vocabularyIds 需要删除的数据主键集合
     * @return 结果
     */
    int deleteVocabularyByVocabularyIds(String[] vocabularyIds);
}
