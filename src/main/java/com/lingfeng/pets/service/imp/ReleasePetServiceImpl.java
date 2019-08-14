/**
 * 
 */
package com.lingfeng.pets.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingfeng.pets.entity.ReleaseAllEntity;
import com.lingfeng.pets.entity.ReleasePetEntity;
import com.lingfeng.pets.mapper.ReleasePetMapper;
import com.lingfeng.pets.service.ReleasePetService;

/**
 * @author 谷春
 *
 */
@Service
public class ReleasePetServiceImpl implements ReleasePetService{
    /**
     * 注入发布表的接口类
     */
    @Autowired
    private ReleasePetMapper releasePetMapper;
    
    /**
     * 新增
     */
    @Override
    public Integer insertReleasePet(ReleasePetEntity releasePetEntity) {
        return releasePetMapper.insertReleasePet(releasePetEntity);
    }

    /**
     * 删除
     */
    @Override
    public Integer deleteReleasePet(ReleasePetEntity releasePetEntity) {
        return releasePetMapper.deleteReleasePet(releasePetEntity);
    }

    /**
     * 修改
     */
    @Override
    public Integer updateReleasePet(ReleasePetEntity releasePetEntity) {
        return releasePetMapper.updateReleasePet(releasePetEntity);
    }
    
    /**
     * 查询所有
     */
    @Override
    public List<ReleasePetEntity> selectAllReleasePet() {
        return releasePetMapper.selectAllReleasePet();
    }


    @Override
    public List<ReleasePetEntity> selectLikeReleasePet(ReleasePetEntity releasePetEntity) {
        return releasePetMapper.selectLikeReleasePet(releasePetEntity);
    }

    @Override
    public List<ReleaseAllEntity> selectReleasePetFoster(String id) {
        return releasePetMapper.selectReleasePetFoster(id);
    }

    @Override
    public List<ReleaseAllEntity> selectReleasePetWalkDog(String id) {
        return releasePetMapper.selectReleasePetWalkDog(id);
    }

    @Override
    public Integer selectCount() {
        return releasePetMapper.selectCount();
    }

    @Override
    public List<ReleasePetEntity> selectReleasePetBytype(Map<String, Object> maps) {
        return releasePetMapper.selectReleasePetBytype(maps);
    }

    @Override
    public ReleasePetEntity selectReleasePetByuserIdandstate(String id, Integer state) {
        return releasePetMapper.selectReleasePetByuserIdandstate(id, state);
    }

    @Override
    public String selectphone(String id) {
        return releasePetMapper.selectphone(id);
    }


}
