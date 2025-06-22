package com.templates.excel;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;

@Data
public class VocabWordTemplate {
    @Excel(name = "英文")
    private String english;

    @Excel(name = "词性: 词1,词2;")
    private String means;
}
