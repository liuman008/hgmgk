/**
 * 
 */
package com.lingfeng.pets.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lingfeng.pets.entity.FosterEntity;

/**
 * @author 谷春
 *
 */
@Mapper
public interface FosterMapper {
    
    /**
     * 新增
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer insertFoster(FosterEntity fosterEntity);
    
    /**
     * 删除
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer deleteFoster(FosterEntity fosterEntity);
    
    /**
     * 修改
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer updateFoster(FosterEntity fosterEntity);
    
    /**
     * 查询所有
     * @author 谷春
     * @param 
     * @return List<ReleasePetEntity>
     * @throws Exception
     */
    public List<FosterEntity> selectAllFoster();
     
     /**
      *模糊分页查询
      * @author 谷春
      * @param 
      * @return List<ReleasePetEntity>
      * @throws Exception
      */
     public List<FosterEntity> selectLikeFoster(FosterEntity fosterEntity);
     
     /**
                  * 查询总行数
      * @author 谷春
      * @param 
      * @return Integer
      * @throws Exception
      */
     public Integer selectCount();
}
