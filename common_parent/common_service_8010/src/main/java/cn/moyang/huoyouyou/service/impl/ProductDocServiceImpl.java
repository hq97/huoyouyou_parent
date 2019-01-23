package cn.moyang.huoyouyou.service.impl;

import cn.moyang.huoyouyou.index.ProductDoc;
import cn.moyang.huoyouyou.repository.ProductDocReository;
import cn.moyang.huoyouyou.service.IProductDocService;
import cn.moyang.huoyouyou.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ElasticSearch
 */
@Service
public class ProductDocServiceImpl implements IProductDocService {
    @Autowired
    private ProductDocReository productDocReository;

    @Override
    public void add(ProductDoc productDoc) {
        productDocReository.save(productDoc);
    }

    @Override
    public void del(Long id) {
        productDocReository.deleteById(id);
    }

    @Override
    public ProductDoc get(Long id) {
        return productDocReository.findById(id).get();
    }

    @Override
    public void batchAdd(List<ProductDoc> productDocList) {
        productDocReository.saveAll(productDocList);
    }

    @Override
    public void batchDel(List<Long> idList) {
        //productDocReository.deleteAll(idList);
        for (Long aLong : idList) {
            productDocReository.deleteById(aLong);
        }
    }

    @Override
    public PageList<ProductDoc> search(Map<String, Object> params) {

        return null;
    }
}
