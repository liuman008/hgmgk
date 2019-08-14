/**
 * 
 */
package com.lingfeng.pets.service;

import java.util.List;

import com.lingfeng.pets.entity.ChargeEntity;

/**
 * @author 谷春
 *
 */

public interface ChargeService {
    
    /**
     * 新增
* @author 谷春
* @param 
* @return Integer
* @throws Exception
*/
public Integer insertCharge(ChargeEntity chargeEntity);

/**
     * 删除
* @author 谷春
* @param 
* @return Integer
* @throws Exception
*/
public Integer deleteCharge(ChargeEntity chargeEntity);

/**
* 修改
* @author 谷春
* @param 
* @return Integer
* @throws Exception
*/
public Integer updateCharge(ChargeEntity chargeEntity);

/**
* 查询所有
* @author 谷春
* @param 
* @return List<ReleasePetEntity>
* @throws Exception
*/
public List<ChargeEntity> selectAllCharge();

/**
 * 模糊分页查询
 * @author 谷春
 * @param 
 * @return List<ChargeEntity>
 * @throws Exception
 */
public List<ChargeEntity> selectLikeCharge(ChargeEntity chargeEntity);

/**
 * 查询总行数
* @author 谷春
* @param 
* @return Integer
* @throws Exception
*/
public Integer selectCount();


}
