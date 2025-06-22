package com.remember.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.remember.domain.Vocabulary;
import com.remember.service.IVocabularyService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

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
