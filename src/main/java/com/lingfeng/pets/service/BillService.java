/**
 * 
 */
package com.lingfeng.pets.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.lingfeng.pets.entity.BillEntity;


/**
 * @author 谷春
 *
 */
@Service
public interface BillService {
    
    /**
     * 新增数据
     * @author 谷春
     * @param 
     * @return boolean
     * @throws Exception
     */
     public Integer insertBill(BillEntity billEntity);
     
     /**
      * 删除数据
      * @author 谷春
      * @param 
      * @return boolean
      * @throws Exception
      */
     public Integer deleteBill(BillEntity billEntity);
     
     /**
      * 修改数据
      * @author 谷春
      * @param 
      * @return boolean
      * @throws Exception
      */
     public Integer updateBill(BillEntity billEntity);
     
     
     /**
      * 查看所有
      * @author 谷春
      * @param 
      * @return List<BillEntity>
      * @throws Exception
      */
     public List<BillEntity> selectAllBill();
     
     /**
      *模糊查询
      * @author 谷春
      * @param 
      * @return List<BillEntity>
      * @throws Exception
      */
     public List<BillEntity> selectLikeBill(BillEntity billEntity);
     
     /**
      *根据用户id查询出未读的信息 
      * @author 谷春
      * @param 
      * @return List<BillEntity>
      * @throws Exception
      */
     public List<BillEntity> selectBillByuserIdandRead(@Param("userId")String userId);

}
