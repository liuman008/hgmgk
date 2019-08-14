/**
 * 
 */
package com.lingfeng.pets.service.imp;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingfeng.pets.entity.UserEntity;
import com.lingfeng.pets.exception.BaseServiceException;
import com.lingfeng.pets.exception.ServiceErrCode;
import com.lingfeng.pets.mapper.UserMapper;
import com.lingfeng.pets.service.UserService;

/**
 * @author 谷春
 *
 */
@Service
public class UserServiceImpl implements UserService{
    
    /**
     * 注入用户表的接口类
     */
    @Autowired
    private UserMapper userMapper;
    
    @Override
    public Integer insertUser(UserEntity userEntity) {
        userEntity.setId(UUID.randomUUID().toString());
        userEntity.setCreate_date(new Date());
        return userMapper.insertUser(userEntity);
    }

    @Override
    public UserEntity selectUserByopenId(String openid) {
        if (!StringUtils.isNotBlank(openid)) {
            throw new BaseServiceException("openid为空！", ServiceErrCode.REQ_PARAM_ERR);
        }
        return userMapper.selectUserByopenId(openid);
    }

    @Override
    public Integer deleteUser(UserEntity userEntity) {
        userEntity.setLastModel_date(new Date());
        return userMapper.deleteUser(userEntity);
    }

    @Override
    public Integer updateUser(UserEntity userEntity) {
        userEntity.setUpdate_date(new Date());
        return userMapper.updateUser(userEntity);
    }

    @Override
    public List<UserEntity> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public UserEntity selectUserByid(String id) {
        return userMapper.selectUserByid(id);
    }

    @Override
    public Integer selectCount() {
        return userMapper.selectCount();
    }

    @Override
    public List<UserEntity> selectLikeUser(UserEntity userEntity) {
        return userMapper.selectLikeUser(userEntity);
    }

}
