/**
 * 
 */
package com.lingfeng.pets.entity;

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
public class ReleasePetEntity {
    
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
         * 公母
         */
    @ApiModelProperty(value = "公母")
        private String maleandfemale;
        
        /**
         * 姓名
         */
    @ApiModelProperty(value = " 姓名")
        private String name;
        
        /**
         * 年龄
         */
    @ApiModelProperty(value = "年龄")
        private Integer age;
        
        /**
         * 是否绝育
         */
    @ApiModelProperty(value = "是否疫苗")
        private Integer is_vaccine;
        
    /**
     * 是否驱虫
     */
    @ApiModelProperty(value = "是否驱虫")
        private Integer is_insectRepellent;
        
        /**
         * 是否绝育
         */
    @ApiModelProperty(value = "是否绝育")
        private Integer is_sterilization;
        
        /**
         * 体重
         */
    @ApiModelProperty(value = "体重")
        private String weight;
    
    /**
     * 内容 
     */
    @ApiModelProperty(value = "内容")
     private String content;
        
        /**
         * 品种
         */
    @ApiModelProperty(value = "品种")
        private String varieties;
        
        /**
         * 型号
         */
    @ApiModelProperty(value = "型号")
        private String models;
        
        /**
         * 过敏史
         */
    @ApiModelProperty(value = "过敏史")
        private String allergy;
        
        /**
         *发情期
         */
    @ApiModelProperty(value = "发情期")
        private String estrus;
    
        /**
                              * 经度
         */
        @ApiModelProperty(value = "经度")
        private double longitude;
        
        /**
                            * 纬度
         */
        @ApiModelProperty(value = "纬度")
        private double latitude;
        
        /**
                          * 距离
         */
        @ApiModelProperty(value = "距离")
        private double distance;
    
       /**
             * 类型
        */
        @ApiModelProperty(value = "类型")
        private Integer type; 
        
        /**
         * 状态
         */
        @ApiModelProperty(value = "状态")
        private Integer state;
        

        /**
         * 用户id
         */
        @ApiModelProperty(value = "用户id")
        private String userId;
        
        /**
         * 图片
         */
        @ApiModelProperty(value = "图片")
        private String image;
        
        /**
         * 是否有效
         */
        @ApiModelProperty(value = "是否有效")
        private Integer is_effective;
        
        /**
                        * 服务费
         */
        @ApiModelProperty(value = "服务费")
        private double serviceCharge;
        
        /**
         * 发布地址
         */
        @ApiModelProperty(value = "发布地址")
        private String address;
        
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
