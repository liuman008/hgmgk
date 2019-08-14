/**
 * 
 */
package com.lingfeng.pets.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lingfeng.pets.entity.UserEntity;

/**
 * @author Liuman
 *
 */
@Mapper
public interface UserMapper {
    
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
    public UserEntity selectUserByopenId(@Param("openid")String openid);
    
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
     * 模糊分页查询
     * @author 谷春
     * @param 
     * @return List<UserEntity>
     * @throws Exception
     */
    public List<UserEntity> selectLikeUser(UserEntity userEntity);
    
    /**
     * 根据id查询数据
     * @author 谷春
     * @param 
     * @return UserEntity
     * @throws Exception
     */
    public UserEntity selectUserByid(@Param("id")String id);
    
    /**
     * 查询总行数
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer selectCount();
}
