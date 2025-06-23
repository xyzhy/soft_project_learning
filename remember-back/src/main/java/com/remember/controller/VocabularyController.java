package com.remember.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.ruoyi.common.utils.http.HttpRequestUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.remember.domain.Vocabulary;
import com.remember.service.IVocabularyService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 这是词, 和词汇做链接Controller
 * 
 * @author ruoyi
 * @date 2025-05-26
 */
@RestController
@RequestMapping("/remember/vocabulary")
public class VocabularyController extends BaseController
{
    @Autowired
    private IVocabularyService vocabularyService;

    /**
     * 通过上传的 excel 表格来添加词汇
     * @return
     */
    @PreAuthorize("@ss.hasPermi('remember:vocabulary:add')")
    @PostMapping("/excel/addition")
    public AjaxResult addBatchVocabByExcel(@RequestParam("file") MultipartFile file, @RequestParam("wordBookId") String wordBookId) throws IOException, InterruptedException {
        JSONObject resp = JSON.parseObject(HttpRequestUtils.sendPostByBytes("/check/excel?wordBookId="+wordBookId, file.getBytes()));
        if (resp.getIntValue("code") == 400) {
            return error(resp.getString("msg"));
        }

        List<Vocabulary> vocabularies = resp.getObject("data", new TypeReference<List<Vocabulary>>() {});
        return this.addBatchVocab(vocabularies);
    }

    /**
     * 批量添加词汇
     * @param vocabulary 词汇
     * @return 成功提示
     */
    @PreAuthorize("@ss.hasPermi('remember:vocabulary:add')")
    @PostMapping("/batchAdd")
    public AjaxResult addBatchVocab(@RequestBody List<Vocabulary> vocabulary) {
        int total_numeric = vocabulary.size();
        int success_numeric = vocabularyService.insertBatchVocabulary(vocabulary);
        return success(String.format("总共%s条数据, 操作成功%s条", total_numeric, success_numeric));
    }

    /**
     * 查询这是词, 和词汇做链接列表
     */
    @PreAuthorize("@ss.hasPermi('remember:vocabulary:list')")
    @GetMapping("/list")
    public TableDataInfo list(Vocabulary vocabulary)
    {
        startPage();
        List<Vocabulary> list = vocabularyService.selectVocabularyList(vocabulary);
        return getDataTable(list);
    }

    /**
     * 导出这是词, 和词汇做链接列表
     */
    @PreAuthorize("@ss.hasPermi('remember:vocabulary:export')")
    @Log(title = "这是词, 和词汇做链接", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Vocabulary vocabulary)
    {
        List<Vocabulary> list = vocabularyService.selectVocabularyList(vocabulary);
        ExcelUtil<Vocabulary> util = new ExcelUtil<Vocabulary>(Vocabulary.class);
        util.exportExcel(response, list, "这是词, 和词汇做链接数据");
    }

    /**
     * 获取这是词, 和词汇做链接详细信息
     */
    @PreAuthorize("@ss.hasPermi('remember:vocabulary:query')")
    @GetMapping(value = "/{vocabularyId}")
    public AjaxResult getInfo(@PathVariable("vocabularyId") String vocabularyId)
    {
        return success(vocabularyService.selectVocabularyByVocabularyId(vocabularyId));
    }

    /**
     * 新增这是词, 和词汇做链接
     */
    @PreAuthorize("@ss.hasPermi('remember:vocabulary:add')")
    @Log(title = "这是词, 和词汇做链接", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Vocabulary vocabulary)
    {
        return toAjax(vocabularyService.insertVocabulary(vocabulary));
    }

    /**
     * 修改这是词, 和词汇做链接
     */
    @PreAuthorize("@ss.hasPermi('remember:vocabulary:edit')")
    @Log(title = "这是词, 和词汇做链接", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Vocabulary vocabulary)
    {
        return toAjax(vocabularyService.updateVocabulary(vocabulary));
    }

    /**
     * 删除这是词, 和词汇做链接
     */
    @PreAuthorize("@ss.hasPermi('remember:vocabulary:remove')")
    @Log(title = "这是词, 和词汇做链接", businessType = BusinessType.DELETE)
	@DeleteMapping("/{vocabularyIds}")
    public AjaxResult remove(@PathVariable String[] vocabularyIds)
    {
        return toAjax(vocabularyService.deleteVocabularyByVocabularyIds(vocabularyIds));
    }
}
