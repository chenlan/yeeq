package io.renren.modules.generator.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.renren.modules.generator.entity.TblWorksiteEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.generator.vo.resp.WorksiteVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 工地表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-07-27 11:01:36
 */
@Mapper
public interface TblWorksiteDao extends BaseMapper<TblWorksiteEntity> {

    IPage<WorksiteVo> searchList(IPage<WorksiteVo> page, @Param("req") Map<String, Object> req);

    WorksiteVo getWorksitById(@Param("worksiteId")Integer id);
}
