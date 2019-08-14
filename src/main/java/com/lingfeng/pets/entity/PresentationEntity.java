/**
 * 
 */
package com.lingfeng.pets.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author LIUMAN
 *
 */
@Data
@ApiModel
public class PresentationEntity implements Serializable{
    
    /**
     * log
     */
    private static final long serialVersionUID = -3191095570507111339L;
    
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;
    
    /**
     * 
     */
    @ApiModelProperty(value = "用户id")
    private String userId;
    
    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private String account;
    
    /**
     * 微信号
     */
    @ApiModelProperty(value = "微信号")
    private String wetCat;
    
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private String create_date;
    
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date update_date;
    
    /**
     * 最后操作人
     */
    @ApiModelProperty(value = "最后操作人")
    private String lastModel_by;
    
    /**
             * 最后操作时间
     */
    @ApiModelProperty(value = "最后操作时间")
    private Date lastModel_date;
    
    /**
             * 是否删除
     */
    @ApiModelProperty(value = "是否删除")
    private Integer is_del;
    

}
