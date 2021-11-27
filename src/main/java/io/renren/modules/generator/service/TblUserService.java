package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.generator.entity.TblUserEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-05-13 12:33:55
 */
public interface TblUserService extends IService<TblUserEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

