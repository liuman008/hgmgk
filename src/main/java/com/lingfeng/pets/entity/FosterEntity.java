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
public class FosterEntity implements Serializable{
    
    /**
     * log
     */
    private static final long serialVersionUID = -3748431084739549010L;

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
     * 当前页数
     */
    @ApiModelProperty (value =  "当前页数")
    private Integer currentPage;
    
    /**
     * 页数
     */
    @ApiModelProperty(value = "页数")
    private Integer pageSize;
    
    /**
     * 寄养天数
     */
    @ApiModelProperty(value = "寄养天数")
    private Integer days;
    
    /**
     */
    @ApiModelProperty(value = "洗澡次数")
    private Integer washNum;
    
    /**
     * 服务费
     */
    @ApiModelProperty(value = "服务费")
    private String serviceCharge;
    
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remarks;
    
    /**
     * 故事
     */
    @ApiModelProperty(value = "故事")
    private String story;
    
    /**
     * 领养条件
     */
    @ApiModelProperty(value = "领养条件")
    private String conditionsofadoption;
    
    /**
     * 宠物证件
     */
    @ApiModelProperty(value = "宠物证件")
    private String certificatesImage;
    
    /**
     * 打卡条件
     */
    @ApiModelProperty(value = "打卡条件")
    private String cardingConditions;
    
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
    
    
    
}
