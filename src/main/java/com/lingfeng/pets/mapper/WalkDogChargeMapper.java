/**
 * 
 */
package com.lingfeng.pets.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lingfeng.pets.entity.WalkDogChargeEntity;

/**
 * @author 谷春
 *
 */
@Mapper
public interface WalkDogChargeMapper {
            
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
        public List<WalkDogChargeEntity> selectWalkDogChargeALLById(@Param("id")String id);


}
