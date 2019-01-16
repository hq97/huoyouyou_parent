package cn.moyang.huoyouyou.mapper;

import cn.moyang.huoyouyou.domain.Brand;
import cn.moyang.huoyouyou.query.BrandQuery;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

/**
 * <p>
 * 品牌信息 Mapper 接口
 * </p>
 *
 * @author hqtest
 * @since 2019-01-14
 */
public interface BrandMapper extends BaseMapper<Brand> {
    /**
     * 查询分页数据
     * @param page
     * @param query
     * @return
     */
    List<Brand> selectPageList(Page<Brand> page, BrandQuery query);
}
