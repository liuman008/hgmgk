/**
 * 
 */
package com.lingfeng.pets.service;

import java.util.List;

import com.lingfeng.pets.entity.UserEntity;

/**
 * @author 谷春
 *
 */
public interface UserService {
    /**
     * 新增会员
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer insertUser(UserEntity userEntity);
    
    /**
     * 根据openid查询数据
     * @author 谷春
     * @param 
     * @return UserEntity
     * @throws Exception
     */
    public UserEntity selectUserByopenId(String openid);
    
    /**
     * 删除数据
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer deleteUser(UserEntity userEntity);
    
    /**
         * 删除数据
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer updateUser(UserEntity userEntity);
    
    /**
     * 查询所有
     * @author 谷春
     * @param 
     * @return List<UserEntity>
     * @throws Exception
     */
    public List<UserEntity> selectAll();
    
    /**
     * 根据id查询数据
     * @author 谷春
     * @param 
     * @return UserEntity
     * @throws Exception
     */
    public UserEntity selectUserByid(String id);
    
    /**
     * 查询总行数
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer selectCount();
    
    /**
     * 模糊分页查询
     * @author 谷春
     * @param 
     * @return List<UserEntity>
     * @throws Exception
     */
    public List<UserEntity> selectLikeUser(UserEntity userEntity);

}
