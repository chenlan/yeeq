package io.renren.modules.generator.vo.resp;

import io.renren.modules.generator.entity.TblWorksiteEntity;
import lombok.Data;

@Data
public class WorksiteVo extends TblWorksiteEntity {
    /**
     * 客户名称
     */
    private String clientName;

    /**
     * 工地电话
     */
    private String worksiteTelephone;


}
