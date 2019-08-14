/**
 * 
 */
package com.lingfeng.pets.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingfeng.pets.entity.FosterEntity;
import com.lingfeng.pets.mapper.FosterMapper;
import com.lingfeng.pets.service.FosterService;

/**
 * @author 谷春
 *
 */
@Service
public class FosterServiceImpl implements FosterService{
    /**
     * 注入寄养表的接口类
     */
    @Autowired
    private FosterMapper fosterMapper;
    
    
    @Override
    public Integer insertFoster(FosterEntity fosterEntity) {
        return fosterMapper.insertFoster(fosterEntity);
    }

    @Override
    public Integer deleteFoster(FosterEntity fosterEntity) {
        return fosterMapper.deleteFoster(fosterEntity);
    }

    @Override
    public Integer updateFoster(FosterEntity fosterEntity) {
        return fosterMapper.updateFoster(fosterEntity);
    }

    @Override
    public List<FosterEntity> selectAllFoster() {
        return fosterMapper.selectAllFoster();
    }

    @Override
    public List<FosterEntity> selectLikeFoster(FosterEntity fosterEntity) {
        return fosterMapper.selectAllFoster();
    }

    @Override
    public Integer selectCount() {
        return fosterMapper.selectCount();
    }

}
