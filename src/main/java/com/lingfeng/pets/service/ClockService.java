/**
 * 
 */
package com.lingfeng.pets.service;

import java.util.List;

import com.lingfeng.pets.entity.ClockEntity;

/**
 * @author 谷春
 *
 */
public interface ClockService {
    
    /**
     * 新增
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer insertClock(ClockEntity clockEntity);
    
    /**
     * 删除
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer deleteClock(ClockEntity clockEntity);
    
    /**
     * 修改
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer updateClock(ClockEntity clockEntity);
    
    /**
     * 查询所有
     * @author 谷春
     * @param 
     * @return List<ReleasePetEntity>
     * @throws Exception
     */
    public List<ClockEntity> selectAllClock();
    
    /**
     * @author 谷春
     * @param 
     * @return List<ClockEntity>
     * @throws Exception
     */
    public List<ClockEntity> selectLikeClocks(ClockEntity clockEntity);
    
    /**
     * 查询总行数
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer selectCount();

}
