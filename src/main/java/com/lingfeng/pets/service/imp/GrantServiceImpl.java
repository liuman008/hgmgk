/**
 * 
 */
package com.lingfeng.pets.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingfeng.pets.entity.GrantEntity;
import com.lingfeng.pets.mapper.GrantMapper;
import com.lingfeng.pets.service.GrantService;

/**
 * @author 谷春
 *
 */
@Service
public class GrantServiceImpl implements GrantService{
    
    /**
     * 注入点赞类的接口类
     */
    @Autowired
    private GrantMapper grantMapper;
    
    @Override
    public Integer insertGrant(GrantEntity grantEntity) {
        return grantMapper.insertGrant(grantEntity);
    }

    @Override
    public Integer deleteGrant(GrantEntity grantEntity) {
        return grantMapper.deleteGrant(grantEntity);
    }

    @Override
    public Integer updateGrant(GrantEntity grantEntity) {
        return grantMapper.updateGrant(grantEntity);
    }

    @Override
    public List<GrantEntity> selectAllGrant() {
        return grantMapper.selectAllGrant();
    }

    @Override
    public GrantEntity selectGrantByuid(String releaseId, String uid) {
        return grantMapper.selectGrantByuid(releaseId, uid);
    }

}
