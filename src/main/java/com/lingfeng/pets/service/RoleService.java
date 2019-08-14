/**
 * 
 */
package com.lingfeng.pets.service;

import java.util.List;

import com.lingfeng.pets.entity.RoleEntity;

/**
 * @author 谷春
 *
 */
public interface RoleService {
    

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
