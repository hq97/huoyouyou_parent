package cn.moyang.huoyouyou.service.impl;

import cn.moyang.huoyouyou.client.PageClient;
import cn.moyang.huoyouyou.client.RedisClient;
import cn.moyang.huoyouyou.domain.ProductType;
import cn.moyang.huoyouyou.mapper.ProductTypeMapper;
import cn.moyang.huoyouyou.service.IProductTypeService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品目录 服务实现类
 * </p>
 *
 * @author hqtest
 * @since 2019-01-14
 */
@Service
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements IProductTypeService {
    private String productType_in_redis = "productType_in_redis";
    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private PageClient pageClient;

    /**
     * @return
     */
    @Override
    public List<ProductType> treeData() {

        // 1 递归方案效率低,会发多次sql语句
        //return getTreeDataRecursion(0L);
        // 2 循环方案,只需发一条sql
        String productTypeInRedis = redisClient.get(productType_in_redis);
        if (StringUtils.isNotBlank(productTypeInRedis)) {//为true  表示有缓存
            System.out.println("缓存：？？？？？？？？？？？");
            return JSONArray.parseArray(productTypeInRedis, ProductType.class);//返回缓存中的数据
        } else {
            System.out.println("数据库：？？？？？？？？？？？？");
            //存入缓存
            List<ProductType> productTypes = getTreeDataLoop(0L);
            redisClient.set(productType_in_redis, JSONObject.toJSONString(productTypes));
            return productTypes;
        }
    }

    /**
     * 将数据封装成树的格式的数据
     *
     * @param l
     * @return
     */
    private List<ProductType> getTreeDataLoop(long l) {
        //返回数据 一级类型,子子孙孙类型下面挂了
        List<ProductType> result = new ArrayList<>();
        //1 获取所有的类型数据
        List<ProductType> productTypes = productTypeMapper.selectList(null);
        //2)遍历所有的类型
        Map<Long, ProductType> productTypesDto = new HashMap<>();
        for (ProductType productType : productTypes) {
            productTypesDto.put(productType.getId(), productType);
        }
        for (ProductType productType : productTypes) {
            Long pid = productType.getPid();
            // ①如果没有父亲就是一级类型 放入返回列表中
            if (pid.longValue() == 0) {
                result.add(productType);
            } else {
                //方案2:通过Map建立id和类型直接关系,以后通过pid直接获取父亲
                ProductType parent = productTypesDto.get(pid);
                parent.getChildren().add(productType);
            }

        }
        return result;
    }

    /**
     * 每一次删除添加修改都会修改数据库中的值，此时静态页面也也需要更新覆盖
     */
    private void synchronizedOpr(){
        //更新缓存中的数据
        List<ProductType> productTypes = getTreeDataLoop(0L);
        redisClient.set(productType_in_redis,JSONObject.toJSONString(productTypes));
        //更新页面
        //静态化产品类型类
        Map<String ,Object> productTypeParams = new HashMap<>();
        productTypeParams.put("model",productTypes);
        productTypeParams.put("templatePath","G:\\itsorceworkspace_idea\\huoyouyou_parent\\product_parent\\product_service_8002\\src\\main\\resources\\template\\productType\\product.type.vm");
        productTypeParams.put("staticPagePath","G:\\itsorceworkspace_idea\\huoyouyou_parent\\product_parent\\product_service_8002\\src\\main\\resources\\template\\productType\\product.type.vm.html");
        pageClient.genStaticPage(productTypeParams);

        //在静态化主页
        Map<String ,Object> indexParams = new HashMap<>();
        Map<String ,Object> modelMap= new HashMap<>();
        modelMap.put("staticRoot","G:\\itsorceworkspace_idea\\huoyouyou_parent\\product_parent\\product_service_8002\\src\\main\\resources\\");
        indexParams.put("model",modelMap);
        indexParams.put("templatePath","G:\\itsorceworkspace_idea\\huoyouyou_parent\\product_parent\\product_service_8002\\src\\main\\resources\\template\\home.vm");
        indexParams.put("staticPagePath","G:\\itsorceworkspace_idea\\huoyouyou_web_parent\\huoyouyou_static_web\\home.html");
        pageClient.genStaticPage(indexParams);
    }
    @Override
    public boolean insert(ProductType entity) {
        boolean insert = super.insert(entity);
        synchronizedOpr();
        return insert;
    }

    @Override
    public boolean deleteById(Serializable id) {
        boolean b = super.deleteById(id);
        synchronizedOpr();
        return b;
    }

    @Override
    public boolean updateById(ProductType entity) {
        boolean b = super.updateById(entity);
        synchronizedOpr();
        return b;
    }


    /**
     * 递归获取无限极数据
     * ①自己调用自己
     * ②要有出口
     *
     * @return
     */
    private List<ProductType> getTreeDataRecursion(Long id) {

        //0
        List<ProductType> children = getAllChildren(id); //1 2
        //出口
        if (children == null || children.size() < 1) {
            return null;
        }
        for (ProductType productType : children) {
            //1 3 4 自己调用自己
            List<ProductType> children1 = getTreeDataRecursion(productType.getId());
            productType.setChildren(children1);
        }
        return children;

    }

    /**
     * 根据id获取数据
     *
     * @param pid
     * @return
     */
    private List<ProductType> getAllChildren(Long pid) {
        Wrapper wrapper = new EntityWrapper<ProductType>();
        wrapper.eq("pid", pid);
        return productTypeMapper.selectList(wrapper);
    }

}
