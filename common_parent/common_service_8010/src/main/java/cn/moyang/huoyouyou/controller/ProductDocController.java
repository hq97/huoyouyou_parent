package cn.moyang.huoyouyou.controller;

import cn.moyang.huoyouyou.client.ProductDocClient;
import cn.moyang.huoyouyou.index.ProductDoc;
import cn.moyang.huoyouyou.service.IProductDocService;
import cn.moyang.huoyouyou.util.AjaxResult;
import cn.moyang.huoyouyou.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ProductDocController implements ProductDocClient {
    @Autowired
    private IProductDocService productDocService;
    /**
     * productDoc
     * 添加修改
     *
     * @param productDoc
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @Override
    public AjaxResult save(ProductDoc productDoc) {
        try {
            productDocService.add(productDoc);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("操作失败");
        }

    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Override
    public AjaxResult del(@PathVariable("id") Long id) {
        try {
            productDocService.del(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除失败");
        }
    }

    /**
     * 获取一条记录
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Override
    public ProductDoc get(@PathVariable("id")Long id) {
        return productDocService.get(id);
    }

    /**
     * 批量添加修改
     *
     * @param productDocList
     * @return
     */
    @RequestMapping(value = "/batchSave", method = RequestMethod.POST)
    @Override
    public AjaxResult batchSave(@RequestBody List<ProductDoc> productDocList) {
        try {
            productDocService.batchAdd(productDocList);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("批量操作失败");
        }
    }

    /**
     * 批量删除
     *
     * @param idList
     * @return
     */
    @RequestMapping(value = "/batchDel", method = RequestMethod.DELETE)
    @Override
    public AjaxResult batchDel(@RequestBody List<Long> idList) {
        try {
            productDocService.batchDel(idList);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("批量删除失败");
        }
    }

    /**
     * 分页搜索
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @Override
    public PageList<ProductDoc> search(Map<String, Object> params) {
        return  productDocService.search(params);
    }
}
