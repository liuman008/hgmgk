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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.lingfeng.pets.config.ResponseCode;
import com.lingfeng.pets.config.ServerResponse;
import com.lingfeng.pets.entity.WalkDogChargeEntity;
import com.lingfeng.pets.service.WalkDogChargeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author 谷春
 *
 */
@RestController("WalkDogChargeController") 
@RequestMapping("/api")
@CrossOrigin
@Api(value = "[遛狗计算费用表]资源操作接口", description = "walkDogChargeController")
public class WalkDogChargeController {
    
    /**
     * 注入遛狗计算费用表的实现类
     */
    @Autowired
    private WalkDogChargeService walkDogChargeService;
    
            /**
             * 新增遛狗表信息
        * @author 谷春
        * @param 
        * @return boolean
        * @throws Exception
        */
        @PostMapping("/walkDogCharge")
        @Timed
        @ApiOperation(value = "创建遛狗表信息", notes = "根据WalkDogChargeEntity对象新增遛狗表信息")
        public boolean addWalkDogCharge(@ApiParam @RequestBody @Validated WalkDogChargeEntity walkDogChargeEntity) {
            walkDogChargeEntity.setId(UUID.randomUUID().toString());
            walkDogChargeEntity.setCreate_date(new Date());
            return walkDogChargeService.addWalkDogCharge(walkDogChargeEntity);
        }
        
        /**
             *   删除遛狗表信息
         * @author liuman
         * @param 
         * @return boolean
         * @throws Exception
         */
        @DeleteMapping("/walkDogCharge")
        @Timed
        @ApiOperation(value = "删除遛狗表信息", notes = "根据UserWalkDogChargeEntity对象删除遛狗表信息")
        public boolean deleteWalkDogCharge(@ApiParam @RequestBody @Validated WalkDogChargeEntity walkDogChargeEntity) {
            return walkDogChargeService.deleteWalkDogCharge(walkDogChargeEntity);
        }
        
        /**
             * 根据id修改遛狗表信息
        * @author liuman
        * @param 
        * @return boolean
        * @throws Exception
        */
        @PutMapping("/walkDogCharge")
        @Timed
        @ApiOperation(value = "根据id修改遛狗表信息", notes = "根据UserWalkDogChargeEntity对象根据id修改遛狗表信息")
        public boolean updateWalkDogChargeByid(@ApiParam @RequestBody @Validated WalkDogChargeEntity walkDogChargeEntity) {
            walkDogChargeEntity.setUpdate_date(new Date());
            return walkDogChargeService.updateWalkDogCharge(walkDogChargeEntity);
        }
        
        /**
             * 查看所有
        * @author liuman
        * @param 
        * @return List<WalkDogChargeEntity>
        * @throws Exception
        */
        @GetMapping("/walkDogCharge/page")
        @Timed
        @ApiOperation(value = "查询遛狗表", notes = "根据UserWalkDogChargeEntity对象查询遛狗表")
        public @ResponseBody List<WalkDogChargeEntity> selectAllWalkDogCharge(){
            return walkDogChargeService.selectWalkDogChargeALL();
        }
        
        /**
         * 计算遛狗的费用
         * @author 谷春
         * @param 
         * @return ServerResponse<Double>
         * @throws Exception
         */
        @PostMapping("/walkDogCharge/Calculation")
        @Timed
        @ApiOperation(value = "计算遛狗的费用", notes = "根据UserWalkDogChargeEntity对象查询遛狗表")
        public ServerResponse<Double> selecWalkDogCharge(Integer hours,String physique){
            if (!StringUtils.isNotBlank(physique)) {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "体格为空！");
            }else if (!StringUtils.isNotBlank(hours+"")) {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "天数为空！");
            }
             Integer phyInteger = Integer.parseInt(physique);
            //计算费用  小时*费用
            WalkDogChargeEntity walkDogChargeEntity = new WalkDogChargeEntity();
            List<WalkDogChargeEntity> wChargeEntities = new ArrayList<WalkDogChargeEntity>();
            double resmoeny = 0 ;
            if (0<phyInteger && phyInteger <=10) {
                wChargeEntities = walkDogChargeService.selectWalkDogChargeALLById("5869f704-81c2-4324-b94a-c888c66503f0");
                if (wChargeEntities == null)  return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "费用表没查询到数据！！");
                double moeny = wChargeEntities.get(0).getMoney();
                 resmoeny = hours*moeny;
            }else if (11<=phyInteger && phyInteger <=20) {
                wChargeEntities = walkDogChargeService.selectWalkDogChargeALLById("59d818a6-50fc-44e8-8453-b4dbc9aeb456");
                if (wChargeEntities == null)  return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "费用表没查询到数据！！");
                double moeny = wChargeEntities.get(0).getMoney();
                resmoeny = hours*moeny;
            }else if(21<=phyInteger && phyInteger <=30) {
                wChargeEntities = walkDogChargeService.selectWalkDogChargeALLById("0d5fdff7-182b-4a47-9ea9-32cff5dd7127");
                if (wChargeEntities == null)  return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "费用表没查询到数据！！");
                double moeny = wChargeEntities.get(0).getMoney();
                resmoeny = hours*moeny;
            }else if(31<=phyInteger && phyInteger <=40) {
                wChargeEntities = walkDogChargeService.selectWalkDogChargeALLById("ca62404a-a078-405f-b938-0fd42fdd935b");
                if (wChargeEntities == null)  return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "费用表没查询到数据！！");
                double moeny = wChargeEntities.get(0).getMoney();
                resmoeny = hours*moeny;
            }else if(41<=phyInteger && phyInteger <=50) {
                wChargeEntities = walkDogChargeService.selectWalkDogChargeALLById("016f3a97-5aea-4feb-979d-a69166240e55");
                if (wChargeEntities == null)  return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "费用表没查询到数据！！");
                double moeny = wChargeEntities.get(0).getMoney();
                resmoeny = hours*moeny;
            }else if(51<=phyInteger && phyInteger <=60) {
                wChargeEntities = walkDogChargeService.selectWalkDogChargeALLById("b08969fe-ec9a-425a-ab21-51004c184e8d");
                if (wChargeEntities == null)  return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "费用表没查询到数据！！");
                double moeny = wChargeEntities.get(0).getMoney();
                resmoeny = hours*moeny;
            }else if(61<=phyInteger && phyInteger <=70) {
                wChargeEntities = walkDogChargeService.selectWalkDogChargeALLById("bfbfa981-7e76-4e52-82a8-f5cef52f40b7");
                if (wChargeEntities == null)  return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "费用表没查询到数据！！");
                double moeny = wChargeEntities.get(0).getMoney();
                resmoeny = hours*moeny;
            }else if(71<=phyInteger && phyInteger <=80) {
                wChargeEntities = walkDogChargeService.selectWalkDogChargeALLById("84c7cec7-226c-474d-99c8-8b578cec1e81");
                if (wChargeEntities == null)  return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "费用表没查询到数据！！");
                double moeny = wChargeEntities.get(0).getMoney();
                resmoeny = hours*moeny;
            }else if(81<= phyInteger) {
                wChargeEntities = walkDogChargeService.selectWalkDogChargeALLById("5dd496d4-8526-4fe3-a4b4-cb67df769490");
                if (wChargeEntities == null)  return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "费用表没查询到数据！！");
                double moeny = wChargeEntities.get(0).getMoney();
                resmoeny = hours*moeny;
            }
            return ServerResponse.createBySuccess(resmoeny);
        }


}
