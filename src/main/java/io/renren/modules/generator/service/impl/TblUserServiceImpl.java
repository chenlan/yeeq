package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.TblUserDao;
import io.renren.modules.generator.entity.TblUserEntity;
import io.renren.modules.generator.service.TblUserService;


@Service("tblUserService")
public class TblUserServiceImpl extends ServiceImpl<TblUserDao, TblUserEntity> implements TblUserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TblUserEntity> page = this.page(
                new Query<TblUserEntity>().getPage(params),
                new QueryWrapper<TblUserEntity>()
        );

        return new PageUtils(page);
    }



}