package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 客户表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-07-27 11:01:37
 */
@Data
@TableName("tbl_client")
public class TblClientEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String telephone;
	/**
	 * 
	 */
	private String companyName;
	/**
	 * 
	 */
	private Integer sex;
	/**
	 * 
	 */
	private String comment;
	/**
	 * 
	 */
	private String operatorName;
	/**
	 * 
	 */
	private Date createrTime;
	/**
	 * 
	 */
	private Date updateTime;
	/**
	 * 
	 */
	private Integer state;

	private String cardNo;


}
