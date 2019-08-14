/**
 * 
 */
package com.lingfeng.pets.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lingfeng.pets.entity.UserRoleEntity;

/**
 * @author 谷春
 *
 */
@Mapper
public interface UserRoleMapper {
    
    /**
     * 新增   
     * @author liuman
     * @param 
     * @return boolean
     * @throws Exception
     */
    public boolean addUserRole(UserRoleEntity userRoleEntity);
    
    /**
                * 根据id进行删除
     * @author liuman
     * @param 
     * @return boolean
     * @throws Exception
     */
    public boolean deleteUserRole(UserRoleEntity userRoleEntity);
    
    /**
             * 根据id进行修改
     * @author 谷春
     * @param 
     * @return boolean
     * @throws Exception
     */
    public boolean updateUserRole(UserRoleEntity userRoleEntity);
    
    /**
         * 查看全部
     * @author 谷春
     * @param 
     * @return List<UserRoleEntity>
     * @throws Exception
     */
    public List<UserRoleEntity> selectUserRoleALL();
    
    /**
         * 模糊分页查询
     * @author 谷春
     * @param 
     * @return List<UserRoleEntity>
     * @throws Exception
     */
    public List<UserRoleEntity> selectLikeUserRole(UserRoleEntity userRoleEntity);
    
    /**
     * 查询总行数
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer selectCount();
    

}
