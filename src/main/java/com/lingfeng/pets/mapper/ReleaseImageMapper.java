/**
 * 
 */
package com.lingfeng.pets.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lingfeng.pets.entity.ReleaseImageEntity;

/**
 * @author 谷春
 *
 */
@Mapper
public interface ReleaseImageMapper {
    
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
    public Integer insertBatchReleaseImage(@Param("list")List<ReleaseImageEntity> releaseImageEntity);
    
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
     * 分页查询
     * @author 谷春
     * @param 
     * @return List<ReleasePetEntity>
     * @throws Exception
     */
    public List<ReleaseImageEntity> selectLikeReleaseImage(ReleaseImageEntity releaseImageEntity);
    
    /**
     * 根据发布id查询数据
     * @author 谷春
     * @param 
     * @return List<ReleaseImageEntity>
     * @throws Exception
     */
    public List<ReleaseImageEntity> selectReleaseImageByreleaseId(@Param("releaseId")String releaseId);

    /**
     * 查询总行数
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer selectCount();
}
