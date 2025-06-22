package com.remember.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.GenerateId;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 词汇书对象 word_book
 * 
 * @author xxxz_left
 * @date 2025-05-15
 */
public class WordBook extends BaseEntity
{
    private static final long serialVersionUID = 1L;


    /** 主键 */
    @GenerateId
    private String wordBookId;

    /** 词汇书名称 */
    @Excel(name = "词汇书名称")
    private String wordBookName;

    /** 词汇量 */
    @Excel(name = "词汇量")
    private Integer wordNums;

    /** 贡献者 */
    @Excel(name = "贡献者")
    private String superPersonnel;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateDate;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

    public void setWordBookId(String wordBookId) 
    {
        this.wordBookId = wordBookId;
    }

    public String getWordBookId() 
    {
        return wordBookId;
    }

    public void setWordBookName(String wordBookName) 
    {
        this.wordBookName = wordBookName;
    }

    public String getWordBookName() 
    {
        return wordBookName;
    }

    public void setWordNums(Integer wordNums) 
    {
        this.wordNums = wordNums;
    }

    public Integer getWordNums() 
    {
        return wordNums;
    }

    public void setSuperPersonnel(String superPersonnel) 
    {
        this.superPersonnel = superPersonnel;
    }

    public String getSuperPersonnel() 
    {
        return superPersonnel;
    }

    public void setUpdateDate(Date updateDate) 
    {
        this.updateDate = updateDate;
    }

    public Date getUpdateDate() 
    {
        return updateDate;
    }

    public void setCreateDate(Date createDate) 
    {
        this.createDate = createDate;
    }

    public Date getCreateDate() 
    {
        return createDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("wordBookId", getWordBookId())
            .append("wordBookName", getWordBookName())
            .append("wordNums", getWordNums())
            .append("superPersonnel", getSuperPersonnel())
            .append("updateDate", getUpdateDate())
            .append("createDate", getCreateDate())
            .toString();
    }
}
