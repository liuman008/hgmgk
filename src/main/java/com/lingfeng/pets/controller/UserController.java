/**
 * 
 */
package com.lingfeng.pets.controller;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.codahale.metrics.annotation.Timed;
import com.lingfeng.pets.config.BankUtil;
import com.lingfeng.pets.config.OpenIdUtils;
import com.lingfeng.pets.config.ResponseCode;
import com.lingfeng.pets.config.ServerResponse;
import com.lingfeng.pets.entity.PageEntity;
import com.lingfeng.pets.entity.UserEntity;
import com.lingfeng.pets.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author 谷春
 *
 */
@RestController("UserController")
@RequestMapping("/api")
@CrossOrigin
@Api(value = "/userController", description = "用户表的接口")
public class UserController {
    /**
     * 注入用户表的实体类
     */
    @Autowired
    private UserService userService;
    
    /**
     * 新增数据
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    @PostMapping("/user")
    @Timed
    @ApiOperation(value = "新增数据",notes = "根据UserEntity对象新增数据")
    public ServerResponse<Integer> insertUser(@ApiParam @RequestBody @Validated UserEntity userEntity) {
        if (!StringUtils.isNotBlank(userEntity.getUserName())) {
            return ServerResponse.createByErrorMessage("用户名为空！");
        }else if (!StringUtils.isNotBlank(userEntity.getPhone())) {
            return ServerResponse.createByErrorMessage("联系电话为空！");
        }else if(!StringUtils.isNotBlank(userEntity.getCounterphotoofIDcard())) {
            return ServerResponse.createByErrorMessage("身份证正照为空！");
        }else if (!StringUtils.isNotBlank(userEntity.getFrontphotoofIDcard())) {
            return ServerResponse.createByErrorMessage("身份证反照为空！");
        }else if (!StringUtils.isNotBlank(userEntity.getCardNumber())) {
            return ServerResponse.createByErrorMessage("银行卡号为空！！");
        }
        char bit = BankUtil.getBankCardCheckCode(userEntity.getCardNumber()
                .substring(0, userEntity.getCardNumber().length() - 1));
       boolean res = userEntity.getCardNumber().charAt(userEntity.getCardNumber().length() - 1) == bit;
        if (!res)  ServerResponse.createByErrorMessage("银行卡号有误！！");
       return ServerResponse.createBySuccess("新增成功！", userService.insertUser(userEntity));
    }
    /**
     * 微信授权
     * @author 谷春
     * @param 
     * @return ServerResponse<UserEntity>
     * @throws Exception
     */
    @PostMapping("/authorizationauth" )
    @Timed
    @ApiOperation(value = "微信授权",notes = "微信授权")
    public ServerResponse<UserEntity> authorization(@ApiParam @RequestBody @Validated Map<String, Object> map) {
        System.out.println("likeName="+map.get("likeName")+"code="+map.get("code"));
        if (!StringUtils.isNotBlank(map.get("code").toString())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "code为空！");
        }
        JSONObject jsonObject = OpenIdUtils.getUserWXLoginInfo(map.get("code").toString(),"wx3b7e6ad21ed259ed", "db0b94b36f2a521b7efc8d85816e817d");
        if(jsonObject==null&&!jsonObject.containsKey("openid")) {
            return ServerResponse.createByErrorMessage("生成openid有误！");
        }
        System.out.println("dsffdsfgt ="+jsonObject.toString());
        String openid = (String)jsonObject.get("openid");
        if(openid.equals("null")) { 
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "openid为空！");
        }
        UserEntity us= userService.selectUserByopenId(openid);
        UserEntity userEntity = new UserEntity();
        if(us == null){
            userEntity.setLikeName(map.get("likeName").toString());
            userEntity.setImage(map.get("image").toString());
            userEntity.setOpenid(openid);
            userService.insertUser(userEntity);
            return ServerResponse.createBySuccess("授权成功！", userEntity);
        }
        return ServerResponse.createBySuccess("授权成功！", us);
    }
    
    /**
     * 删除数据
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @DeleteMapping("/user")
    @Timed
    @ApiOperation(value = "删除数据",notes = "删除数据")
    public ServerResponse<Integer> deleteUser(@ApiParam @Validated @RequestBody UserEntity userEntity) {
        if (!StringUtils.isNotBlank(userEntity.getLastModel_by())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(),"最后操作人为空！");
        }
        Integer resInteger = userService.deleteUser(userEntity);
        return ServerResponse.createBySuccess("删除成功！", resInteger);
    }

    /**
     * 修改数据
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PutMapping("/user")
    @Timed
    @ApiOperation(value = "修改数据",notes = "修改数据")
    public ServerResponse<Integer> updateUser(@ApiParam @Validated @RequestBody UserEntity userEntity) {
        if (!StringUtils.isNotBlank(userEntity.getUserName())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(),"姓名为空！");
        }else if (!StringUtils.isNotBlank(userEntity.getPhone())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(),"电话号为空！");
        }else if (!StringUtils.isNotBlank(userEntity.getCounterphotoofIDcard())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(),"正面身份照为空！");
        }else if (!StringUtils.isNotBlank(userEntity.getFrontphotoofIDcard())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(),"烦面身份照为空！");
        }else if(!StringUtils.isNotBlank(userEntity.getSex())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(),"性别为空！");
        }else if (!StringUtils.isNotBlank(userEntity.getCardNumber())) {
            return ServerResponse.createByErrorMessage("银行卡号为空！！");
        }
        char bit = BankUtil.getBankCardCheckCode(userEntity.getCardNumber()
                .substring(0, userEntity.getCardNumber().length() - 1));
       boolean resb = userEntity.getCardNumber().charAt(userEntity.getCardNumber().length() - 1) == bit;
        if (!resb)  ServerResponse.createByErrorMessage("银行卡号有误！！");
        Integer res = userService.updateUser(userEntity);
        if (res>0) {
            return ServerResponse.createBySuccess("修改成功!", res);
        }
        return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "修改失败！");
    }
    
    /**
     * 查询所有
     * @author 谷春
     * @param 
     * @return ServerResponse<UserEntity>
     * @throws Exception
     */
   @GetMapping("/usepage")
    @Timed
    @ApiOperation(value = "查询数据",notes = "根据UserEntity对象查询数据")
    public ServerResponse<List<UserEntity>> selectAll() {
        List<UserEntity> list = userService.selectAll();
        return ServerResponse.createBySuccess(list);
    }
   
   /**
    * 模糊分页查询
    * @author 谷春
    * @param 
    * @return ServerResponse<List<UserEntity>>
    * @throws Exception
    */
   @PostMapping("/usepage/likepage")
   @Timed
   @ApiOperation(value = "模糊分页查询",notes = "模糊分页查询")
   public ServerResponse<PageEntity<UserEntity>> selectLikePage(@ApiParam @Validated @RequestBody UserEntity userEntity) {
    PageEntity pageEntity = new PageEntity();
    //封装总记录数
        int totalCount = userService.selectCount();
        pageEntity.setTotalCount(totalCount);
    if (userEntity.getCurrentPage() != null) {
       //封装当前页数
       pageEntity.setCurrPage(userEntity.getCurrentPage());
       //每页显示的数据
       int pageSize=5;
       pageEntity.setPageSize(pageSize);
       //封装总页数
       double tc = totalCount;
       Double num =Math.ceil(tc/pageSize);//向上取整
       pageEntity.setTotalPage(num.intValue());
       userEntity.setCurrIndex((userEntity.getCurrentPage() -1)*pageSize);
       userEntity.setPageSize(pageSize);
     //封装每页显示的数据
       List<UserEntity> lists = userService.selectLikeUser(userEntity);
       pageEntity.setLists(lists);
   }else {
       List<UserEntity> lists = userService.selectLikeUser(userEntity);
       pageEntity.setLists(lists);
   }
    return ServerResponse.createBySuccess(pageEntity);
   }
    /**
     * 根据id查询数据-
     * @author 谷春
     * @param 
     * @return ServerResponse<UserEntity>
     * @throws Exception
     */
    @GetMapping("/user/{id}")
    @Timed
    @ApiOperation(value = "根据id查询数据",notes = "接口说明：id不能为空")
    public ServerResponse<UserEntity> selectUserByid(@ApiParam @RequestBody @PathVariable String id) {
        if (!StringUtils.isNotBlank(id)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "id为空！！");
        }
        return ServerResponse.createBySuccess(userService.selectUserByid(id));
    }
}
