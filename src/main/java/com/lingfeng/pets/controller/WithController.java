/**
 * 
 */
package com.lingfeng.pets.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.lingfeng.pets.config.ResponseCode;
import com.lingfeng.pets.config.ServerResponse;
import com.lingfeng.pets.entity.UserEntity;
import com.lingfeng.pets.entity.WithdrawEntity;
import com.lingfeng.pets.service.UserService;
import com.lingfeng.pets.service.WithdrawService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author 谷春
 *
 */
@RestController("WithController")
@RequestMapping("/api")
@CrossOrigin
@Api(value = "/withController", description = "提现表的接口")
public class WithController {
    
    /**
     * 注入提现表的实现类
     */
    @Autowired
    private WithdrawService withdrawService;
    
    /**
     * 注入用户表的实现类
     */
    @Autowired
    private UserService userService;
    
    /**
     * 新增提现信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PostMapping("/withdraw")
    @Timed
    @ApiOperation(value = "新增提现信息",notes = "新增WithdrawEntity说明：参数必须全部都有！")
    public ServerResponse<Boolean> insertWithdraw(@ApiParam @Validated @RequestBody WithdrawEntity withdrawEntity){
        if (!StringUtils.isNotBlank(withdrawEntity.getAccount()+"")) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "金额为空！！");
        }else if (!StringUtils.isNotBlank(withdrawEntity.getUserId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "用户id为空！！");
        }
        //根据用户id查询余额
        UserEntity userEntity = userService.selectUserByid(withdrawEntity.getUserId());
        double balance = userEntity.getBalance();
        if (balance < withdrawEntity.getAccount()) {
            return ServerResponse.createByErrorMessage("提现金额超过余额！！");
        }
        withdrawEntity.setId(UUID.randomUUID().toString());
        withdrawEntity.setCreate_date(new Date());
        double money = withdrawEntity.getAccount()-balance;
        //修改余额
        UserEntity userEntitys = new UserEntity();
        userEntitys.setId(withdrawEntity.getUserId());
        userEntitys.setBalance(money);
        userService.updateUser(userEntitys);
        return ServerResponse.createBySuccess("提现申请成功！！", withdrawService.addWithdraw(withdrawEntity));
    }
    /**
     * 删除提现信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @DeleteMapping("/withdraw")
    @Timed
    @ApiOperation(value = "删除提现信息",notes = "删除WithdrawEntity说明：根据id删除数据,还有最后操作人id！")
    public ServerResponse<Boolean> deleteWithdraw(@ApiParam @Validated @RequestBody WithdrawEntity withdrawEntity){
        if (!StringUtils.isNotBlank(withdrawEntity.getId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "id不能为空！！");
        }else if (!StringUtils.isNotBlank(withdrawEntity.getLastModel_by())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "最后操作人不能为空！");
        }
        withdrawEntity.setLastModel_date(new Date());
        return ServerResponse.createBySuccess("删除发布信息成功！", withdrawService.deleteWithdraw(withdrawEntity));
    }
    
    
    /**
     * 修改提现信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PutMapping("/withdraw")
    @Timed
    @ApiOperation(value = "修改提现信息",notes = "修改WithdrawEntity说明：根据id修改数据,id不能为空！")
    public ServerResponse<Boolean> updateWithdraw(@ApiParam @Validated @RequestBody WithdrawEntity withdrawEntity){
        if (!StringUtils.isNotBlank(withdrawEntity.getId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "id不能为空！！");
        }
        withdrawEntity.setUpdate_date(new Date());
        return ServerResponse.createBySuccess("修改发布信息成功！", withdrawService.updateWithdraw(withdrawEntity));
    }
    
    /**
     * 查询数据
     * @author 谷春
     * @param 
     * @return ServerResponse<ReleasePetEntity>
     * @throws Exception
     */
    @GetMapping("/withdraw/all")
    @Timed
    @ApiOperation(value = "查看所有",notes = "查看所有！")
    public ServerResponse<List<WithdrawEntity >> selectAllWithdraw(){
        return ServerResponse.createBySuccess(withdrawService.selectWithdrawALL());
    }
    
    
   

}
