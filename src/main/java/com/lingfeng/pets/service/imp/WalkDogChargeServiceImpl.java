/**
 * 
 */
package com.lingfeng.pets.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingfeng.pets.entity.WalkDogChargeEntity;
import com.lingfeng.pets.mapper.WalkDogChargeMapper;
import com.lingfeng.pets.service.WalkDogChargeService;

/**
 * @author 谷春
 *
 */
@Service
public class WalkDogChargeServiceImpl implements WalkDogChargeService{
    
    /**
     * 注入遛狗费用表的接口类
     */
    @Autowired
    private WalkDogChargeMapper walkDogChargeMapper;

    @Override
    public boolean addWalkDogCharge(WalkDogChargeEntity WalkDogChargeEntity) {
        return walkDogChargeMapper.addWalkDogCharge(WalkDogChargeEntity);
    }

    @Override
    public boolean deleteWalkDogCharge(WalkDogChargeEntity WalkDogChargeEntity) {
        return walkDogChargeMapper.deleteWalkDogCharge(WalkDogChargeEntity);
    }

    @Override
    public boolean updateWalkDogCharge(WalkDogChargeEntity WalkDogChargeEntity) {
        return walkDogChargeMapper.updateWalkDogCharge(WalkDogChargeEntity);
    }

    @Override
    public List<WalkDogChargeEntity> selectWalkDogChargeALL() {
        return walkDogChargeMapper.selectWalkDogChargeALL();
    }

    @Override
    public List<WalkDogChargeEntity> selectWalkDogChargeALLById(String id) {
        return walkDogChargeMapper.selectWalkDogChargeALLById(id);
    }
    
    
    /**
     * 计算遛狗服务费
     * @author 谷春
     * @param 
     * @return double
     * @throws Exception
     */
    public double selecWalkDogCharge(Integer hours,String physique){
         Integer phyInteger = Integer.parseInt(physique);
        //计算费用  小时*费用
        WalkDogChargeEntity walkDogChargeEntity = new WalkDogChargeEntity();
        List<WalkDogChargeEntity> wChargeEntities = new ArrayList<WalkDogChargeEntity>();
        double resmoeny = 0 ;
        if (0<phyInteger && phyInteger <=10) {
            wChargeEntities = walkDogChargeMapper.selectWalkDogChargeALLById("5869f704-81c2-4324-b94a-c888c66503f0");
            double moeny = wChargeEntities.get(0).getMoney();
             resmoeny = hours*moeny;
        }else if (11<=phyInteger && phyInteger <=20) {
            wChargeEntities = walkDogChargeMapper.selectWalkDogChargeALLById("59d818a6-50fc-44e8-8453-b4dbc9aeb456");
            double moeny = wChargeEntities.get(0).getMoney();
            resmoeny = hours*moeny;
        }else if(21<=phyInteger && phyInteger <=30) {
            wChargeEntities = walkDogChargeMapper.selectWalkDogChargeALLById("0d5fdff7-182b-4a47-9ea9-32cff5dd7127");
            double moeny = wChargeEntities.get(0).getMoney();
            resmoeny = hours*moeny;
        }else if(31<=phyInteger && phyInteger <=40) {
            wChargeEntities = walkDogChargeMapper.selectWalkDogChargeALLById("ca62404a-a078-405f-b938-0fd42fdd935b");
            double moeny = wChargeEntities.get(0).getMoney();
            resmoeny = hours*moeny;
        }else if(41<=phyInteger && phyInteger <=50) {
            wChargeEntities = walkDogChargeMapper.selectWalkDogChargeALLById("016f3a97-5aea-4feb-979d-a69166240e55");
            double moeny = wChargeEntities.get(0).getMoney();
            resmoeny = hours*moeny;
        }else if(51<=phyInteger && phyInteger <=60) {
            wChargeEntities = walkDogChargeMapper.selectWalkDogChargeALLById("b08969fe-ec9a-425a-ab21-51004c184e8d");
            double moeny = wChargeEntities.get(0).getMoney();
            resmoeny = hours*moeny;
        }else if(61<=phyInteger && phyInteger <=70) {
            wChargeEntities = walkDogChargeMapper.selectWalkDogChargeALLById("bfbfa981-7e76-4e52-82a8-f5cef52f40b7");
            double moeny = wChargeEntities.get(0).getMoney();
            resmoeny = hours*moeny;
        }else if(71<=phyInteger && phyInteger <=80) {
            wChargeEntities = walkDogChargeMapper.selectWalkDogChargeALLById("84c7cec7-226c-474d-99c8-8b578cec1e81");
            double moeny = wChargeEntities.get(0).getMoney();
            resmoeny = hours*moeny;
        }else if(81<= phyInteger) {
            wChargeEntities = walkDogChargeMapper.selectWalkDogChargeALLById("5dd496d4-8526-4fe3-a4b4-cb67df769490");
            double moeny = wChargeEntities.get(0).getMoney();
            resmoeny = hours*moeny;
        }
        return resmoeny;
    }
}
