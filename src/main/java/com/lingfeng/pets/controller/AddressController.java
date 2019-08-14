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
import com.lingfeng.pets.entity.AddressEntity;
import com.lingfeng.pets.entity.PageEntity;
import com.lingfeng.pets.service.AddressService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author 谷春
 *
 */
@RestController("AdddressController")
@RequestMapping("/api")
@CrossOrigin
@Api(value = "/addressController", description = "地址表的接口")
public class AddressController {
    
    /**
     * 注入地址表的实现类
     */
    @Autowired
    private AddressService addressService;
    
    /**
     * 新增地址信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PostMapping("/address")
    @Timed
    @ApiOperation(value = "新增地址信息",notes = "新增AddressEntity说明：参数必须全部都有！")
    public ServerResponse<Integer> insertAddress(@ApiParam @Validated @RequestBody AddressEntity addressEntity){
        addressEntity.setId(UUID.randomUUID().toString());
        addressEntity.setCreate_date(new Date());
        return ServerResponse.createBySuccess("新增发布信息成功！", addressService.insertAddress(addressEntity));
    }
    /**
     * 删除地址信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @DeleteMapping("/address")
    @Timed
    @ApiOperation(value = "删除地址信息",notes = "删除AddressEntity说明：根据id删除数据,还有最后操作人id！")
    public ServerResponse<Integer> deleteAddress(@ApiParam @Validated @RequestBody AddressEntity addressEntity){
        if (!StringUtils.isNotBlank(addressEntity.getId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "id不能为空！！");
        }else if (!StringUtils.isNotBlank(addressEntity.getLastModel_by())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "最后操作人不能为空！");
        }
        addressEntity.setLastModel_date(new Date());
        return ServerResponse.createBySuccess("删除发布信息成功！", addressService.deleteAddress(addressEntity));
    }
    
    
    /**
     * 删除地址信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @DeleteMapping("/addressbacth")
    @Timed
    @ApiOperation(value = "批量删除地址信息",notes = "删除AddressEntity说明：根据id删除数据,还有最后操作人id！")
    public ServerResponse<Integer> deleteBatchAddress(@ApiParam @Validated @RequestBody AddressEntity addressEntity){
        if (!StringUtils.isNotBlank(addressEntity.getId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "id不能为空！！");
        }else if (!StringUtils.isNotBlank(addressEntity.getLastModel_by())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "最后操作人不能为空！");
        }
        String [] ids = addressEntity.getId().split(",");
        Integer result = 0;
        for (String string : ids) {
            addressEntity.setLastModel_date(new Date());
            addressEntity.setId(string);
            addressService.deleteAddress(addressEntity);
            if (addressService.deleteAddress(addressEntity) >0) result++;
        }
        
        return ServerResponse.createBySuccess("删除地址信息成功！",result);
    }
    
    /**
     * 修改地址信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PutMapping("/address")
    @Timed
    @ApiOperation(value = "修改地址信息",notes = "修改AddressEntity说明：根据id修改数据,id不能为空！")
    public ServerResponse<Integer> updateAddress(@ApiParam @Validated @RequestBody AddressEntity addressEntity){
        if (!StringUtils.isNotBlank(addressEntity.getId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "id不能为空！！");
        }
        addressEntity.setUpdate_date(new Date());
        return ServerResponse.createBySuccess("修改发布信息成功！", addressService.updateAddress(addressEntity));
    }
    
    /**
     * 查询数据
     * @author 谷春
     * @param 
     * @return ServerResponse<ReleasePetEntity>
     * @throws Exception
     */
    @GetMapping("/address/all")
    @Timed
    @ApiOperation(value = "查看所有",notes = "查看所有！")
    public ServerResponse<List<AddressEntity >> selectAllAddress(){
        return ServerResponse.createBySuccess(addressService.selectAllAddress());
    }
    
    
    /**
     * 模糊分页查询
     * @author 谷春
     * @param 
     * @return ServerResponse<ReleasePetEntity>
     * @throws Exception
     */
    @PostMapping("/address/likepage")
    @Timed
    @ApiOperation(value = "模糊分页查询",notes = "模糊分页查询")
    public ServerResponse<PageEntity<AddressEntity >> selectLikeAddress(@ApiParam @Validated @RequestBody AddressEntity addressEntity){
        PageEntity pageEntity = new PageEntity();
        //封装总记录数
         int totalCount = addressService.selectCount();
         pageEntity.setTotalCount(totalCount);
        if (addressEntity.getCurrentPage() != null) {
           //封装当前页数
           pageEntity.setCurrPage(addressEntity.getCurrentPage());
           //每页显示的数据
           int pageSize=5;
           pageEntity.setPageSize(pageSize);
           //封装总页数
           double tc = totalCount;
           Double num =Math.ceil(tc/pageSize);//向上取整
           pageEntity.setTotalPage(num.intValue());
           addressEntity.setCurrIndex((addressEntity.getCurrentPage() -1)*pageSize);
           addressEntity.setPageSize(pageSize);
         //封装每页显示的数据
           List<AddressEntity> lists = addressService.selectlikeAddress(addressEntity);
           pageEntity.setLists(lists);
       }else {
           List<AddressEntity> lists = addressService.selectlikeAddress(addressEntity);
           pageEntity.setLists(lists);
       }
        return ServerResponse.createBySuccess(pageEntity);
    }
}
