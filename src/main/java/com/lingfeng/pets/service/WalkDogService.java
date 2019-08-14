/**
 * 
 */
package com.lingfeng.pets.service;

import java.util.List;

import com.lingfeng.pets.entity.WalkDogEntity;

/**
 * @author 谷春
 *
 */

public interface WalkDogService {
    
    /**
     * 新增
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer insertWalkDog(WalkDogEntity walkDogEntity);
    
    /**
     * 删除
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer deleteWalkDog(WalkDogEntity walkDogEntity);
    
    /**
     * 修改
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer updateWalkDog(WalkDogEntity walkDogEntity);
    
    /**
     * 查询所有
     * @author 谷春
     * @param 
     * @return List<ReleasePetEntity>
     * @throws Exception
     */
    public List<WalkDogEntity> selectAllWalkDog();
    
    /**
     * 模糊分页查询
     * @author 谷春
     * @param 
     * @return List<ReleasePetEntity>
     * @throws Exception
     */
    public List<WalkDogEntity> selectLikeWalkDog(WalkDogEntity walkDogEntity); 
    
    
    /**
     * 查询总行数
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer selectCount();

}
