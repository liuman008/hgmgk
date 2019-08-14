/**
 * 
 */
package com.lingfeng.pets.service;

import java.util.List;

import com.lingfeng.pets.entity.BannerEntity;

/**
 * @author 谷春
 *
 */
public interface BannerService {
    
    
    /**
             * 新增
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer insertBanner(BannerEntity bannerEntity);
    
    /**
             * 删除
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer deleteBanner(BannerEntity bannerEntity);
    
    /**
     * 修改
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer updateBanner(BannerEntity bannerEntity);
    
    /**
     * 查询所有
     * @author 谷春
     * @param 
     * @return List<ReleasePetEntity>
     * @throws Exception
     */
    public List<BannerEntity> selectAllBanner();
    
    /**
     * 模糊分页查询
     * @author 谷春
     * @param 
     * @return List<BannerEntity>
     * @throws Exception
     */
    public List<BannerEntity> selectLikeBanner(BannerEntity bannerEntity);
    
    
    /**
     * 查询总行数
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer selectCount();

}
