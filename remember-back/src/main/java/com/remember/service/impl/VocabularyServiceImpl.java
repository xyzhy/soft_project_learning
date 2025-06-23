package com.remember.service.impl;

import java.util.*;

import com.ruoyi.common.annotation.DateOperation;
import com.ruoyi.common.exception.attribute.NullAttributeException;
import com.ruoyi.common.utils.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
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
     * 批量插入词汇
     * 如果词汇书中已经存在词汇则进行更新操作
     * @param vocabularies 待操作的词汇
     * @return 操作成功的数量
     */
    @Override
    public int insertBatchVocabulary(List<Vocabulary> vocabularies) {
        System.out.println(vocabularies);
        int success_numeric = 0;
        List<Vocabulary> waitUpdateVocab = new ArrayList<>();
        List<Vocabulary> waitInsertVocab = new ArrayList<>();
        waitInsertVocab.addAll(vocabularies);

        // 词汇书中拥有的词汇
        Vocabulary select = new Vocabulary();
        select.setWordBookId(vocabularies.get(0).getWordBookId());
        List<Vocabulary> wordOwnVocab = selectVocabularyList(select);

        // 检查是否拥有这些词
        for (Vocabulary vocabulary : vocabularies) {
            // 合并词性和词义
            for (Vocabulary word : wordOwnVocab) {
                if (word.equals(vocabulary)) {
                    // 检查其用用这些词则将其从插入词中移除, 并移入修改词
                    waitInsertVocab.remove(vocabulary);
                    // 新意思
                    String[][] new_pos_means = getPosMeans(vocabulary);
                    // 旧意思
                    String[][] old_pos_means = getPosMeans(word);
                    // 合并之后的意思
                    StringBuffer means = new StringBuffer();

                    for (String[] newPosMean : new_pos_means) {
                        boolean flag = false;
                        for (String[] oldPosMean : old_pos_means) {
                            // 词性相同, 现在校验中文意思然后去重合并
                            if (StringUtils.equals(newPosMean[0], oldPosMean[0])) {
                                // 去重
                                Set<String> set = new HashSet<>();
                                set.addAll(Arrays.asList(newPosMean[1].split(",")));
                                set.addAll(Arrays.asList(oldPosMean[1].split(",")));
                                // 合并
                                String[] joinAfterMeans = set.toArray(new String[]{});
                                means
                                        .append(newPosMean[0])
                                        .append(":");
                                for (int i = 0; i < joinAfterMeans.length; i++) {
                                    means.append(joinAfterMeans[i]);
                                    if (i+1 < joinAfterMeans.length) {
                                        means.append(",");
                                    }
                                }
                                means.append(";");
                                flag = true;
                            }
                        }
                        if (!flag) {
                            means
                                    .append(newPosMean[0])
                                    .append(":")
                                    .append(newPosMean[1])
                                    .append(";");
                        }
                    }
                    word.setMeans(means.toString());
                    waitUpdateVocab.add(word);
                }
            }
        }

        System.out.println(waitInsertVocab);
        System.out.println(waitUpdateVocab);
        // 批量插入和批量更新
        if (!waitInsertVocab.isEmpty()) {
            success_numeric += vocabularyMapper.insertBatchVocabulary(waitInsertVocab);
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
    public int updateVocabulary(Vocabulary vocabulary)
    {
        // todo 更新, 如果是更新单词, 则将单词从原来的词汇中删除, 再检查是否拥有此单词, 如果有就更新词性词义, 如果没有就直接重新插入
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
