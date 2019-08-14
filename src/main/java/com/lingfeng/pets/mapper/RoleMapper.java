/**
 * 
 */
package com.lingfeng.pets.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lingfeng.pets.entity.RoleEntity;

/**
 * @author 谷春
 *
 */
@Mapper
public interface RoleMapper {
    

    /**
                    * 新增   
   * @author liuman
   * @param 
   * @return boolean
   * @throws Exception
   */
   public boolean addRole(RoleEntity roleEntity);
   
   /**
                   * 根据id进行删除
   * @author liuman
   * @param 
   * @return boolean
   * @throws Exception
   */
   public boolean deleteRole(RoleEntity roleEntity);
   
   /**
                    * 根据id进行修改
   * @author liuman
   * @param 
   * @return boolean
   * @throws Exception
   */
   public boolean updateRole(RoleEntity roleEntity);
   
   /**
                    * 查看全部
   * @author liuman
   * @param 
   * @throws Exception
   */
   public List<RoleEntity> selectRoleALL();
   
   /**
    *    根据用户ID查询角色名
    * @author 谷春
    * @param 
    * @return RoleEntity
    * @throws Exception
    */
   public RoleEntity selectRoleByUserId(String userId);

}
