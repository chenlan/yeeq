package io.renren.jenkins.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ServerAddressEntity implements Serializable {

    /**
     * ip地址
     */
    private String ip;

    /**
     * 用户名
     */
    private String username;


    /**
     * 密码
     */
    private String password;

}
