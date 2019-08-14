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
 * @author 谷春
 *
 */
@Data
@ApiModel 
public class GrantEntity implements Serializable{
    
    /**
     * Log
     */
    private static final long serialVersionUID = -8724842870658517154L;
    
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;
    
    /**
     * 发布id
     */
    @ApiModelProperty(value = "发布id")
    private String releaseId;
    
    /**
     * 当前用户id
     */
    @ApiModelProperty(value = "当前用户id")
    private String uid;
    
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date create_date;
    
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
