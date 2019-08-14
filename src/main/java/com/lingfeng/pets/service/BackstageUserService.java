/**
 * 
 */
package com.lingfeng.pets.service;

import java.util.List;

import com.lingfeng.pets.entity.BackstageUserEntity;


/**
 * @author 谷春
 *
 */
public interface BackstageUserService {
    
    /**
     * 新增   
     * @author liuman
     * @param 
     * @return boolean
     * @throws Exception
     */
    public boolean addBackstageUser(BackstageUserEntity backstageUserEntity);
    
    /**
                * 根据id进行删除
     * @author liuman
     * @param 
     * @return boolean
     * @throws Exception
     */
    public boolean deleteBackstageUser(BackstageUserEntity backstageUserEntity);
    
    /**
             * 根据id进行修改
     * @author 谷春
     * @param 
     * @return boolean
     * @throws Exception
     */
    public boolean updateBackstageUser(BackstageUserEntity backstageUserEntity);
    
    /**
         * 查看全部
     * @author 谷春
     * @param 
     * @return List<BackstageUserEntity>
     * @throws Exception
     */
    public List<BackstageUserEntity> selectBackstageUserALL();

    /**
         * 分页查询
    * @author 谷春
    * @param 
    * @return List<BackstageUserEntity>
    * @throws Exception
    */
    public List<BackstageUserEntity> queryBackstageUserBySql(int currPage, int pageSize);
    
    /**
     * 模糊查询
     * @author 谷春
     * @param 
     * @return List<BackstageUserEntity>
     * @throws Exception
     */
    public List<BackstageUserEntity> likeBackstageUser(BackstageUserEntity backstageUserEntity);
    
    /**
     * 登录
     * @author 谷春
     * @param 
     * @return BackstageUserEntity
     * @throws Exception
     */
    public BackstageUserEntity login(String userName,String userPass);
    
    /**
     * 根据用户名查询数据
     * @author 谷春
     * @param 
     * @return BackstageUserEntity
     * @throws Exception
     */
    public BackstageUserEntity selectBackstageUserByuserName(String userName);
    
    /**
     * 查询总行数
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer selectCount();

}
