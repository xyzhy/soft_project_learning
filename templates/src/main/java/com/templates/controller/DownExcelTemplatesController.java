package com.templates.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.templates.excel.VocabWordTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/templates/excel")
public class DownExcelTemplatesController {

    /**
     * 此方法用来导出, 根据传入的class进行导出
     * @param response
     * @param tClass
     * @param <T>
     */
    private <T> void export(HttpServletResponse response, Class<T> tClass) {
        ExcelUtil<T> util = new ExcelUtil<T>(tClass);
        util.exportExcel(response, null, "templates");
    }
    /**
     * 此方法用来导出词典模型
     * @param response
     */
    @RequestMapping("/vocabWord")
    public void vocabTemplate(HttpServletResponse response) {
        export(response, VocabWordTemplate.class);
    }
}
