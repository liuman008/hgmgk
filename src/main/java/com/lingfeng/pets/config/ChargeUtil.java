/**
 * 
 */
package com.lingfeng.pets.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lingfeng.pets.entity.ChargeEntity;
import com.lingfeng.pets.entity.WalkDogChargeEntity;
import com.lingfeng.pets.service.ChargeService;
import com.lingfeng.pets.service.WalkDogChargeService;

/**
 * @author 谷春
 *
 */
public class ChargeUtil {
    
    /**
     * 寄养服务费表
     */
    @Autowired
    private static ChargeService chargeService;
    
    /**
     * 遛狗服务费
     */
    @Autowired
    private static WalkDogChargeService walkDogChargeService;
    
    
    /**
     * 计算遛狗服务费
     * @author 谷春
     * @param 
     * @return double
     * @throws Exception
     */
    public static double selecWalkDogCharge(Integer hours,String physique){
         Integer phyInteger = Integer.parseInt(physique);
        //计算费用  小时*费用
        WalkDogChargeEntity walkDogChargeEntity = new WalkDogChargeEntity();
        List<WalkDogChargeEntity> wChargeEntities = new ArrayList<WalkDogChargeEntity>();
        double resmoeny = 0 ;
        if (0<phyInteger && phyInteger <=10) {
            wChargeEntities = walkDogChargeService.selectWalkDogChargeALLById("5869f704-81c2-4324-b94a-c888c66503f0");
            double moeny = wChargeEntities.get(0).getMoney();
             resmoeny = hours*moeny;
        }else if (11<=phyInteger && phyInteger <=20) {
            wChargeEntities = walkDogChargeService.selectWalkDogChargeALLById("59d818a6-50fc-44e8-8453-b4dbc9aeb456");
            double moeny = wChargeEntities.get(0).getMoney();
            resmoeny = hours*moeny;
        }else if(21<=phyInteger && phyInteger <=30) {
            wChargeEntities = walkDogChargeService.selectWalkDogChargeALLById("0d5fdff7-182b-4a47-9ea9-32cff5dd7127");
            double moeny = wChargeEntities.get(0).getMoney();
            resmoeny = hours*moeny;
        }else if(31<=phyInteger && phyInteger <=40) {
            wChargeEntities = walkDogChargeService.selectWalkDogChargeALLById("ca62404a-a078-405f-b938-0fd42fdd935b");
            double moeny = wChargeEntities.get(0).getMoney();
            resmoeny = hours*moeny;
        }else if(41<=phyInteger && phyInteger <=50) {
            wChargeEntities = walkDogChargeService.selectWalkDogChargeALLById("016f3a97-5aea-4feb-979d-a69166240e55");
            double moeny = wChargeEntities.get(0).getMoney();
            resmoeny = hours*moeny;
        }else if(51<=phyInteger && phyInteger <=60) {
            wChargeEntities = walkDogChargeService.selectWalkDogChargeALLById("b08969fe-ec9a-425a-ab21-51004c184e8d");
            double moeny = wChargeEntities.get(0).getMoney();
            resmoeny = hours*moeny;
        }else if(61<=phyInteger && phyInteger <=70) {
            wChargeEntities = walkDogChargeService.selectWalkDogChargeALLById("bfbfa981-7e76-4e52-82a8-f5cef52f40b7");
            double moeny = wChargeEntities.get(0).getMoney();
            resmoeny = hours*moeny;
        }else if(71<=phyInteger && phyInteger <=80) {
            wChargeEntities = walkDogChargeService.selectWalkDogChargeALLById("84c7cec7-226c-474d-99c8-8b578cec1e81");
            double moeny = wChargeEntities.get(0).getMoney();
            resmoeny = hours*moeny;
        }else if(81<= phyInteger) {
            wChargeEntities = walkDogChargeService.selectWalkDogChargeALLById("5dd496d4-8526-4fe3-a4b4-cb67df769490");
            double moeny = wChargeEntities.get(0).getMoney();
            resmoeny = hours*moeny;
        }
        return resmoeny;
    }
    
    
    /**
     * 计算寄养服务费
     * @author 谷春
     * @param 
     * @return double
     * @throws Exception
     */
    public static double selectCharge(Integer days,String physique){
        Integer phyInteger = Integer.parseInt(physique);
        ChargeEntity chargeEntity = new ChargeEntity();
        List<ChargeEntity>  res = new ArrayList<ChargeEntity>();
        double resmoeny = 0 ;
        if (0<phyInteger && phyInteger <=10) {
            chargeEntity.setId("4238d751-b6c8-4f27-90ad-89816aa96843");
            res = chargeService.selectLikeCharge(chargeEntity);
            double moeny = res.get(0).getMoney();
             resmoeny = days*moeny;
        }else if (11<=phyInteger && phyInteger <=20) {
            chargeEntity.setId("91095c8f-4bfb-4d62-908c-bf5c80190b50");
            res = chargeService.selectLikeCharge(chargeEntity);
            double moeny = res.get(0).getMoney();
            resmoeny = days*moeny;
        }else if(21<=phyInteger && phyInteger <=30) {
            chargeEntity.setId("4fd3406c-7e10-414c-be28-3e044773bed7");
            System.out.println("kkkkkk=="+ChargeUtil.chargeService.selectLikeCharge(chargeEntity));
            res = ChargeUtil.chargeService.selectLikeCharge(chargeEntity);
            double moeny = res.get(0).getMoney();
            resmoeny = days*moeny;
        }else if(31<=phyInteger && phyInteger <=40) {
            chargeEntity.setId("f12e8cff-47b5-4c76-8be6-b04c8a8d70c3");
            res = chargeService.selectLikeCharge(chargeEntity);
            double moeny = res.get(0).getMoney();
            resmoeny = days*moeny;
        }else if(41<=phyInteger && phyInteger <=50) {
            chargeEntity.setId("b0fd07ee-e9b0-49fc-8070-542752e9ba19");
            res = chargeService.selectLikeCharge(chargeEntity);
            double moeny = res.get(0).getMoney();
            resmoeny = days*moeny;
        }else if(51<=phyInteger && phyInteger <=60) {
            chargeEntity.setId("8dbe30b8-2eeb-4d4f-8761-4c4112f16821");
            res = chargeService.selectLikeCharge(chargeEntity);
            double moeny = res.get(0).getMoney();
            resmoeny = days*moeny;
        }else if(61<=phyInteger && phyInteger <=70) {
            chargeEntity.setId("26284681-e4ef-4bd6-9d57-772f40281eed");
            res = chargeService.selectLikeCharge(chargeEntity);
            double moeny = res.get(0).getMoney();
            resmoeny = days*moeny;
        }else if(71<=phyInteger && phyInteger <=80) {
            chargeEntity.setId("4b7c4ee1-1c08-47bc-93c2-1d58a50dd58e");
            res = chargeService.selectLikeCharge(chargeEntity);
            double moeny = res.get(0).getMoney();
            resmoeny = days*moeny;
        }else if(81<= phyInteger) {
            chargeEntity.setId("44b050d3-176c-4fd4-a73b-93a297c023ee");
            res = chargeService.selectLikeCharge(chargeEntity);
            double moeny = res.get(0).getMoney();
            resmoeny = days*moeny;
        }
        return resmoeny;
    }

}
