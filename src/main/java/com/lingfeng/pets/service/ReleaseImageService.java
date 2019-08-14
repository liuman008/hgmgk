/**
 * 
 */
package com.lingfeng.pets.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lingfeng.pets.entity.ReleaseImageEntity;

/**
 * @author 谷春
 *
 */
public interface ReleaseImageService {
    
    
    /**
     * 新增
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer insertReleaseImage(ReleaseImageEntity releaseImageEntity);
    
    /**
     * 批量新增
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer insertBatchReleaseImage(List<ReleaseImageEntity> releaseImageEntity);
    
    /**
     * 删除
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer deleteReleaseImage(ReleaseImageEntity releaseImageEntity);
    
    /**
     * 修改
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer updateReleaseImage(ReleaseImageEntity releaseImageEntity);
    
    /**
     * 查询所有
     * @author 谷春
     * @param 
     * @return List<ReleasePetEntity>
     * @throws Exception
     */
    public List<ReleaseImageEntity> selectAllReleaseImage();
    
    /**
     * 根据发布id查询数据
     * @author 谷春
     * @param 
     * @return List<ReleaseImageEntity>
     * @throws Exception
     */
    public List<ReleaseImageEntity> selectReleaseImageByreleaseId(@Param("releaseId")String releaseId);

    /**
     * 分页查询
     * @author 谷春
     * @param 
     * @return List<ReleasePetEntity>
     * @throws Exception
     */
    public List<ReleaseImageEntity> selectLikeReleaseImage(ReleaseImageEntity releaseImageEntity);
    
    /**
     * 查询总行数
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer selectCount();
}
