package cn.moyang.huoyouyou.service.impl;

import cn.moyang.huoyouyou.client.ProductDocClient;
import cn.moyang.huoyouyou.domain.*;
import cn.moyang.huoyouyou.index.ProductDoc;
import cn.moyang.huoyouyou.mapper.*;
import cn.moyang.huoyouyou.query.ProductQuery;
import cn.moyang.huoyouyou.service.IProductService;
import cn.moyang.huoyouyou.util.PageList;
import cn.moyang.huoyouyou.util.StrUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

/**
 * <p>
 * 商品 服务实现类
 * </p>
 *
 * @author hqtest
 * @since 2019-01-18
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductExtMapper productExtMapper;
    @Autowired
    private ProductDocClient productDocClient;
    @Autowired
    private SkuMapper skuMapper;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Override
    public PageList<Product> selectPageList(ProductQuery query) {
        Page<Product> page = new Page<>(query.getPage(), query.getRows());
        List<Product> list = productMapper.loadPageData(page, query);
        long total = page.getTotal();
        return new PageList<>(total, list);
    }

    @Override
    public void onSale(String ids, Integer onSale) {
        List<Long> idList = StrUtils.splitStr2LongArr(ids);
        if(onSale ==1 ){//上架
            //数据库中的状态和上架时间要修改
            Map<String,Object> params = new HashMap<>();
            params.put("idList",idList);
            params.put("timeStamp",new Date().getTime());
            productMapper.onSale(params);
            //添加都爱es库中
            List<ProductDoc> productDocs = product2productDoc(idList);
            productDocClient.batchSave(productDocs);
        }else {//下架
            //数据库中的状态和下架时间需要改变
            Map<String,Object> params = new HashMap<>();
            params.put("idList",idList);
            params.put("timeStamp",new Date().getTime());
            productMapper.offSale(params);

            //删除es库中的记录
            productDocClient.batchDel(idList);
        }
    }

    @Override
    public boolean insert(Product entity) {
        //除了要添加自己的，也还需要添加关联表的信息
        entity.setCreateTime(new Date().getTime());
        productMapper.insert(entity);
        //System.out.println(">>>>>>>>>>>>"+entity.getId());
        ProductExt ext = entity.getProductExt();
        if (ext != null) {
            ext.setProductId(entity.getId());
            productExtMapper.insert(ext);
        }
        return true;
    }

    @Override
    public boolean updateById(Product entity) {
        //除了更新自己的，也还需要更新关联表的信息
        entity.setCreateTime(new Date().getTime());
        productMapper.updateById(entity);

        //通过productId查询productExt
        System.out.println(entity.getId());
        Wrapper<ProductExt> entityWrapper =
                new EntityWrapper<ProductExt>().eq("productId", entity.getId());
        List<ProductExt> productExts = productExtMapper.selectList(entityWrapper);
        System.out.println(productExts);
        ProductExt productExt = productExts.get(0);

        //把前台传递进来值设置给数据库查询出来值,并且把它修改进去
        ProductExt ext = entity.getProductExt();
        if (ext != null) {
            productExt.setDescription(ext.getDescription());
            productExt.setRichContent(ext.getRichContent());
            productExtMapper.updateById(productExt);
        }

        //如果是上架状态，也需要更新es库
        if (entity.getState() == 1) {
            ProductDoc productDoc = product2productDoc(entity);
            productDocClient.save(productDoc);
        }
        return true;
    }

    /**
     * ElasticSearch
     * <p>
     * 覆写删除方法，当商品状态为上架的时候，删除时 同时删除es中的数据
     *
     * @param
     * @return
     */
    @Override
    public boolean deleteById(Serializable id) {
        super.deleteById(id);
        Product product = productMapper.selectById(id);
        if (product.getState() == 1) {
            productDocClient.del(Long.valueOf(id.toString()));
        }
        return true;
    }

    private List<ProductDoc> product2productDoc(List<Long> idList) {
        List<ProductDoc> productDocs = new ArrayList<>();
        for (Long aLong : idList) {
            Product product = productMapper.selectById(aLong);
            ProductDoc productDoc = product2productDoc(product);
            productDocs.add(productDoc);
        }
        return productDocs;
    }
    /**
     *  转换一个
     * @param product
     * @return
     */
    private ProductDoc product2productDoc(Product product) {
        //选中 alt+enter
        ProductDoc productDoc = new ProductDoc();
        productDoc.setId(product.getId());
        productDoc.setProductTypeId(product.getProductTypeId());
        productDoc.setBrandId(product.getBrandId());

        //从某个商品sku中获取最大或最小
        List<Sku> skuList = skuMapper.selectList(new EntityWrapper<Sku>().eq("productId", product.getId()));
        Integer maxPrice = skuList.get(0).getPrice();
        Integer minPrice = skuList.get(0).getPrice();
        for (Sku sku : skuList) {
            maxPrice = maxPrice < sku.getPrice() ? sku.getPrice() : maxPrice;
            minPrice = minPrice < sku.getPrice() ? minPrice : sku.getPrice();
        }
        productDoc.setMaxPrice(maxPrice);
        productDoc.setMinPrice(minPrice);

        productDoc.setSaleCount(product.getSaleCount());
        productDoc.setOnSaleTime(Integer.valueOf(product.getOnSaleTime()));
        productDoc.setCommentCount(product.getCommentCount());
        productDoc.setViewCount(product.getViewCount());

        String medias = product.getMedias();
        if (StringUtils.isNotBlank(medias))productDoc.setImages(Arrays.asList(medias.split(",")));


        Brand brand = brandMapper.selectById(product.getBrandId());
        ProductType productType = productTypeMapper.selectById(product.getProductTypeId());
        //投机-有空格就会分词
        String all = product.getName()+" "+
                product.getSubName()+" "+
                brand.getName()+" "+
                productType.getName()+" ";
        productDoc.setAll(all);

        productDoc.setViewProperties(product.getViewProperties());
        productDoc.setSkuProperties(product.getSkuTemplate());

        return productDoc;
    }

}

