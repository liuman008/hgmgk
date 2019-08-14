/**
 * 
 */
package com.lingfeng.pets.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.lingfeng.pets.config.ResponseCode;
import com.lingfeng.pets.config.ServerResponse;
import com.lingfeng.pets.entity.BillEntity;
import com.lingfeng.pets.service.BillService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author 谷春
 *
 */
@RestController("BillController")
@RequestMapping("/api")
@CrossOrigin
@Api(value = "[账单表]资源操作接口", description = "billController")
public class BillController {
    
    /**
     * 注入账单表的实现类
     */
    @Autowired
    private BillService billService;
    
    
    /**
     * 新增账单表信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PostMapping("/bill")
    @Timed
    @ApiOperation(value = "新增账单表信息",notes = "新增BillEntity说明：参数必须全部都有！")
    public ServerResponse<Integer> insertBill(@ApiParam @Validated @RequestBody BillEntity billEntity){
        billEntity.setId(UUID.randomUUID().toString());
        billEntity.setCreate_date(new Date());
        return ServerResponse.createBySuccess("新增发布信息成功！", billService.insertBill(billEntity));
    }
    /**
     * 删除账单表信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @DeleteMapping("/bill")
    @Timed
    @ApiOperation(value = "删除账单表信息",notes = "删除BillEntity说明：根据id删除数据,还有最后操作人id！")
    public ServerResponse<Integer> deleteBill(@ApiParam @Validated @RequestBody BillEntity billEntity){
        if (!StringUtils.isNotBlank(billEntity.getId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "id不能为空！！");
        }else if (!StringUtils.isNotBlank(billEntity.getLastModel_by())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "最后操作人不能为空！");
        }
        billEntity.setLastModel_date(new Date());
        return ServerResponse.createBySuccess("删除发布信息成功！", billService.deleteBill(billEntity));
    }
    
    
    /**
     * 修改账单表信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PutMapping("/bill")
    @Timed
    @ApiOperation(value = "修改账单表信息",notes = "修改BillEntity说明：根据id修改数据,id不能为空！")
    public ServerResponse<Integer> updateBill(@ApiParam @Validated @RequestBody BillEntity billEntity){
        if (!StringUtils.isNotBlank(billEntity.getId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "id不能为空！！");
        }
        billEntity.setUpdate_date(new Date());
        return ServerResponse.createBySuccess("修改发布信息成功！",billService.updateBill(billEntity));
    }
    
    /**
     * 查询数据
     * @author 谷春
     * @param 
     * @return ServerResponse<ReleasePetEntity>
     * @throws Exception
     */
    @GetMapping("/bill/all")
    @Timed
    @ApiOperation(value = "查看所有",notes = "查看所有！")
    public ServerResponse<List<BillEntity >> selectAllBill(){
        return ServerResponse.createBySuccess(billService.selectAllBill());
    }
    
    /**
     * 模糊查询
     * @author 谷春
     * @param 
     * @return ServerResponse<ReleasePetEntity>
     * @throws Exception
     */
    @PostMapping("/bill/like")
    @Timed
    @ApiOperation(value = "模糊查询",notes = "模糊查询！")
    public ServerResponse<List<BillEntity >> selectLikeBill(@ApiParam @Validated @RequestBody BillEntity billEntity){
        return ServerResponse.createBySuccess(billService.selectLikeBill(billEntity));
    }
    
    /**
     * 根据用户id查询数据
     * @author 谷春
     * @param 
     * @return ServerResponse<ReleasePetEntity>
     * @throws Exception
     */
    @GetMapping("/bill/userId/{userId}")
    @Timed
    @ApiOperation(value = "查看所有",notes = "查看所有！")
    public ServerResponse<List<BillEntity >> selectBillByuserId(@PathVariable String userId){
        if (!StringUtils.isNotBlank(userId)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "请求参数为空！！！");
        }
        BillEntity billEntity = new BillEntity();
        billEntity.setUserId(userId);
        billEntity.setIs_read(1);
        return ServerResponse.createBySuccess(billService.selectLikeBill(billEntity));
    }
    
    /**
     * 寄养或遛狗已打钱的接口
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PostMapping("/bill/calculation}")
    @Timed
    @ApiOperation(value = "寄养或遛狗已打钱的接口",notes = " 寄养或遛狗已打钱的接口！")
    public ServerResponse<Integer> jiyangOrWalkDog(String userId,String releaseId,String account,Integer type){
        BillEntity billEntity = new BillEntity();
        billEntity.setId(UUID.randomUUID().toString());
        billEntity.setUserId(userId);
        billEntity.setIs_output(2);
        billEntity.setMoney(account);
        if (type ==1) {
            billEntity.setContent("助养订单完成");
        }else {
            billEntity.setContent("遛狗订单完成");
        }
        billEntity.setCreate_date(new Date());
        return ServerResponse.createBySuccess("打钱成功", billService.insertBill(billEntity));
    }
    
    /**
     *根据用户id查询出未读的信息 
     * @author 谷春
     * @param 
     * @return List<BillEntity>
     * @throws Exception
     */
    @GetMapping("/bill/isread/{userId}")
    @Timed
    @ApiOperation(value = "根据用户id查询出未读的信息",notes = " 根据用户id查询出未读的信息！")
    public ServerResponse<List<BillEntity>> selectBillByuserIdandRead(@PathVariable String userId) {
        if (!StringUtils.isNotBlank(userId)) {
            return ServerResponse.createByErrorMessage("用户id为空！！");
        }
       List<BillEntity> billList  = billService.selectBillByuserIdandRead(userId);
       if (billList.size()>0) {
           return ServerResponse.createBySuccessMessage("您有"+billList.size()+"笔账单信息，点击账单查看明显");
       }
       return null;
    }
    
    /**
     * 修改已读状态
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PutMapping("/bill/isread")
    @Timed
    @ApiOperation(value = "修改已读状态",notes = " 修改已读状态！")
    public ServerResponse<Integer> updateBillByuserId(String userId){
        List<BillEntity> billList  = billService.selectBillByuserIdandRead(userId);
        Integer reInteger = 0;
        for (BillEntity billEntity : billList) {
            BillEntity billEntitys = new BillEntity();
            billEntitys.setId(billEntity.getId()); 
            billEntitys.setIs_read(1);
            billEntitys.setUpdate_date(new Date());
            Integer t = billService.updateBill(billEntitys);
            System.out.println("t="+t);
           reInteger++;
        }
        return ServerResponse.createBySuccess(reInteger);
    }
}
