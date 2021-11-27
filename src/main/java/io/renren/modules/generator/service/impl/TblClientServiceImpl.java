package io.renren.modules.generator.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.TblClientDao;
import io.renren.modules.generator.entity.TblClientEntity;
import io.renren.modules.generator.service.TblClientService;


@Service("tblClientService")
public class TblClientServiceImpl extends ServiceImpl<TblClientDao, TblClientEntity> implements TblClientService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String clientname = (String)params.get("clientname");

        IPage<TblClientEntity> page = this.page(
                new Query<TblClientEntity>().getPage(params),
                new QueryWrapper<TblClientEntity>().like(StringUtils.isNotBlank(clientname),"name", clientname)
        );

        return new PageUtils(page);
    }

    @Override
    public List<TblClientEntity> queryAll(Map<String, Object> params) {
        return this.listByMap(params);
    }

}