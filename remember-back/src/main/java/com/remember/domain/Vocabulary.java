package com.remember.domain;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.GenerateId;
import com.ruoyi.common.utils.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 这是词, 和词汇做链接对象 vocabulary
 * 
 * @author ruoyi
 * @date 2025-05-26
 */
public class Vocabulary extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 词汇id */
    @GenerateId
    private String vocabularyId;

    /** 这个词属于哪一个词汇书 */
    private String wordBookId;

    /** 英文单词 */
    @Excel(name = "英文单词")
    private String english;

    /** 中文意思 */
    @Excel(name = "词性: 词1,词2;")
    private String means;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateDate;

    public String getMeans() {
        return means;
    }

    public void setMeans(String means) {
        this.means = means;
    }

    public void setVocabularyId(String vocabularyId)
    {
        this.vocabularyId = vocabularyId;
    }

    public String getVocabularyId() 
    {
        return vocabularyId;
    }

    public void setWordBookId(String wordBookId)
    {
        this.wordBookId = wordBookId;
    }

    public String getWordBookId()
    {
        return wordBookId;
    }

    public void setEnglish(String english) 
    {
        this.english = english;
    }

    public String getEnglish() 
    {
        return english;
    }

    public void setCreateDate(Date createDate) 
    {
        this.createDate = createDate;
    }

    public Date getCreateDate() 
    {
        return createDate;
    }

    public void setUpdateDate(Date updateDate) 
    {
        this.updateDate = updateDate;
    }

    public Date getUpdateDate() 
    {
        return updateDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("vocabularyId", getVocabularyId())
            .append("wordBookId", getWordBookId())
                .append("english", getEnglish())
                .append("means", getMeans())
            .append("createDate", getCreateDate())
            .append("updateDate", getUpdateDate())
            .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vocabulary that = (Vocabulary) o;
        return StringUtils.equals(getEnglish(), that.getEnglish());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(english);
    }
}
