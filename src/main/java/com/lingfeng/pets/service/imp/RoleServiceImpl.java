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

import com.lingfeng.pets.entity.RoleEntity;
import com.lingfeng.pets.exception.BaseServiceException;
import com.lingfeng.pets.exception.ServiceErrCode;
import com.lingfeng.pets.mapper.RoleMapper;
import com.lingfeng.pets.service.RoleService;


/**
 * @author 谷春
 *
 */
@Service
public class RoleServiceImpl implements RoleService{
    
    /**
              * 注入角色表的接口类
     */
    @Autowired
    private RoleMapper roleMapper;
    
    /**
     * 新增的方法
     */
    @Override
    public boolean addRole(RoleEntity roleEntity) {
        roleEntity.setCreate_date(new Date());
        roleEntity.setId(UUID.randomUUID().toString());
        return roleMapper.addRole(roleEntity);
    }
    /**
     * 根据id删除角色信息
     */
    @Override
    public boolean deleteRole(RoleEntity roleEntity) {
        roleEntity.setLastModel_date(new Date());
        return roleMapper.deleteRole(roleEntity);
    }
    /**
     * 根据id修改角色信息
     */
    @Override
    public boolean updateRole(RoleEntity roleEntity) {
        roleEntity.setUpdate_date(new Date());
        return roleMapper.updateRole(roleEntity);
    }
    
    /**
     * 查询所有
     */
    @Override
    public List<RoleEntity> selectRoleALL() {
        return roleMapper.selectRoleALL();
    }
    
    /**
     * 根据用户id查询角色信息
     */
    @Override
    public RoleEntity selectRoleByUserId(String userId) {
        if (!StringUtils.isNotBlank(userId)) {
            throw new BaseServiceException("参数为空！", ServiceErrCode.REQ_PARAM_ERR);
        }
        return roleMapper.selectRoleByUserId(userId);
    }

}
