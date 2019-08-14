/**
 * 
 */
package com.lingfeng.pets.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 谷春
 *
 */
@Data
@ApiModel
public class ReleaseAllEntity implements Serializable{
    
    
    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    private String content;
    
    
    /**
     * 疫苗图片路径
     */
    @ApiModelProperty(value = "疫苗图片路径 （寄养）")
    private String VaccineImage;
    
    /**
     * 发布图片路径
     */
    @ApiModelProperty(value = "发布图片路径  (公共)")
    private String releaseImage;
    /**
     * 当前用户id
     */
    @ApiModelProperty(value = "当前用户id (公共)")
    private String userId;
    
    /**
     * Log
     */
    private static final long serialVersionUID = 6502457759036032142L;
    
    /**
     * 公母
     */
    @ApiModelProperty(value = "公母 (公共)")
    private String maleandfemale;
    
    /**
     * 姓名
     */
   @ApiModelProperty(value = " 姓名  (公共)")
    private String name;
    
    /**
     * 年龄
     */
   @ApiModelProperty(value = "年龄 (公共)")
    private Integer age;
    
   /**
    * 是否绝育
    */
   @ApiModelProperty(value = "是否绝育")
   private Integer is_sterilization;
    
    /**
     * 是否疫苗
     */
    @ApiModelProperty(value = "是否疫苗  (公共)")
    private Integer is_vaccine;
    
    /**
     * 是否驱虫
     */
    @ApiModelProperty(value = "是否驱虫  (公共)")
    private Integer is_insectRepellent;
    
    /**
     * 体重
     */
    @ApiModelProperty(value = "体重 (公共)")
    private String weight;
    
    /**
     * 品种
     */
    @ApiModelProperty(value = "品种  (公共) ")
    private String varieties;
    
    /**
     * 型号
     */
    @ApiModelProperty(value = "型号  (公共)")
    private String models;
    
    /**
     * 过敏史
     */
    @ApiModelProperty(value = "过敏史  (公共)")
    private String allergy;
    
    /**
     *发情期
     */
    @ApiModelProperty(value = "发情期  (公共) ")
    private String estrus;
    
    /**
     * 寄养天数
     */
    @ApiModelProperty(value = "寄养天数 （寄养）")
    private Integer days;
    
    /**
     * 洗澡次数
     */
    @ApiModelProperty(value = "洗澡次数 （寄养）")
    private Integer washNum;
    
    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String address;
    
    /**
     * 服务费
     */
    @ApiModelProperty(value = "服务费 （寄养）")
    private String serviceCharge;
    
    /**
     * 宠物证件
     */
    @ApiModelProperty(value = "宠物证件  （寄养）")
    private String certificatesImage;
    
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注 （寄养）")
    private String remarks;
    
    /**
     * 故事
     */
    @ApiModelProperty(value = "故事 （寄养）")
    private String story;
    
    /**
     * 领养条件
     */
    @ApiModelProperty(value = "领养条件 （寄养）")
    private String conditionsofadoption;
    
    /**
     * 打卡条件
     */
    @ApiModelProperty(value = "打卡条件（寄养） ")
    private String cardingConditions;
    
    /**
     * 小时/天
     */
    @ApiModelProperty(value = "小时/天 （遛狗）")
    private String hoursDays;
    
    /**
     * 小时/周
     */
    @ApiModelProperty(value = "小时/周 （遛狗）")
    private String hoursWeeks;
    
    /**
     * 小时/月
     */
    @ApiModelProperty(value = "小时/月  （遛狗）")
    private String hoursMouth;
    
    /**
     * 金额
     */
    @ApiModelProperty(value = "金额  （公共）")
    private String money;
    
    /**
     * 经度
     */
    @ApiModelProperty(value = "经度（公共）")
    private double longitude;
    
    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度（公共）")
    private double latitude;
    
    /**
     * 距离
     */
    @ApiModelProperty(value = "距离  （公共）")
    private double distance;

   /**
         * 类型
    */
    @ApiModelProperty(value = "类型  寄养 1 遛狗2")
    private Integer type; 
    
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer state;

}
