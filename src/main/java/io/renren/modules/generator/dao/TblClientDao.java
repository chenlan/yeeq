package io.renren.modules.generator.dao;

import io.renren.modules.generator.entity.TblClientEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客户表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-07-27 11:01:37
 */
@Mapper
public interface TblClientDao extends BaseMapper<TblClientEntity> {
	
}
