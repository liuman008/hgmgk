/**
 * 
 */
package com.lingfeng.pets.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingfeng.pets.entity.ChargeEntity;
import com.lingfeng.pets.mapper.ChargeMapper;
import com.lingfeng.pets.service.ChargeService;

/**
 * @author 谷春
 *
 */
@Service
public class ChargeServiceImpl implements ChargeService{
    
    /**
     * 注入服务费表的接口类
     */
    @Autowired
    private ChargeMapper chargeMapper;

    @Override
    public Integer insertCharge(ChargeEntity chargeEntity) {
        return chargeMapper.insertCharge(chargeEntity);
    }

    @Override
    public Integer deleteCharge(ChargeEntity chargeEntity) {
        return chargeMapper.deleteCharge(chargeEntity);
    }

    @Override
    public Integer updateCharge(ChargeEntity chargeEntity) {
        return chargeMapper.updateCharge(chargeEntity);
    }

    @Override
    public List<ChargeEntity> selectAllCharge() {
        return chargeMapper.selectAllCharge();
    }

    @Override
    public List<ChargeEntity> selectLikeCharge(ChargeEntity chargeEntity) {
        return chargeMapper.selectLikeCharge(chargeEntity);
    }

    @Override
    public Integer selectCount() {
        return chargeMapper.selectCount();
    }
    
    /**
     * 计算寄养服务费
     * @author 谷春
     * @param 
     * @return double
     * @throws Exception
     */
    public double selectCharge(Integer days,String physique){
        Integer phyInteger = Integer.parseInt(physique);
        ChargeEntity chargeEntity = new ChargeEntity();
        List<ChargeEntity>  res = new ArrayList<ChargeEntity>();
        double resmoeny = 0 ;
        if (0<phyInteger && phyInteger <=10) {
            chargeEntity.setId("4238d751-b6c8-4f27-90ad-89816aa96843");
            res = chargeMapper.selectLikeCharge(chargeEntity);
            double moeny = res.get(0).getMoney();
             resmoeny = days*moeny;
        }else if (11<=phyInteger && phyInteger <=20) {
            chargeEntity.setId("91095c8f-4bfb-4d62-908c-bf5c80190b50");
            res = chargeMapper.selectLikeCharge(chargeEntity);
            double moeny = res.get(0).getMoney();
            resmoeny = days*moeny;
        }else if(21<=phyInteger && phyInteger <=30) {
            chargeEntity.setId("4fd3406c-7e10-414c-be28-3e044773bed7");
            System.out.println("kkkkkk=="+chargeMapper.selectLikeCharge(chargeEntity));
            res = chargeMapper.selectLikeCharge(chargeEntity);
            double moeny = res.get(0).getMoney();
            resmoeny = days*moeny;
        }else if(31<=phyInteger && phyInteger <=40) {
            chargeEntity.setId("f12e8cff-47b5-4c76-8be6-b04c8a8d70c3");
            res = chargeMapper.selectLikeCharge(chargeEntity);
            double moeny = res.get(0).getMoney();
            resmoeny = days*moeny;
        }else if(41<=phyInteger && phyInteger <=50) {
            chargeEntity.setId("b0fd07ee-e9b0-49fc-8070-542752e9ba19");
            res = chargeMapper.selectLikeCharge(chargeEntity);
            double moeny = res.get(0).getMoney();
            resmoeny = days*moeny;
        }else if(51<=phyInteger && phyInteger <=60) {
            chargeEntity.setId("8dbe30b8-2eeb-4d4f-8761-4c4112f16821");
            res = chargeMapper.selectLikeCharge(chargeEntity);
            double moeny = res.get(0).getMoney();
            resmoeny = days*moeny;
        }else if(61<=phyInteger && phyInteger <=70) {
            chargeEntity.setId("26284681-e4ef-4bd6-9d57-772f40281eed");
            res = chargeMapper.selectLikeCharge(chargeEntity);
            double moeny = res.get(0).getMoney();
            resmoeny = days*moeny;
        }else if(71<=phyInteger && phyInteger <=80) {
            chargeEntity.setId("4b7c4ee1-1c08-47bc-93c2-1d58a50dd58e");
            res = chargeMapper.selectLikeCharge(chargeEntity);
            double moeny = res.get(0).getMoney();
            resmoeny = days*moeny;
        }else if(81<= phyInteger) {
            chargeEntity.setId("44b050d3-176c-4fd4-a73b-93a297c023ee");
            res = chargeMapper.selectLikeCharge(chargeEntity);
            double moeny = res.get(0).getMoney();
            resmoeny = days*moeny;
        }
        return resmoeny;
    }

}
