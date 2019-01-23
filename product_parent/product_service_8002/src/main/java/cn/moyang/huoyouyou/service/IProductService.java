package cn.moyang.huoyouyou.service;

import cn.moyang.huoyouyou.domain.Product;
import cn.moyang.huoyouyou.query.ProductQuery;
import cn.moyang.huoyouyou.util.PageList;
import com.baomidou.mybatisplus.service.IService;

import java.io.Serializable;

/**
 * <p>
 * 商品 服务类
 * </p>
 *
 * @author hqtest
 * @since 2019-01-18
 */
public interface IProductService extends IService<Product> {
    /**
     * 分页查询数据
     * @param query
     * @return
     */
    PageList<Product> selectPageList(ProductQuery query);


    void onSale(String ids, Integer onSale);
}
