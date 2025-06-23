package com.remember.service.impl;

import java.util.*;

import com.ruoyi.common.annotation.DateOperation;
import com.ruoyi.common.exception.attribute.NullAttributeException;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import com.remember.mapper.VocabularyMapper;
import com.remember.domain.Vocabulary;
import com.remember.service.IVocabularyService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 词汇Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-05-26
 */
@Service
public class VocabularyServiceImpl implements IVocabularyService 
{
    private final VocabularyMapper vocabularyMapper;

    public VocabularyServiceImpl(VocabularyMapper vocabularyMapper) {
        this.vocabularyMapper = vocabularyMapper;
    }

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
     * 得到词汇中的词性和中文意思
     * @param vocabulary 待拆分的词
     * @return 每一行是不同的词性及其意思, 第一列是词性, 第二列是中文意思
     */
    private String[][] getPosMeans(Vocabulary vocabulary) {
        String newMeans = vocabulary.getMeans();
        String[] newWordMeans = newMeans.split(";");
        List<String[]> temp = new ArrayList<>();
        for (String newWordMean : newWordMeans) {
            temp.add(newWordMean.split(":"));
        }
        return temp.toArray(new String[][]{});
    }

    /**
     * 从方法用来合并两个词汇的词义
     * @param newMeans 可能蕴含新的词义的词汇
     * @param oldMeans 原来的词义
     * @return 合并之后的词义
     */
    private String extraMeans(Vocabulary newMeans, Vocabulary oldMeans) {
        Map<String, String> map = new HashMap<>();
        // 新意思
        String[][] new_pos_means = getPosMeans(newMeans);
        for (String[] newPosMean : new_pos_means) {
            map.put(newPosMean[0], newPosMean[1]);
        }
        // 旧意思
        String[][] old_pos_means = getPosMeans(oldMeans);
        for (String[] oldPosMean : old_pos_means) {
            String oldMean = map.get(oldPosMean[0]);
            if (StringUtils.isEmpty(oldMean)) {
                map.put(oldPosMean[0], oldPosMean[1]);
            }

            StringBuilder means = new StringBuilder();
            // 去重
            Set<String> set = new HashSet<>();
            set.addAll(Arrays.asList(oldMean.split(",")));
            set.addAll(Arrays.asList(oldPosMean[1].split(",")));
            // 合并
            String[] joinAfterMeans = set.toArray(new String[]{});
            for (int i = 0; i < joinAfterMeans.length; i++) {
                means.append(joinAfterMeans[i]);
                if (i+1 < joinAfterMeans.length) {
                    means.append(",");
                }
            }
            map.put(oldPosMean[0], means.toString());
        }

        // 合并之后的意思
        StringBuilder means = new StringBuilder();
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            means
                    .append(stringStringEntry.getKey())
                    .append(":")
                    .append(stringStringEntry.getValue())
                    .append(";");

        }
        return means.toString();
    }

    /**
     * 批量插入词汇
     * 如果词汇书中已经存在词汇则进行更新操作
     * @param vocabularies 待操作的词汇
     * @return 操作成功的数量
     */
    @Override
    public int insertBatchVocabulary(List<Vocabulary> vocabularies) {
        System.out.println(vocabularies);
        int success_numeric = 0;
        // 更具上传的词汇寻找以及存在的词汇
        List<Vocabulary> waitUpdateVocab = vocabularyMapper.selectVocabularyListByEnglish(vocabularies, vocabularies.get(0).getWordBookId());

        // 检查是否拥有这些词
        for (Vocabulary oldMeans : waitUpdateVocab) {
            int ownIndex = vocabularies.indexOf(oldMeans);
            Vocabulary newMeans = vocabularies.remove(ownIndex);

            // 合并词性和词义
            oldMeans.setMeans(extraMeans(newMeans, oldMeans));
        }

        // 批量插入和批量更新
        if (!vocabularies.isEmpty()) {
            success_numeric += vocabularyMapper.insertBatchVocabulary(vocabularies);
        }
        if (!waitUpdateVocab.isEmpty()) {
            success_numeric += vocabularyMapper.updateBatchVocabulary(waitUpdateVocab);
        }
        return success_numeric;
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
    @Transactional
    public int updateVocabulary(Vocabulary vocabulary)
    {
        // 更新, 如果是更新单词, 则将单词从原来的词汇中删除, 再检查是否拥有此单词, 如果有就更新词性词义, 如果没有就直接重新插入
        Vocabulary origin = vocabularyMapper.selectVocabularyByVocabularyId(vocabulary.getVocabularyId());
        Vocabulary mayEexistOrigin = vocabularyMapper.selectVocabularyByEnglish(vocabulary);
        // 如果是更新的是单词, 并且其中没有单词的话, 那么就直接原地更新就行了或者只是更新词义的话, 也可以原地更新
        if (mayEexistOrigin == null || StringUtils.equals(origin.getEnglish(), vocabulary.getEnglish())) {
            return vocabularyMapper.updateVocabulary(vocabulary);
        }

        // 如果原先拥有这个单词, 那么合并词性和词义, 并删除原先的单词
        int remove = vocabularyMapper.deleteVocabularyByVocabularyId(vocabulary.getVocabularyId());
        if (remove == 0) {
            throw new RuntimeException("更新操作失败: 删除原先词出现了未知错误");
        }
        mayEexistOrigin.setMeans(extraMeans(vocabulary, mayEexistOrigin));
        return vocabularyMapper.updateVocabulary(mayEexistOrigin);
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
