package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.generator.entity.TblWorksiteEntity;
import io.renren.modules.generator.vo.resp.WorksiteVo;

import java.util.Map;

/**
 * 工地表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-07-27 11:01:36
 */
public interface TblWorksiteService extends IService<TblWorksiteEntity> {

    PageUtils queryPage(Map<String, Object> params);

    WorksiteVo getWorksiteById(Integer id);
}

