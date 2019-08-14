/**
 * 
 */
package com.lingfeng.pets.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lingfeng.pets.entity.WithdrawEntity;

/**
 * @author 谷春
 *
 */
@Mapper
public interface WithdrawMapper {
    
    /**
         * 新增   
    * @author liuman
    * @param 
    * @return boolean
    * @throws Exception
    */
    public boolean addWithdraw(WithdrawEntity withdrawEntity);
    
    /**
        * 根据id进行删除
    * @author liuman
    * @param 
    * @return boolean
    * @throws Exception
    */
    public boolean deleteWithdraw(WithdrawEntity withdrawEntity);
    
    /**
         * 根据id进行修改
    * @author liuman
    * @param 
    * @return boolean
    * @throws Exception
    */
    public boolean updateWithdraw(WithdrawEntity withdrawEntity);
    
    /**
         * 查看全部
    * @author liuman
    * @param 
    * @throws Exception
    */
    public List<WithdrawEntity> selectWithdrawALL();

}
