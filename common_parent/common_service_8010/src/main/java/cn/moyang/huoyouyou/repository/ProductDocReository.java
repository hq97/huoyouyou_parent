package cn.moyang.huoyouyou.repository;

import cn.moyang.huoyouyou.index.ProductDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDocReository extends ElasticsearchRepository<ProductDoc,Long> {
}
