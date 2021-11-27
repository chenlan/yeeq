package io.renren.jenkins.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProjectEntity implements Serializable {

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 部署分支
     */
    private String branch;

    /**
     * 部署环境
     */
    private String env;

    private Boolean isAuto;
    /**
     * 服务器列表
     */
    private List<ServerAddressEntity> serverAddressEntityList;


}
