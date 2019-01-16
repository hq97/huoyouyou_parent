package cn.moyang.huoyouyou.service;

import cn.moyang.huoyouyou.domain.Brand;
import cn.moyang.huoyouyou.query.BrandQuery;
import cn.moyang.huoyouyou.util.PageList;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 品牌信息 服务类
 * </p>
 *
 * @author hqtest
 * @since 2019-01-14
 */
public interface IBrandService extends IService<Brand> {
    /**
     * 分页查询,关联对象
     * @param query
     * @return
     */
    PageList<Brand> selectPageList(BrandQuery query);
}
