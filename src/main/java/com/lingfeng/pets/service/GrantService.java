/**
 * 
 */
package com.lingfeng.pets.service;

import java.util.List;

import com.lingfeng.pets.entity.GrantEntity;

/**
 * @author 谷春
 *
 */
public interface GrantService {
    
    /**
     * 新增数据
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer insertGrant(GrantEntity grantEntity);
    
    /**
     * 删除数据
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer deleteGrant(GrantEntity grantEntity);
    
    /**
     * 修改数据
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer updateGrant(GrantEntity grantEntity);
    
    /**
     * 查询数据
     * @author 谷春
     * @param 
     * @return List<GrantEntity>
     * @throws Exception
     */
    public List<GrantEntity> selectAllGrant();
    
    /**
     * 根据用户id和发布id查询数据
     * @author 谷春
     * @param 
     * @return GrantEntity
     * @throws Exception
     */
    public GrantEntity selectGrantByuid(String releaseId,String uid);

}
