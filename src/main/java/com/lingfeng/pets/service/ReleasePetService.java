/**
 * 
 */
package com.lingfeng.pets.service;

import java.util.List;
import java.util.Map;

import com.lingfeng.pets.entity.ReleaseAllEntity;
import com.lingfeng.pets.entity.ReleasePetEntity;

/**
 * @author 谷春
 *
 */
public interface ReleasePetService {
    
    /**
     * 新增
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer insertReleasePet(ReleasePetEntity releasePetEntity);
    
    /**
     * 删除
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer deleteReleasePet(ReleasePetEntity releasePetEntity);
    
    /**
     * 修改
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer updateReleasePet(ReleasePetEntity releasePetEntity);
    
    /**
     * 查询所有
     * @author 谷春
     * @param 
     * @return List<ReleasePetEntity>
     * @throws Exception
     */
    public List<ReleasePetEntity> selectAllReleasePet();
    
    /**
     * 根据类型查询数据
     * @author 谷春
     * @param 
     * @return List<ReleasePetEntity>
     * @throws Exception
     */
    public List<ReleasePetEntity> selectReleasePetBytype(Map<String, Object> maps);
    
    /**
     * 模糊查询
     * @author 谷春
     * @param 
     * @return List<ReleasePetEntity>
     * @throws Exception
     */
    public List<ReleasePetEntity> selectLikeReleasePet(ReleasePetEntity releasePetEntity);
    
    /**
     * 查询寄养
     * @author 谷春
     * @param 
     * @return List<ReleaseAllEntity>
     * @throws Exception
     */
    public List<ReleaseAllEntity> selectReleasePetFoster(String id);
    
    /**
     * 查询遛狗
     * @author 谷春
     * @param 
     * @return List<ReleaseAllEntity>
     * @throws Exception
     */
    public List<ReleaseAllEntity> selectReleasePetWalkDog(String id);
    
    /**
     * 查询总行数
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer selectCount();
    
    /**
     * 根据用户id和状态查询数据
     * @author 谷春
     * @param 
     * @return ReleasePetEntity
     * @throws Exception
     */
    public ReleasePetEntity selectReleasePetByuserIdandstate(String id,Integer state);
    
    
    /**
     * 得到用户的电话号码
     * @author 谷春
     * @param 
     * @return String
     * @throws Exception
     */
    public String selectphone(String id);
}
