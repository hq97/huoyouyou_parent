package cn.moyang.huoyouyou.service;

import cn.moyang.huoyouyou.index.ProductDoc;
import cn.moyang.huoyouyou.util.PageList;

import java.util.List;
import java.util.Map;

public interface IProductDocService {
    /**
     * 添加
     * @param productDoc
     */
    void add(ProductDoc productDoc);

    /**
     * 删除
     * @param id
     */
    void del (Long id);

    /**
     * 获取一条数据
     * @param id
     * @return
     */
    ProductDoc get(Long id);

    /**
     * 批量添加修改
     * @param productDocList
     */
    void batchAdd(List<ProductDoc> productDocList);

    /**
     * 批量删除
     * @param idList
     */
    void  batchDel(List<Long> idList);

    /**
     * 分页搜索
     * @param params
     * @return
     */
    PageList<ProductDoc> search(Map<String ,Object> params);
}
