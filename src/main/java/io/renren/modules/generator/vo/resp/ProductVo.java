package io.renren.modules.generator.vo.resp;

import io.renren.modules.generator.entity.TblProductEntity;
import lombok.Data;

@Data
public class ProductVo extends TblProductEntity {
    private String productTypeName;
    private String productBillingTypeName;
}
