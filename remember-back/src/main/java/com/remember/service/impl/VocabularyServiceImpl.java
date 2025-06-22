package com.remember.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DateOperation;
import com.ruoyi.common.annotation.WaitFillId;
import com.ruoyi.common.exception.attribute.NullAttributeException;
import com.ruoyi.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.remember.mapper.VocabularyMapper;
import com.remember.domain.Vocabulary;
import com.remember.service.IVocabularyService;

/**
 * 词汇Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-26
 */
@Service
public class VocabularyServiceImpl implements IVocabularyService 
{
    private static final Logger log = LoggerFactory.getLogger(VocabularyServiceImpl.class);
    @Autowired
    private VocabularyMapper vocabularyMapper;

    /**
     * 查询词汇
     * 
     * @param vocabularyId 词汇主键
     * @return 词汇
     */
    @Override
    public Vocabulary selectVocabularyByVocabularyId(String vocabularyId)
    {
        return vocabularyMapper.selectVocabularyByVocabularyId(vocabularyId);
    }

    /**
     * 查询词汇列表
     * 
     * @param vocabulary 词汇
     * @return 词汇
     */
    @Override
    public List<Vocabulary> selectVocabularyList(Vocabulary vocabulary)
    {
        // 应选择词汇书才显示
        if (StringUtils.isBlank(vocabulary.getWordBookId())) {
            throw new NullAttributeException("请选择一本词汇书");
        }
        return vocabularyMapper.selectVocabularyList(vocabulary);
    }

    /**
     * 新增词汇
     * 
     * @param vocabulary 词汇
     * @return 结果
     */
    @Override
    public int insertVocabulary(Vocabulary vocabulary)
    {
        return vocabularyMapper.insertVocabulary(vocabulary);
    }

    /**
     * 修改词汇
     * 
     * @param vocabulary 词汇
     * @return 结果
     */
    @Override
    @DateOperation
    public int updateVocabulary(Vocabulary vocabulary)
    {
        return vocabularyMapper.updateVocabulary(vocabulary);
    }

    /**
     * 批量删除词汇
     * 
     * @param vocabularyIds 需要删除的词汇主键
     * @return 结果
     */
    @Override
    public int deleteVocabularyByVocabularyIds(String[] vocabularyIds)
    {
        return vocabularyMapper.deleteVocabularyByVocabularyIds(vocabularyIds);
    }

    /**
     * 删除词汇信息
     * 
     * @param vocabularyId 词汇主键
     * @return 结果
     */
    @Override
    public int deleteVocabularyByVocabularyId(String vocabularyId)
    {
        return vocabularyMapper.deleteVocabularyByVocabularyId(vocabularyId);
    }
}
