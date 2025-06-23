package com.remember.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.remember.domain.WordBook;
import com.remember.service.IWordBookService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 词汇书Controller
 * 
 * @author xxxz_left
 * @date 2025-05-15
 */
@RestController
@RequestMapping("/remember/wordBook")
public class WordBookController extends BaseController
{
    private final IWordBookService wordBookService;

    public WordBookController(IWordBookService wordBookService) {
        this.wordBookService = wordBookService;
    }

    /**
     * 查询词汇书列表
     */
    @PreAuthorize("@ss.hasPermi('remember:wordBook:list')")
    @GetMapping("/list")
    public TableDataInfo list(WordBook wordBook)
    {
        startPage();
        List<WordBook> list = wordBookService.selectWordBookList(wordBook);
        return getDataTable(list);
    }

    /**
     * 导出词汇书列表
     */
    @PreAuthorize("@ss.hasPermi('remember:wordBook:export')")
    @Log(title = "词汇书", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WordBook wordBook)
    {
        List<WordBook> list = wordBookService.selectWordBookList(wordBook);
        ExcelUtil<WordBook> util = new ExcelUtil<>(WordBook.class);
        util.exportExcel(response, list, "词汇书数据");
    }

    /**
     * 获取词汇书详细信息
     */
    @PreAuthorize("@ss.hasPermi('remember:wordBook:query')")
    @GetMapping(value = "/{wordBookId}")
    public AjaxResult getInfo(@PathVariable("wordBookId") String wordBookId)
    {
        return success(wordBookService.selectWordBookByWordBookId(wordBookId));
    }

    /**
     * 新增词汇书
     */
    @PreAuthorize("@ss.hasPermi('remember:wordBook:add')")
    @Log(title = "词汇书", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WordBook wordBook)
    {
        return toAjax(wordBookService.insertWordBook(wordBook));
    }

    /**
     * 修改词汇书
     */
    @PreAuthorize("@ss.hasPermi('remember:wordBook:edit')")
    @Log(title = "词汇书", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WordBook wordBook)
    {
        return toAjax(wordBookService.updateWordBook(wordBook));
    }

    /**
     * 删除词汇书
     */
    @PreAuthorize("@ss.hasPermi('remember:wordBook:remove')")
    @Log(title = "词汇书", businessType = BusinessType.DELETE)
	@DeleteMapping("/{wordBookIds}")
    public AjaxResult remove(@PathVariable String[] wordBookIds)
    {
        return toAjax(wordBookService.deleteWordBookByWordBookIds(wordBookIds));
    }
}
