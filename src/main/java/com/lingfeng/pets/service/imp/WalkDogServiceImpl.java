/**
 * 
 */
package com.lingfeng.pets.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingfeng.pets.entity.WalkDogEntity;
import com.lingfeng.pets.mapper.WalkDogMapper;
import com.lingfeng.pets.service.WalkDogService;

/**
 * @author 谷春
 *
 */
@Service
public class WalkDogServiceImpl implements WalkDogService{
    /**
     * 注入遛狗表的接口类
     */
    @Autowired
    private WalkDogMapper walkDogMapper;
    
    @Override
    public Integer insertWalkDog(WalkDogEntity walkDogEntity) {
        return walkDogMapper.insertWalkDog(walkDogEntity);
    }

    @Override
    public Integer deleteWalkDog(WalkDogEntity walkDogEntity) {
        return walkDogMapper.deleteWalkDog(walkDogEntity);
    }

    @Override
    public Integer updateWalkDog(WalkDogEntity walkDogEntity) {
        return walkDogMapper.updateWalkDog(walkDogEntity);
    }

    @Override
    public List<WalkDogEntity> selectAllWalkDog() {
        return walkDogMapper.selectAllWalkDog();
    }

    @Override
    public List<WalkDogEntity> selectLikeWalkDog(WalkDogEntity walkDogEntity) {
        return walkDogMapper.selectLikeWalkDog(walkDogEntity);
    }

    @Override
    public Integer selectCount() {
        return walkDogMapper.selectCount();
    }

}
