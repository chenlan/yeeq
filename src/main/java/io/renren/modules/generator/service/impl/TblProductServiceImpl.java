package io.renren.modules.generator.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.renren.modules.generator.common.ProductConstants;
import io.renren.modules.generator.vo.resp.ProductVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.TblProductDao;
import io.renren.modules.generator.entity.TblProductEntity;
import io.renren.modules.generator.service.TblProductService;


@Service("tblProductService")
public class TblProductServiceImpl extends ServiceImpl<TblProductDao, TblProductEntity> implements TblProductService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String productType = params.get("productType").toString();

        IPage<TblProductEntity> page = this.page(
                new Query<TblProductEntity>().getPage(params),
                productType.equals("")? new QueryWrapper<TblProductEntity>():new QueryWrapper<TblProductEntity>().eq("product_type", Integer.parseInt(productType))
        );

        List<ProductVo> productVoList = new ArrayList<>();
        for (TblProductEntity entity: page.getRecords()) {
            ProductVo productVo = new ProductVo();
            BeanUtils.copyProperties(entity, productVo);
            productVo.setProductTypeName(ProductConstants.ProductType.getDescByType(entity.getProductType()));
            productVo.setProductBillingTypeName(ProductConstants.ProductBillingType.getDescByType(entity.getProductBillingType()));
            productVoList.add(productVo);
        }
        PageUtils pageUtils = new PageUtils(page);
        pageUtils.setList(productVoList);
        return pageUtils;
    }

    @Override
    public List getProductTypeList() {
            return ProductConstants.ProductType.toList();
    }

}