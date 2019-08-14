/**
 * 
 */
package com.lingfeng.pets.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingfeng.pets.entity.ReleaseImageEntity;
import com.lingfeng.pets.mapper.ReleaseImageMapper;
import com.lingfeng.pets.service.ReleaseImageService;

/**
 * @author 谷春
 *
 */
@Service
public class ReleaseImageServiceImpl implements ReleaseImageService{
    
    /**
     * 注入发布图片表的接口类
     */
    @Autowired
    private ReleaseImageMapper releaseImageMapper;

    @Override
    public Integer insertReleaseImage(ReleaseImageEntity releaseImageEntity) {
        return releaseImageMapper.insertReleaseImage(releaseImageEntity);
    }

    @Override
    public Integer insertBatchReleaseImage(List<ReleaseImageEntity> releaseImageEntity) {
        return releaseImageMapper.insertBatchReleaseImage(releaseImageEntity);
    }

    @Override
    public Integer deleteReleaseImage(ReleaseImageEntity releaseImageEntity) {
        return releaseImageMapper.deleteReleaseImage(releaseImageEntity);
    }

    @Override
    public Integer updateReleaseImage(ReleaseImageEntity releaseImageEntity) {
        return releaseImageMapper.updateReleaseImage(releaseImageEntity);
    }

    @Override
    public List<ReleaseImageEntity> selectAllReleaseImage() {
        return releaseImageMapper.selectAllReleaseImage();
    }

    @Override
    public List<ReleaseImageEntity> selectReleaseImageByreleaseId(String releaseId) {
        return releaseImageMapper.selectReleaseImageByreleaseId(releaseId);
    }

    @Override
    public List<ReleaseImageEntity> selectLikeReleaseImage(ReleaseImageEntity releaseImageEntity) {
        return releaseImageMapper.selectLikeReleaseImage(releaseImageEntity);
    }

    @Override
    public Integer selectCount() {
        return releaseImageMapper.selectCount();
    }
    

}
