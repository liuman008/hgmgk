/**
 * 
 */
package com.lingfeng.pets.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 谷春
 *
 */
@Data
@ApiModel
public class CommentEntity implements Serializable{
    
    /**
     * Log
     */
    private static final long serialVersionUID = -1183671540967675382L;

    /**
     * id
     */
    @ApiModelProperty("/id")
    private String id;
    
    /**
     * 评论人
     */
    @ApiModelProperty("/评论人id")
    private String commentUserId;
    
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;
    
   /**
    * 用头像
    */
    @ApiModelProperty(value = "用户头像")
    private String userLogo;
    
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
     * 评论内容
     */
    @NotEmpty(message = "评论内容不能为空")
    @Size(max = 500, message = "评论内容不能多于500个字符")
    private String commentContent;
    
    /**
     * 宠物圈id
     */
    private String petcircleId;
    
    /**
     * 被回复人id
     */
    private String replyUserId;
    
    /**
     * 父级id
     */
    private String paretId;
    
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
