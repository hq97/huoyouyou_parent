package cn.moyang.huoyouyou.client;

import cn.moyang.huoyouyou.index.ProductDoc;
import cn.moyang.huoyouyou.util.AjaxResult;
import cn.moyang.huoyouyou.util.PageList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@FeignClient(value = "HUOYOUYOU-COMMON",fallbackFactory = PageClientFallbackFactory.class)
@RequestMapping("/productDoc")
public interface ProductDocClient {
    /**
     * productDoc
     * 添加修改
     * @param productDoc
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    AjaxResult save(ProductDoc productDoc);

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    AjaxResult del(@PathVariable("id") Long id);

    /**
     * 获取一条记录
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    ProductDoc get(@PathVariable("id") Long id);

    /**
     * 批量添加修改
     * @param productDocList
     * @return
     */
    @RequestMapping(value = "/batchSave",method = RequestMethod.POST)
    AjaxResult batchSave(@RequestBody List<ProductDoc> productDocList);

    /**
     * 批量删除
     * @param idList
     * @return
     */
    @RequestMapping(value = "/batchDel",method = RequestMethod.DELETE)
    AjaxResult batchDel(@RequestBody List<Long> idList);

    /**
     * 分页搜索
     * @param params
     * @return
     */
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    PageList<ProductDoc> search(Map<String ,Object> params);
}
