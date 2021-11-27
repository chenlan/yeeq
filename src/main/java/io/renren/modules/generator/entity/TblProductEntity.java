package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-11-16 17:36:38
 */
@Data
@TableName("tbl_product")
public class TblProductEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 产品类型
	 */
	private Integer productType;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 产品单位
	 */
	private String productUnit;
	/**
	 * 产品数值
	 */
	private Float productValue;
	/**
	 * 计费类型
	 */
	private Integer productBillingType;
	/**
	 * 
	 */
	private String comment;
	/**
	 * 状态
	 */
	private Integer state;

}
