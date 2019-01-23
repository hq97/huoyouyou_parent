package cn.moyang.huoyouyou.index;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.ArrayList;
import java.util.List;

@Document(indexName = "huoyouyou",type = "product")
public class ProductDoc {
    @Id
    private Long id;
    /**
     * 商品品牌
     *
     */
    private Long brandId;
    /**
     * 商品类型
     */
    private Long productTypeId;
    /**
     * 最低价
     */
    private Integer minPrice;
    /**
     * 最高价
     */
    private Integer maxPrice;
    /**
     * 销量
     */
    private Integer saleCount;
    /**
     * 评论总数
     */
    private Integer commentCount;
    /**
     * 浏览总数
     */
    private Integer viewCount;

    /**
     * 上架时间
     */
    private Integer onSaleTime;
    /**
     * 图片
     */
    @Field(type = FieldType.Keyword)
    List<String> images = new ArrayList<>();
    /**
     * 模糊查询的所有字段
     *  name ,subname, productType Name,brand  name,
     */
    @Field(type = FieldType.Text ,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String all;
    /**
     *显示属性
     */
    @Field(type = FieldType.Keyword)
    private String viewProperties;
    /**
     * sku属性
     */
    @Field(type = FieldType.Keyword)
    private String skuProperties;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getOnSaleTime() {
        return onSaleTime;
    }

    public void setOnSaleTime(Integer onSaleTime) {
        this.onSaleTime = onSaleTime;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public String getViewProperties() {
        return viewProperties;
    }

    public void setViewProperties(String viewProperties) {
        this.viewProperties = viewProperties;
    }

    public String getSkuProperties() {
        return skuProperties;
    }

    public void setSkuProperties(String skuProperties) {
        this.skuProperties = skuProperties;
    }
}
