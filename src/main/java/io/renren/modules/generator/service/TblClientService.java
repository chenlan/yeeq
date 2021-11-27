package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.generator.entity.TblClientEntity;

import java.util.List;
import java.util.Map;

/**
 * 客户表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-07-27 11:01:37
 */
public interface TblClientService extends IService<TblClientEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<TblClientEntity> queryAll(Map<String, Object> params);

}

