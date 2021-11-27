package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.generator.common.ProductConstants;
import io.renren.modules.generator.entity.TblProductEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-11-16 17:36:38
 */
public interface TblProductService extends IService<TblProductEntity> {

    PageUtils queryPage(Map<String, Object> params);
    List getProductTypeList();

}

