package cn.moyang.huoyouyou.domain;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * SKU
 * </p>
 *
 * @author hqtest
 * @since 2019-01-22
 */
@TableName("t_sku")
public class Sku extends Model<Sku> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 商品ID
     */
    private Long productId;
    /**
     * 优惠价
     */
    private Integer price;
    /**
     * 可用库存
     */
    private Integer stock;
    /**
     * SKU属性摘要
     */
    private String skuValues;
    private Boolean state;
    private String indexs;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getSkuValues() {
        return skuValues;
    }

    public void setSkuValues(String skuValues) {
        this.skuValues = skuValues;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getIndexs() {
        return indexs;
    }

    public void setIndexs(String indexs) {
        this.indexs = indexs;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Sku{" +
        ", id=" + id +
        ", productId=" + productId +
        ", price=" + price +
        ", stock=" + stock +
        ", skuValues=" + skuValues +
        ", state=" + state +
        ", indexs=" + indexs +
        "}";
    }
}
