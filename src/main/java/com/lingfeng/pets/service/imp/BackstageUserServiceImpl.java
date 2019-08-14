/**
 * 
 */
package com.lingfeng.pets.service.imp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingfeng.pets.entity.BackstageUserEntity;
import com.lingfeng.pets.exception.BaseServiceException;
import com.lingfeng.pets.exception.ServiceErrCode;
import com.lingfeng.pets.mapper.BackstageUserMapper;
import com.lingfeng.pets.service.BackstageUserService;


/**
 * @author 谷春
 *
 */
@Service
public class BackstageUserServiceImpl implements BackstageUserService{
    
    
    /**
     * 注入后台用户表的接口类
     */
    
    @Autowired
    private BackstageUserMapper backstageUserMapper;
    
    @Override
    public boolean addBackstageUser(BackstageUserEntity backstageUserEntity) {
        backstageUserEntity.setCreate_date(new Date());
        return backstageUserMapper.addBackstageUser(backstageUserEntity);
    }

    @Override
    public boolean deleteBackstageUser(BackstageUserEntity backstageUserEntity) {
        backstageUserEntity.setLastModel_date(new Date());
        return backstageUserMapper.deleteBackstageUser(backstageUserEntity);
    }

    @Override
    public boolean updateBackstageUser(BackstageUserEntity backstageUserEntity) {
        backstageUserEntity.setUpdate_date(new Date());
        return backstageUserMapper.updateBackstageUser(backstageUserEntity);
    }

    @Override
    public List<BackstageUserEntity> selectBackstageUserALL() {
        return backstageUserMapper.selectBackstageUserALL();
    }
    /**
     * 分页查询
     */
    @Override
    public List<BackstageUserEntity> queryBackstageUserBySql(int currPage, int pageSize) {
        Map<String, Object> pages = new HashMap<String, Object>();
        pages.put("currIndex", (currPage-1)*pageSize);
        pages.put("pageSize", pageSize);
        return backstageUserMapper.queryBackstageUserBySql(pages);
    }
    
    /**
     * 模糊查询
     */
    @Override
    public List<BackstageUserEntity> likeBackstageUser(BackstageUserEntity backstageUserEntity) {
        if (!StringUtils.isNotBlank(backstageUserEntity.getCurrIndex().toString()) && !StringUtils.isNotBlank(backstageUserEntity.getPageSize().toString())) {
            backstageUserEntity.setCurrIndex((backstageUserEntity.getCurrIndex()-1)*backstageUserEntity.getPageSize());
        }
        return backstageUserMapper.likeBackstageUser(backstageUserEntity);
    }

    @Override
    public BackstageUserEntity login(String userName, String userPass) {
        if (!StringUtils.isNotBlank(userName)||!StringUtils.isNotBlank(userPass)) {
            throw new BaseServiceException("参数不完整！", ServiceErrCode.REQ_PARAM_ERR);
        }
        return backstageUserMapper.login(userName, userPass);
    }

    @Override
    public BackstageUserEntity selectBackstageUserByuserName(String userName) {
        if (!StringUtils.isNotBlank(userName)) {
            throw new BaseServiceException("参数不完整！", ServiceErrCode.REQ_PARAM_ERR);
        }
        return backstageUserMapper.selectBackstageUserByuserName(userName);
    }

    @Override
    public Integer selectCount() {
        return backstageUserMapper.selectCount();
    }

}
