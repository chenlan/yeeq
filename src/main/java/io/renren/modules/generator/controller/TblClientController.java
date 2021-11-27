package io.renren.modules.generator.controller;

import java.util.*;

import io.renren.modules.generator.vo.resp.SelectClientVo;
import io.renren.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.generator.entity.TblClientEntity;
import io.renren.modules.generator.service.TblClientService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 客户表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-07-27 11:01:37
 */
@RestController
@RequestMapping("generator/tblclient")
public class TblClientController extends AbstractController {
    @Autowired
    private TblClientService tblClientService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:tblclient:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = tblClientService.queryPage(params);
        return R.ok().put("page", page);
    }

    @RequestMapping("/allList")
    @RequiresPermissions("generator:tblclient:list")
    public R allList(@RequestParam Map<String, Object> params){
        List<TblClientEntity> tblClientEntityList= tblClientService.queryAll(params);
        List<SelectClientVo> clientList = new ArrayList<>();
        for (TblClientEntity clientEntity:tblClientEntityList) {
            SelectClientVo clientVo = new SelectClientVo();
            BeanUtils.copyProperties(clientEntity, clientVo);
            clientList.add(clientVo);
        }
        return R.ok().put("clientList", clientList);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:tblclient:info")
    public R info(@PathVariable("id") Integer id){
		TblClientEntity tblClient = tblClientService.getById(id);

        return R.ok().put("tblClient", tblClient);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:tblclient:save")
    public R save(@RequestBody TblClientEntity tblClient){
        Date curDate = new Date();
        tblClient.setCreaterTime(curDate);
        tblClient.setUpdateTime(curDate);
        tblClient.setOperatorName(getUserName());
		tblClientService.save(tblClient);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:tblclient:update")
    public R update(@RequestBody TblClientEntity tblClient){
        tblClient.setUpdateTime(new Date());
        tblClient.setOperatorName(getUserName());
		tblClientService.updateById(tblClient);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:tblclient:delete")
    public R delete(@RequestBody Integer[] ids){
		tblClientService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
