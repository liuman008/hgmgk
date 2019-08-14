/**
 * 
 */
package com.lingfeng.pets.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
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
import com.lingfeng.pets.config.ResponseCode;
import com.lingfeng.pets.config.ServerResponse;
import com.lingfeng.pets.entity.BackstageUserEntity;
import com.lingfeng.pets.entity.PageEntity;
import com.lingfeng.pets.entity.UserRoleEntity;
import com.lingfeng.pets.exception.BaseServiceException;
import com.lingfeng.pets.exception.ServiceErrCode;
import com.lingfeng.pets.login.NoneAuth;
import com.lingfeng.pets.login.RedisTokenHelp;
import com.lingfeng.pets.service.BackstageUserService;
import com.lingfeng.pets.service.RoleService;
import com.lingfeng.pets.service.UserRoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author 谷春
 *
 */
 @RestController("BackstageUserController")
 @RequestMapping("/api")
 @CrossOrigin
 @Api(value = "[后台用户]资源操作接口", tags = "backstageUserController")
public class BackstageUserController {
         /**
                              *  注入后台用户表的实现类
          */
         @Autowired
         private BackstageUserService backstageUserService;
         
        
         /**
                              * 注入角色表
          */
         @Autowired
         private RoleService roleService;
         
         /**
                          * 注入用户角色表
          */
         @Autowired
         private UserRoleService userRoleService;
         
         @Autowired
         private RedisTokenHelp redisTokenHelp;
         
         
         private static final String ROLENAME = "超级管理员";
     
         /**
                            * 新增后台用户表信息
         * @author 谷春
         * @param 
         * @return boolean
         * @throws Exception
         */
         @PostMapping("/backstageUser")
         @Timed
         @ApiOperation(value = "创建后台用户表信息", notes = "根据BackstageUserEntity对象新增后台用户表信息")
         public boolean addBackstageUser(@ApiParam @RequestBody @Validated BackstageUserEntity backstageUserEntity) {
             backstageUserEntity.setId(UUID.randomUUID().toString());
             return backstageUserService.addBackstageUser(backstageUserEntity);
         }
         
         

         /**
                            * 新增后台用户表信息
         * @author 谷春
         * @param 
         * @return boolean
         * @throws Exception
         */
         @PostMapping("/backstageUserpage")
         @Timed
         @ApiOperation(value = "页面新增后台用户的接口", notes = "根据BackstageUserEntity对象新增后台用户表信息")
         public ServerResponse<Integer> addBackstageUserBypage(@ApiParam @RequestBody @Validated BackstageUserEntity backstageUserEntity) {
             Integer result = 0;
             System.out.println("USERID="+backstageUserEntity.getUserId());
             if (!StringUtils.isNotBlank(backstageUserEntity.getUserId())) {
                 return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "userId为空!");
             }else if (!StringUtils.isNotBlank(backstageUserEntity.getUserName())) {
                 return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "用户名为空!");
                 
             }else if(!StringUtils.isNotBlank(backstageUserEntity.getPassword())) {
                 return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "密码为空!");
             }else if (!StringUtils.equals(ROLENAME, roleService.selectRoleByUserId(backstageUserEntity.getUserId()).getRoleName())) {
                 return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "您没有权限进行新增操作");
             }
             BackstageUserEntity backstage = backstageUserService.selectBackstageUserByuserName(backstageUserEntity.getUserName());
             if (backstage == null) {
                 String userIds = UUID.randomUUID().toString();
                 backstageUserEntity.setId(userIds);
                 boolean res = backstageUserService.addBackstageUser(backstageUserEntity);
                 if (res) {
                     result++; 
                     UserRoleEntity userRoleEntity = new UserRoleEntity();
                     userRoleEntity.setUserId(userIds);
                     userRoleEntity.setRoleId("afee40d9-8b16-4bee-8ecc-1559afc70a0f");
                     boolean ress = userRoleService.addUserRole(userRoleEntity);
                     if (ress) result++; 
                 }
                
             }else {
                 throw new BaseServiceException("该用户名以存在，请重新输入!", ServiceErrCode.REQ_PARAM_ERR);
             }
             return ServerResponse.createBySuccess(result);
         }
         
         /**
          *   删除后台用户表信息
          * @author liuman
          * @param 
          * @return boolean
          * @throws Exception
          */
         @DeleteMapping("/backstageUser")
         @Timed
         @ApiOperation(value = "删除后台用户表信息", notes = "根据BackstageUserEntity对象删除后台用户表信息")
         public boolean deleteBackstageUser(@ApiParam @RequestBody @Validated BackstageUserEntity backstageUserEntity) {
             return backstageUserService.deleteBackstageUser(backstageUserEntity);
         }
         
         /**
          * 页面删除用户信息
          * @author 谷春
          * @param 
          * @return boolean
          * @throws Exception
          */
         @DeleteMapping("/backstageUserPage")
         @Timed
         @ApiOperation(value = "页面删除用户信息", notes = "根据BackstageUserEntity对象页面删除用户信息")
         public ServerResponse<Object> deleteBackstageUserPage(@ApiParam @RequestBody @Validated BackstageUserEntity backstageUserEntity) {
             if (!StringUtils.isNotBlank(backstageUserEntity.getUserId())) {
                 return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "请求参数为空！!");
             }else if (!StringUtils.equals(ROLENAME, roleService.selectRoleByUserId(backstageUserEntity.getUserId()).getRoleName())) {
                 return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "您没有权限进行删除操作");
             }
             backstageUserEntity.setLastModel_by(backstageUserEntity.getLastModel_by());
             return ServerResponse.createBySuccess(backstageUserService.deleteBackstageUser(backstageUserEntity));
         }
         /**
         * 根据id修改后台用户表信息
         * @author liuman
         * @param 
         * @return boolean
         * @throws Exception
         */
         @PutMapping("/backstageUser")
         @Timed
         @ApiOperation(value = "根据id删除后台用户表告信息", notes = "根据BackstageUserEntity对象根据id删除后台用户表信息")
         public boolean updateBackstageUserByid(@ApiParam @RequestBody @Validated BackstageUserEntity backstageUserEntity) {
             return backstageUserService.updateBackstageUser(backstageUserEntity);
         }
         
         /**
          * 根据id修改后台用户表信息
          * @author liuman
          * @param 
          * @return boolean
          * @throws Exception
          */
          @PutMapping("/backstageUserpage")
          @Timed
          @ApiOperation(value = "页面根据id修改后台用户表告信息", notes = "页面根据BackstageUserEntity对象根据id修改后台用户表信息")
          public ServerResponse<Object> updateBackstageUserByidtwo(@ApiParam @RequestBody @Validated BackstageUserEntity backstageUserEntity) {
              if (!StringUtils.isNotBlank(backstageUserEntity.getUserId())) {
                  return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "请求参数为空！!");
              }else if (!StringUtils.equals(ROLENAME, roleService.selectRoleByUserId(backstageUserEntity.getUserId()).getRoleName())) {
                  return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "您没有权限进行修改操作");
              }
              return ServerResponse.createBySuccess(backstageUserService.updateBackstageUser(backstageUserEntity));
          }
         /**
                           * 查看所有
         * @author liuman
         * @param 
         * @return List<BackstageUserEntity>
         * @throws Exception
         */
          @GetMapping("/backstageUser/page")
         @Timed
         @ApiOperation(value = "创建后台用户表", notes = "根据BackstageUserEntity对象新增后台用户表")
         public @ResponseBody ServerResponse<List<BackstageUserEntity>> selectAllBackstageUser(){
             return ServerResponse.createBySuccess(backstageUserService.selectBackstageUserALL());
         }
         
         /**
                              * 分页查询
          * @author liuman
          * @param 
          * @return List<BrandEntity>
          * @throws Exception
          */
         public ServerResponse<List<BackstageUserEntity>> queryBackstageUsersBySql(@PathVariable int currPage,@PathVariable int pageSize,@PathVariable String userId) {
             List<BackstageUserEntity> alllist = new ArrayList<BackstageUserEntity>();
             List<BackstageUserEntity> list = backstageUserService.queryBackstageUserBySql(currPage, pageSize);
             for (BackstageUserEntity backstageUserEntity : list) {
                if (!StringUtils.equals(userId, backstageUserEntity.getId())) {
                    alllist.add(backstageUserEntity);
                }
             }
             return ServerResponse.createBySuccess(alllist);
         }
         
         /**
                              * 模糊分页查询
          * @author 谷春
          * @param 
          * @return List<BackstageUserEntity>
          * @throws Exception
          */
         @PostMapping("/backstageUser/likepage")
         @Timed
         @ApiOperation(value = "模糊分页查询", notes = "根据BackstageUserEntity对象模糊分页查询")
         public ServerResponse<PageEntity<BackstageUserEntity>> likeBackstageUser(@ApiParam @RequestBody @Validated  BackstageUserEntity backstageUserEntity) {
             PageEntity pageEntity = new PageEntity();
             //封装总记录数
                 int totalCount = backstageUserService.selectCount();
                 pageEntity.setTotalCount(totalCount);
             if (backstageUserEntity.getCurrentPage() != null) {
                //封装当前页数
                pageEntity.setCurrPage(backstageUserEntity.getCurrentPage());
                //每页显示的数据
                int pageSize=5;
                pageEntity.setPageSize(pageSize);
                //封装总页数
                double tc = totalCount;
                Double num =Math.ceil(tc/pageSize);//向上取整
                pageEntity.setTotalPage(num.intValue());
                backstageUserEntity.setCurrIndex((backstageUserEntity.getCurrentPage() -1)*pageSize);
                backstageUserEntity.setPageSize(pageSize);
              //封装每页显示的数据
                List<BackstageUserEntity> lists = backstageUserService.likeBackstageUser(backstageUserEntity);
                pageEntity.setLists(lists);
            }else {
                List<BackstageUserEntity> lists = backstageUserService.likeBackstageUser(backstageUserEntity);
                pageEntity.setLists(lists);
            }
             return ServerResponse.createBySuccess(pageEntity);
         }
         
         /**
                              * 登录
          * @author 谷春
          * @param 
          * @return BackstageUserEntity
          * @throws Exception
          */
         @NoneAuth
         @GetMapping("/backstageUserlogin/{userName}/{userPass}")
         @Timed
         @ApiOperation(value = "登录",notes = "登录")
         public Map<String, Object> login(@PathVariable String userName, @PathVariable String userPass) throws Exception {
            Map<String, Object> map = new HashMap<>();
            BackstageUserEntity backstageUserEntity =  backstageUserService.login(userName, userPass);
            if (!StringUtils.isNotBlank(userPass)||!StringUtils.isNotBlank(userName)) {
                 throw new BaseServiceException("参数不完整！!", ServiceErrCode.REQ_PARAM_ERR);
            }else if (StringUtils.isNotBlank(backstageUserEntity.toString())) {
                BackstageUserEntity backstageUserEntitys = backstageUserService.login(userName, userPass);
                if (backstageUserEntitys == null ) {
                    
                }
                String userId = backstageUserEntitys.getId();
                String token = redisTokenHelp.create(userName, userPass, userId);
                if (token != null){
                    map.put("code", "10000");
                    map.put("message","认证成功");
                    map.put("token", token);
                    map.put("date", backstageUserService.login(userName, userPass));
                    return map;
                }
           }else if (!StringUtils.isNotBlank(backstageUserEntity.toString())) {
               map.put("code", "00000");
               map.put("message","密码或账户有误！");
           }
            return map;
         }
         
         /**
                              * 根据用户名查询数据
          * @author 谷春
          * @param 
          * @return BackstageUserEntity
          * @throws Exception
          */ 
         @GetMapping("/backstageUserbyname/{userName}")
         @Timed
         @ApiOperation(value = "根据用户名查询数据",notes = "根据用户名查询数据")
         public ServerResponse<BackstageUserEntity> selectBackstageUserByuserName(@PathVariable String userName) {
            if (!StringUtils.isNotBlank(userName)) {
                 return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "参数为空！");
            }
             return ServerResponse.createBySuccess(backstageUserService.selectBackstageUserByuserName(userName));
         }
}
