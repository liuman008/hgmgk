/**
 * 
 */
package com.lingfeng.pets.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.lingfeng.pets.entity.RoleEntity;
import com.lingfeng.pets.service.RoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author 谷春
 *
 */
@RestController("RoleController") 
@RequestMapping("/api")
@CrossOrigin
@Api(value = "[角色表]资源操作接口", tags = "roleController")
public class RoleController {
    
    /**
     * 注入角色表的实现类
     */
    @Autowired
    private RoleService roleService;
    
    /**
         * 新增角色表信息
    * @author 谷春
    * @param 
    * @return boolean
    * @throws Exception
    */
    @PostMapping("/role")
    @Timed
    @ApiOperation(value = "创建角色表信息", notes = "根据RoleEntity对象新增角色表信息")
    public boolean addRole(@ApiParam @RequestBody @Validated RoleEntity roleEntity) {
        return roleService.addRole(roleEntity);
    }
    
    /**
         *   删除角色表信息
     * @author liuman
     * @param 
     * @return boolean
     * @throws Exception
     */
    @DeleteMapping("/role")
    @Timed
    @ApiOperation(value = "删除角色表信息", notes = "根据UserRoleEntity对象删除角色表信息")
    public boolean deleteRole(@ApiParam @RequestBody @Validated RoleEntity roleEntity) {
        return roleService.deleteRole(roleEntity);
    }
    
    /**
         * 根据id修改角色表信息
    * @author liuman
    * @param 
    * @return boolean
    * @throws Exception
    */
    @PutMapping("/role")
    @Timed
    @ApiOperation(value = "根据id修改角色表信息", notes = "根据UserRoleEntity对象根据id修改角色表信息")
    public boolean updateRoleByid(@ApiParam @RequestBody @Validated RoleEntity roleEntity) {
        return roleService.updateRole(roleEntity);
    }
    
    /**
         * 查看所有
    * @author liuman
    * @param 
    * @return List<RoleEntity>
    * @throws Exception
    */
    @GetMapping("/role/page")
    @Timed
    @ApiOperation(value = "查询角色表", notes = "根据UserRoleEntity对象查询角色表")
    public @ResponseBody List<RoleEntity> selectAllRole(){
        return roleService.selectRoleALL();
    }
    
   /**
         * 根据用户id查询角色
    * @author liuman
    * @param 
    * @return List<RoleEntity>
    * @throws Exception
    */
    @GetMapping("/role/{userId}")
    @Timed
    @ApiOperation(value = "根据用户id查询角色", notes = "根据RoleEntity对象根据用户id查询角色")
    public @ResponseBody RoleEntity selectrRoleByUserId(@PathVariable @ApiParam String userId){
        return roleService.selectRoleByUserId(userId);
    }
}
