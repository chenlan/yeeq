package io.renren.modules.generator.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.renren.modules.generator.common.ProductConstants;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.generator.entity.TblProductEntity;
import io.renren.modules.generator.service.TblProductService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-11-16 17:36:38
 */
@RestController
@RequestMapping("generator/tblproduct")
public class TblProductController {
    @Autowired
    private TblProductService tblProductService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:tblproduct:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = tblProductService.queryPage(params);

        return R.ok().put("page", page);
    }


    @RequestMapping("/getProductTypeList")
    @RequiresPermissions("generator:tblproduct:list")
    public R getProductTypeList(){
        List productDic = tblProductService.getProductTypeList();
        return R.ok().put("productDic", productDic);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:tblproduct:info")
    public R info(@PathVariable("id") Integer id){
		TblProductEntity tblProduct = tblProductService.getById(id);

        return R.ok().put("tblProduct", tblProduct);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:tblproduct:save")
    public R save(@RequestBody TblProductEntity tblProduct){
		tblProductService.save(tblProduct);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:tblproduct:update")
    public R update(@RequestBody TblProductEntity tblProduct){
		tblProductService.updateById(tblProduct);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:tblproduct:delete")
    public R delete(@RequestBody Integer[] ids){
		tblProductService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
