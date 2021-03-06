package cn.moyang.huoyouyou.controller;

import cn.moyang.huoyouyou.domain.Brand;
import cn.moyang.huoyouyou.query.BrandQuery;
import cn.moyang.huoyouyou.service.IBrandService;
import cn.moyang.huoyouyou.util.AjaxResult;
import cn.moyang.huoyouyou.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    public IBrandService brandService;

    /**
    * 保存和修改公用的
    * @param brand  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Brand brand){
        try {
            if(brand.getId()!=null){
                brandService.updateById(brand);
            }else{
                brandService.insert(brand);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage());
        }
    }

    /**
    * 批量删除删除对象信息
    * @param
    * @return
    */
    @RequestMapping(value="/ids/{ids}",method=RequestMethod.DELETE)
    public AjaxResult del(@PathVariable("ids") String ids){
        try {
            String[] strings = ids.split(",");
            brandService.deleteBatchIds(Arrays.asList(strings));
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }
    /**
     * 删除对象信息
     * @param id
     * @return
     */
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            brandService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Brand get(@PathVariable("id")Long id)
    {
        return brandService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Brand> list(){

        return brandService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Brand> json(@RequestBody BrandQuery query)
    {
      /*  Page<Brand> page = new Page<Brand>(query.getPage(),query.getRows());
            page = brandService.selectPage(page);
            return new PageList<Brand>(page.getTotal(),page.getRecords());*/
      return brandService.selectPageList(query);
    }
}
