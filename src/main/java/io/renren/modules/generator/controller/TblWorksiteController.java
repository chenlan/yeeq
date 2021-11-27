package io.renren.modules.generator.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import io.renren.modules.generator.vo.resp.WorksiteVo;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.generator.entity.TblWorksiteEntity;
import io.renren.modules.generator.service.TblWorksiteService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 工地表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-07-27 11:01:36
 */
@RestController
@RequestMapping("generator/tblworksite")
public class TblWorksiteController extends AbstractController {
    @Autowired
    private TblWorksiteService tblWorksiteService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:tblworksite:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = tblWorksiteService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:tblworksite:info")
    public R info(@PathVariable("id") Integer id){
		WorksiteVo tblWorksite = tblWorksiteService.getWorksiteById(id);
        return R.ok().put("tblWorksite", tblWorksite);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:tblworksite:save")
    public R save(@RequestBody TblWorksiteEntity tblWorksite){
        Date curDate = new Date();
        tblWorksite.setCreaterTime(curDate);
        tblWorksite.setUpdateTime(curDate);
        tblWorksite.setOperator(getUserName());
		tblWorksiteService.save(tblWorksite);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:tblworksite:update")
    public R update(@RequestBody TblWorksiteEntity tblWorksite){
        Date curDate = new Date();
        tblWorksite.setUpdateTime(curDate);
        tblWorksite.setOperator(getUserName());
		tblWorksiteService.updateById(tblWorksite);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:tblworksite:delete")
    public R delete(@RequestBody Integer[] ids){
		tblWorksiteService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
