package cn.moyang.huoyouyou.mapper;

import cn.moyang.huoyouyou.domain.Product;
import cn.moyang.huoyouyou.query.ProductQuery;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品 Mapper 接口
 * </p>
 *
 * @author hqtest
 * @since 2019-01-18
 */
public interface ProductMapper extends BaseMapper<Product> {

    /**
     * 查询分页数据
     * @param page
     * @param query
     * @return
     */
    List<Product> loadPageData(Page<Product> page, ProductQuery query);

    /**
     * 上架
     * @param params
     */
    void onSale(Map<String, Object> params);

    /**
     * 下架
     * @param params
     */
    void offSale(Map<String, Object> params);
}
