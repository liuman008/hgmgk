/**
 * 
 */
package com.lingfeng.pets.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.lingfeng.pets.config.ResponseCode;
import com.lingfeng.pets.config.ServerResponse;
import com.lingfeng.pets.entity.ChargeEntity;
import com.lingfeng.pets.service.ChargeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author 谷春
 *
 */
@RestController("ChargeController")
@RequestMapping("/api")
@CrossOrigin
@Api(value = "/chargeController", description = "费用表的接口")
public class ChargeController {
    
    /**
     * 注入费用表的实现类
     */
    @Autowired
    private ChargeService chargeService;
    
    /**
     * 新增费用表信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PostMapping("/charge")
    @Timed
    @ApiOperation(value = "新增费用表信息",notes = "新增ChargeEntity说明：参数必须全部都有！")
    public ServerResponse<Integer> insertCharge(@ApiParam @Validated @RequestBody ChargeEntity chargeEntity){
        chargeEntity.setId(UUID.randomUUID().toString());
        chargeEntity.setCreate_date(new Date());
        return ServerResponse.createBySuccess("新增发布信息成功！", chargeService.insertCharge(chargeEntity));
    }
    /**
     * 删除费用表信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @DeleteMapping("/charge")
    @Timed
    @ApiOperation(value = "删除费用表信息",notes = "删除ChargeEntity说明：根据id删除数据,还有最后操作人id！")
    public ServerResponse<Integer> deleteCharge(@ApiParam @Validated @RequestBody ChargeEntity chargeEntity){
        if (!StringUtils.isNotBlank(chargeEntity.getId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "id不能为空！！");
        }else if (!StringUtils.isNotBlank(chargeEntity.getLastModel_by())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "最后操作人不能为空！");
        }
        chargeEntity.setLastModel_date(new Date());
        return ServerResponse.createBySuccess("删除发布信息成功！", chargeService.deleteCharge(chargeEntity));
    }
    
    
    /**
     * 修改费用表信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PutMapping("/charge")
    @Timed
    @ApiOperation(value = "修改费用表信息",notes = "修改ChargeEntity说明：根据id修改数据,id不能为空！")
    public ServerResponse<Integer> updateCharge(@ApiParam @Validated @RequestBody ChargeEntity chargeEntity){
        if (!StringUtils.isNotBlank(chargeEntity.getId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "id不能为空！！");
        }
        chargeEntity.setUpdate_date(new Date());
        return ServerResponse.createBySuccess("修改发布信息成功！", chargeService.updateCharge(chargeEntity));
    }
    
    /**
     * 查询数据
     * @author 谷春
     * @param 
     * @return ServerResponse<ReleasePetEntity>
     * @throws Exception
     */
    @GetMapping("/charge/all")
    @Timed
    @ApiOperation(value = "查看所有",notes = "查看所有！")
    public ServerResponse<List<ChargeEntity>> selectAllCharge(){
        return ServerResponse.createBySuccess(chargeService.selectAllCharge());
    }
    
    /**
     * 计算费用的接口
     * @author 谷春
     * 
     * @param 
     * @return ServerResponse<List<ChargeEntity>>
     * @throws Exception
     */
    @PostMapping("/charge/count")
    @Timed
    @ApiOperation(value = "计算寄养费用",notes = "计算寄养费用")
    public ServerResponse<Double> selectCharge(Integer days,String physique){
        if (!StringUtils.isNotBlank(physique)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "体格为空！");
        }else if (!StringUtils.isNotBlank(days+"")) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "天数为空！");
        }
        Integer phyInteger = Integer.parseInt(physique);
        ChargeEntity chargeEntity = new ChargeEntity();
        List<ChargeEntity>  res = new ArrayList<ChargeEntity>();
        double resmoeny = 0 ;
        if (0<phyInteger && phyInteger <=10) {
            chargeEntity.setId("4238d751-b6c8-4f27-90ad-89816aa96843");
            res = chargeService.selectLikeCharge(chargeEntity);
            if (res == null)  return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "费用表没查询到数据！！");
            double moeny = res.get(0).getMoney();
             resmoeny = days*moeny;
        }else if (11<=phyInteger && phyInteger <=20) {
            chargeEntity.setId("91095c8f-4bfb-4d62-908c-bf5c80190b50");
            res = chargeService.selectLikeCharge(chargeEntity);
            if (res == null)  return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "费用表没查询到数据！！");
            double moeny = res.get(0).getMoney();
            resmoeny = days*moeny;
        }else if(21<=phyInteger && phyInteger <=30) {
            chargeEntity.setId("4fd3406c-7e10-414c-be28-3e044773bed7");
            res = chargeService.selectLikeCharge(chargeEntity);
            if (res == null)  return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "费用表没查询到数据！！");
            double moeny = res.get(0).getMoney();
            resmoeny = days*moeny;
        }else if(31<=phyInteger && phyInteger <=40) {
            chargeEntity.setId("f12e8cff-47b5-4c76-8be6-b04c8a8d70c3");
            res = chargeService.selectLikeCharge(chargeEntity);
            if (res == null)  return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "费用表没查询到数据！！");
            double moeny = res.get(0).getMoney();
            resmoeny = days*moeny;
        }else if(41<=phyInteger && phyInteger <=50) {
            chargeEntity.setId("b0fd07ee-e9b0-49fc-8070-542752e9ba19");
            res = chargeService.selectLikeCharge(chargeEntity);
            if (res == null)  return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "费用表没查询到数据！！");
            double moeny = res.get(0).getMoney();
            resmoeny = days*moeny;
        }else if(51<=phyInteger && phyInteger <=60) {
            chargeEntity.setId("8dbe30b8-2eeb-4d4f-8761-4c4112f16821");
            res = chargeService.selectLikeCharge(chargeEntity);
            if (res == null)  return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "费用表没查询到数据！！");
            double moeny = res.get(0).getMoney();
            resmoeny = days*moeny;
        }else if(61<=phyInteger && phyInteger <=70) {
            chargeEntity.setId("26284681-e4ef-4bd6-9d57-772f40281eed");
            res = chargeService.selectLikeCharge(chargeEntity);
            if (res == null)  return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "费用表没查询到数据！！");
            double moeny = res.get(0).getMoney();
            resmoeny = days*moeny;
        }else if(71<=phyInteger && phyInteger <=80) {
            chargeEntity.setId("4b7c4ee1-1c08-47bc-93c2-1d58a50dd58e");
            res = chargeService.selectLikeCharge(chargeEntity);
            if (res == null)  return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "费用表没查询到数据！！");
            double moeny = res.get(0).getMoney();
            resmoeny = days*moeny;
        }else if(81<= phyInteger) {
            chargeEntity.setId("44b050d3-176c-4fd4-a73b-93a297c023ee");
            res = chargeService.selectLikeCharge(chargeEntity);
            if (res == null)  return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "费用表没查询到数据！！");
            double moeny = res.get(0).getMoney();
            resmoeny = days*moeny;
        }
        return ServerResponse.createBySuccess(resmoeny);
    }
    
}
