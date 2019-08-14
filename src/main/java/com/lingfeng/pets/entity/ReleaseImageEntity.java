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
public class ReleaseImageEntity implements Serializable{
    
    /**
     * Log
     */
    private static final long serialVersionUID = -3363022020890900703L;
    
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;
    
    
    /**
     * 行数
     */
    @ApiModelProperty(value = "行数")
    private Integer currIndex;
    
    /**
     * 页数
     */
    @ApiModelProperty(value = "页数")
    private Integer pageSize;
    
    /**
     * 当前行数
     */
    @ApiModelProperty(value = "当前行数")
    private Integer currentPage;
    
    /**
     * 发布id
     */
    @ApiModelProperty(value = "发布id")
    private String releaseId;
    
    /**
     * 图片
     */
    @ApiModelProperty(value = "图片")
    private String image;

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
