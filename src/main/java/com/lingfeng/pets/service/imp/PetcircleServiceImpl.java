/**
 * 
 */
package com.lingfeng.pets.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingfeng.pets.entity.PetcircleEntity;
import com.lingfeng.pets.mapper.PetcircleMapper;
import com.lingfeng.pets.service.PetcircleService;

/**
 * @author 谷春
 *
 */
@Service
public class PetcircleServiceImpl implements PetcircleService{
    
    /**
     * 注入宠物圈的接口类
     */
    @Autowired
    private PetcircleMapper petcircleMapper;
    
    
    
    @Override
    public Integer insertPetcircle(PetcircleEntity petcircleEntity) {
        return petcircleMapper.insertPetcircle(petcircleEntity);
    }

    @Override
    public Integer deletePetcircle(PetcircleEntity petcircleEntity) {
        return petcircleMapper.deletePetcircle(petcircleEntity);
    }

    @Override
    public Integer updatePetcircle(PetcircleEntity petcircleEntity) {
        return petcircleMapper.updatePetcircle(petcircleEntity);
    }

    @Override
    public List<PetcircleEntity> selectAllPetcircle() {
        return petcircleMapper.selectAllPetcircle();
    }

    @Override
    public List<PetcircleEntity> selectlikePetcircle(PetcircleEntity petcircleEntity) {
        return petcircleMapper.selectlikePetcircle(petcircleEntity);
    }

    @Override
    public Integer selectCount() {
        return petcircleMapper.selectCount();
    }

}
