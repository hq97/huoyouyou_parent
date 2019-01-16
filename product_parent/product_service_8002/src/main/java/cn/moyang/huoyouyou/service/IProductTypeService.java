package cn.moyang.huoyouyou.service;

import cn.moyang.huoyouyou.domain.ProductType;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 商品目录 服务类
 * </p>
 *
 * @author hqtest
 * @since 2019-01-14
 */
public interface IProductTypeService extends IService<ProductType> {

    /**
     * 获取无限极数据
     * @return
     */
    List<ProductType> treeData();
}
