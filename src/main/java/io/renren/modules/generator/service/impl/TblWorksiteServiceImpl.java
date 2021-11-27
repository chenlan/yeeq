package io.renren.modules.generator.service.impl;

import io.renren.modules.generator.vo.resp.WorksiteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.TblWorksiteDao;
import io.renren.modules.generator.entity.TblWorksiteEntity;
import io.renren.modules.generator.service.TblWorksiteService;


@Service("tblWorksiteService")
public class TblWorksiteServiceImpl extends ServiceImpl<TblWorksiteDao, TblWorksiteEntity> implements TblWorksiteService {

    @Autowired
    private TblWorksiteDao tblWorksiteDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
       // String worksite_name = (String)params.get("worksite_name");
       // String client_name = (String)params.get("link_name");productType

        IPage page = new Query<WorksiteVo>().getPage(params);
//        IPage<TblWorksiteEntity> pageResult = this.page(page
//                ,
//                new QueryWrapper<TblWorksiteEntity>()
//                        .like(StringUtils.isNotBlank(worksite_name),"name", worksite_name).or()
//                        .like(StringUtils.isNotBlank(link_name),"client_name", worksite_name)
//        );

        IPage pageResult = tblWorksiteDao.searchList(page,params);
        return new PageUtils(pageResult);
    }



    @Override
    public WorksiteVo getWorksiteById(Integer id) {
        return tblWorksiteDao.getWorksitById(id);
    }


}