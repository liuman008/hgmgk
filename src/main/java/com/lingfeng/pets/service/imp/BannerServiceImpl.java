/**
 * 
 */
package com.lingfeng.pets.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingfeng.pets.entity.BannerEntity;
import com.lingfeng.pets.mapper.BannerMapper;
import com.lingfeng.pets.service.BannerService;

/**
 * @author 谷春
 *
 */
@Service
public class BannerServiceImpl implements BannerService{
    
    /**
     * 注入banner表的接口类
     */
    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public Integer insertBanner(BannerEntity bannerEntity) {
        return bannerMapper.insertBanner(bannerEntity);
    }

    @Override
    public Integer deleteBanner(BannerEntity bannerEntity) {
        return bannerMapper.deleteBanner(bannerEntity);
    }

    @Override
    public Integer updateBanner(BannerEntity bannerEntity) {
        return bannerMapper.updateBanner(bannerEntity);
    }

    @Override
    public List<BannerEntity> selectAllBanner() {
        return bannerMapper.selectAllBanner();
    }

    @Override
    public List<BannerEntity> selectLikeBanner(BannerEntity bannerEntity) {
        return bannerMapper.selectLikeBanner(bannerEntity);
    }

    @Override
    public Integer selectCount() {
        return bannerMapper.selectCount();
    }

}
