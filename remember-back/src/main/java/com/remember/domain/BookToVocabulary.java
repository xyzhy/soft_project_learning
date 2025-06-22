package com.remember.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 这个记录了每一个词汇书中对应的词对象 book_to_vocabulary
 *
 * @author ruoyi
 * @date 2025-05-26
 */
public class BookToVocabulary extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 字典id */
    private String bookToVocabularyId;

    /** 词汇书id */
    @Excel(name = "词汇书id")
    private String wordBookId;

    /** 词id */
    @Excel(name = "词id")
    private String vocabularyId;

    public void setBookToVocabularyId(String bookToVocabularyId)
    {
        this.bookToVocabularyId = bookToVocabularyId;
    }

    public String getBookToVocabularyId()
    {
        return bookToVocabularyId;
    }

    public void setWordBookId(String wordBookId)
    {
        this.wordBookId = wordBookId;
    }

    public String getWordBookId()
    {
        return wordBookId;
    }

    public void setVocabularyId(String vocabularyId)
    {
        this.vocabularyId = vocabularyId;
    }

    public String getVocabularyId()
    {
        return vocabularyId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("bookToVocabularyId", getBookToVocabularyId())
                .append("wordBookId", getWordBookId())
                .append("vocabularyId", getVocabularyId())
                .toString();
    }
}