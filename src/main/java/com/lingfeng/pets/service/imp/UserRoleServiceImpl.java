/**
 * 
 */
package com.lingfeng.pets.service.imp;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingfeng.pets.entity.UserRoleEntity;
import com.lingfeng.pets.mapper.UserRoleMapper;
import com.lingfeng.pets.service.UserRoleService;


/**
 * @author 谷春
 *
 */
@Service
public class UserRoleServiceImpl implements UserRoleService{
    /**
     * 注入用户角色表的接口类
     */
    @Autowired
    private UserRoleMapper userRoleMapper;
    
    @Override
    public boolean addUserRole(UserRoleEntity userRoleEntity) {
        userRoleEntity.setCreate_date(new Date());
        userRoleEntity.setId(UUID.randomUUID().toString());
        return userRoleMapper.addUserRole(userRoleEntity);
    }

    @Override
    public boolean deleteUserRole(UserRoleEntity userRoleEntity) {
        userRoleEntity.setLastModel_date(new Date());
        return userRoleMapper.deleteUserRole(userRoleEntity);
    }

    @Override
    public boolean updateUserRole(UserRoleEntity userRoleEntity) {
        userRoleEntity.setUpdate_date(new Date());
        return userRoleMapper.updateUserRole(userRoleEntity);
    }

    @Override
    public List<UserRoleEntity> selectUserRoleALL() {
        return userRoleMapper.selectUserRoleALL();
    }

    @Override
    public List<UserRoleEntity> selectLikeUserRole(UserRoleEntity userRoleEntity) {
        return userRoleMapper.selectLikeUserRole(userRoleEntity);
    }

    @Override
    public Integer selectCount() {
        return userRoleMapper.selectCount();
    }

}
