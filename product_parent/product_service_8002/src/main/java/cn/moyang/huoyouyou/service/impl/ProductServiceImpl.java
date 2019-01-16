package cn.moyang.huoyouyou.service.impl;

import cn.moyang.huoyouyou.domain.Product;
import cn.moyang.huoyouyou.mapper.ProductMapper;
import cn.moyang.huoyouyou.service.IProductService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品 服务实现类
 * </p>
 *
 * @author hqtest
 * @since 2019-01-14
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
