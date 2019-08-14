/**
 * 
 */
package com.lingfeng.pets.service;

import java.util.List;

import com.lingfeng.pets.entity.WalkDogChargeEntity;

/**
 * @author 谷春
 *
 */
public interface WalkDogChargeService {
    
            
            /**
             * 新增   
        * @author liuman
        * @param 
        * @return boolean
        * @throws Exception
        */
        public boolean addWalkDogCharge(WalkDogChargeEntity WalkDogChargeEntity);
        
        /**
            * 根据id进行删除
        * @author liuman
        * @param 
        * @return boolean
        * @throws Exception
        */
        public boolean deleteWalkDogCharge(WalkDogChargeEntity WalkDogChargeEntity);
        
        /**
             * 根据id进行修改
        * @author liuman
        * @param 
        * @return boolean
        * @throws Exception
        */
        public boolean updateWalkDogCharge(WalkDogChargeEntity WalkDogChargeEntity);
        
        /**
             * 查看全部
        * @author liuman
        * @param 
        * @throws Exception
        */
        public List<WalkDogChargeEntity> selectWalkDogChargeALL();
        
        /**
                 * 根据id查询数据
        * @author liuman
        * @param 
        * @throws Exception
        */
        public List<WalkDogChargeEntity> selectWalkDogChargeALLById(String id);


}
