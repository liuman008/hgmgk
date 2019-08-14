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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.lingfeng.pets.config.ServerResponse;
import com.lingfeng.pets.entity.PageEntity;
import com.lingfeng.pets.entity.UserRoleEntity;
import com.lingfeng.pets.service.UserRoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author 谷春
 *
 */
@RestController("UserRoleController") 
@RequestMapping("/api")
@CrossOrigin
@Api(value = "[用户角色表]资源操作接口", tags = "userRoleController")

public class UserRoleController {
    
    /**
     * 注入用户角色表的实现类
     */
    @Autowired
    private UserRoleService userRoleService;
    
    
        /**
         * 新增用户角色表信息
    * @author 谷春
    * @param 
    * @return boolean
    * @throws Exception
    */
    @PostMapping("/userRole")
    @Timed
    @ApiOperation(value = "创建用户角色表信息", notes = "根据UserRoleEntity对象新增用户角色表信息")
    public ServerResponse<Boolean> addUserRole(@ApiParam @RequestBody @Validated UserRoleEntity userRoleEntity) {
        return ServerResponse.createBySuccess(userRoleService.addUserRole(userRoleEntity));
    }
    
    /**
         *   删除用户角色表信息
     * @author liuman
     * @param 
     * @return boolean
     * @throws Exception
     */
    @DeleteMapping("/userRole")
    @Timed
    @ApiOperation(value = "删除用户角色表信息", notes = "根据UserRoleEntity对象删除用户角色表信息")
    public ServerResponse<Boolean> deleteUserRole(@ApiParam @RequestBody @Validated UserRoleEntity userRoleEntity) {
        return ServerResponse.createBySuccess(userRoleService.deleteUserRole(userRoleEntity));
    }
    
    /**
         * 根据id修改用户角色表信息
    * @author liuman
    * @param 
    * @return boolean
    * @throws Exception
    */
    @PutMapping("/userRole")
    @Timed
    @ApiOperation(value = "根据id修改用户角色表信息", notes = "根据UserRoleEntity对象根据id修改用户角色表信息")
    public ServerResponse<Boolean> updateUserRoleByid(@ApiParam @RequestBody @Validated UserRoleEntity userRoleEntity) {
        return ServerResponse.createBySuccess(userRoleService.updateUserRole(userRoleEntity));
    }
    
    /**
         * 查看所有
    * @author liuman
    * @param 
    * @return List<BrandEntity>
    * @throws Exception
    */
    @GetMapping("/userRole/page")
    @Timed
    @ApiOperation(value = "查询用户角色表", notes = "根据UserRoleEntity对象查询用户角色表")
    public @ResponseBody ServerResponse<List<UserRoleEntity>> selectAllUserRole(){
        return ServerResponse.createBySuccess(userRoleService.selectUserRoleALL());
    }
    
    /**
     * 模糊分页查询
     * @author 谷春
     * @param 
     * @return ServerResponse<List<UserRoleEntity>>
     * @throws Exception
     */
    @PostMapping("/userRole/likepage")
    public @ResponseBody ServerResponse<PageEntity<UserRoleEntity>> selectlikeUserRole(@ApiParam @RequestBody @Validated UserRoleEntity userRoleEntity){
        PageEntity pageEntity = new PageEntity();
        //封装总记录数
            int totalCount = userRoleService.selectCount();
            pageEntity.setTotalCount(totalCount);
        if (userRoleEntity.getCurrentPage() != null) {
           //封装当前页数
           pageEntity.setCurrPage(userRoleEntity.getCurrentPage());
           //每页显示的数据
           int pageSize=5;
           pageEntity.setPageSize(pageSize);
           //封装总页数
           double tc = totalCount;
           Double num =Math.ceil(tc/pageSize);//向上取整
           pageEntity.setTotalPage(num.intValue());
           userRoleEntity.setCurrIndex((userRoleEntity.getCurrentPage() -1)*pageSize);
           userRoleEntity.setPageSize(pageSize);
         //封装每页显示的数据
           List<UserRoleEntity> lists = userRoleService.selectLikeUserRole(userRoleEntity);
           pageEntity.setLists(lists);
       }else {
           List<UserRoleEntity> lists = userRoleService.selectLikeUserRole(userRoleEntity);
           pageEntity.setLists(lists);
       }
        return ServerResponse.createBySuccess(pageEntity);
    }
}
