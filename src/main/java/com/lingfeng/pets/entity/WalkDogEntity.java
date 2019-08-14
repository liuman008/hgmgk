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
public class WalkDogEntity implements Serializable{
    
    /**
     * Log
     */
    private static final long serialVersionUID = -7584695512432912128L;
    
    /**
     *id
     */
    @ApiModelProperty(value = "id")
    private String id;
    
    /**
     * 当前用户id
     */
    @ApiModelProperty(value = "当前用户id")
    private String userId;
    
    /**
     * 小时/天
     */
    @ApiModelProperty(value = "小时/天")
    private String hoursDays;
    
    /**
     * 小时/周
     */
    @ApiModelProperty(value = "小时/周")
    private String hoursWeeks;
    
    /**
     * 小时/月
     */
    @ApiModelProperty(value = "小时/月")
    private String hoursMouth;
    
    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private String money;
    
    /**
     * 发布id
     */
    @ApiModelProperty(value = "发布id")
    private String releaseId;
    
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
                 * 行数
     */
    @ApiModelProperty(value = "行数")
    private Integer currIndex;
    

}
