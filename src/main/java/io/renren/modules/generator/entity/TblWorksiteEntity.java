package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 工地表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-07-27 11:01:36
 */
@Data
@TableName("tbl_worksite")
public class TblWorksiteEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 工地名称
	 */
	private String name;
	/**
	 * 工地地址
	 */
	private String address;

	/**
	 * 客户id
	 */
	private Integer clientId;

	/**
	 * 备注
	 */
	private String comment;
	/**
	 * 操作人
	 */
	private String operator;
	/**
	 * 创建时间
	 */
	private Date createrTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 状态
	 */
	private Integer state;

}
