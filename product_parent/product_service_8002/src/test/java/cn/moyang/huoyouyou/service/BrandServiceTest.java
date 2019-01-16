package cn.moyang.huoyouyou.service;

import cn.moyang.huoyouyou.ProductServiceApplication_8002;
import cn.moyang.huoyouyou.domain.Brand;
import cn.moyang.huoyouyou.query.BrandQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductServiceApplication_8002.class)
public class BrandServiceTest {

    @Autowired
    private IBrandService brandService;

    @Test
    public void test() throws Exception {
        BrandQuery query = new BrandQuery();
        query.setKeyword("七");
       /*  PageList<Brand> pagelist = brandService.selectPageList(query);
         System.out.println(pagelist.getTotal());
         List<Brand> rows = pagelist.getRows();
         for (Brand row : rows) {
             System.out.println(row);
             System.out.println(row.getProductType());

         }
*/

    }

    @Test
    public void test02() {
        System.out.println(brandService);
       Brand brand = brandService.selectById(3L);
        System.out.println(brand);
    }
}
