package io.renren.modules.generator.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.generator.entity.TblUserEntity;
import io.renren.modules.generator.service.TblUserService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-05-13 12:33:55
 */
@RestController
@RequestMapping("generator/tbluser")
public class TblUserController {
    @Autowired
    private TblUserService tblUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:tbluser:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = tblUserService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:tbluser:info")
    public R info(@PathVariable("id") Integer id){
		TblUserEntity tblUser = tblUserService.getById(id);

        return R.ok().put("tblUser", tblUser);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:tbluser:save")
    public R save(@RequestBody TblUserEntity tblUser){
		tblUserService.save(tblUser);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:tbluser:update")
    public R update(@RequestBody TblUserEntity tblUser){
		tblUserService.updateById(tblUser);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:tbluser:delete")
    public R delete(@RequestBody Integer[] ids){
		tblUserService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
