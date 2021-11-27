package io.renren.modules.generator.vo.resp;

import lombok.Data;

import java.io.Serializable;

@Data
public class SelectClientVo implements Serializable {

    private Integer id;
    /**
     *
     */
    private String name;
    /**
     *
     */
    private String telephone;
}
