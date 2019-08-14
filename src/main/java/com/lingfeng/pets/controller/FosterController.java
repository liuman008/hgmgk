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
import com.lingfeng.pets.entity.FosterEntity;
import com.lingfeng.pets.entity.PageEntity;
import com.lingfeng.pets.service.FosterService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author 谷春
 *
 */
@RestController("FosterController")
@RequestMapping("/api")
@CrossOrigin
@Api(value = "/fosterController", description = "寄养的接口")
@ApiImplicitParams({
        @ApiImplicitParam(value = "0",name = "执行成功！"),
        @ApiImplicitParam(value = "1",name = "执行失败")
})
public class FosterController {
    
    /**
     * 注入寄养的是实现类
     */
    @Autowired
    private FosterService fosterService;
    
    /**
     * 新增寄养信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PostMapping("/foster")
    @Timed
    @ApiOperation(value = "新增寄养信息",notes = "新增FosterEntity说明：参数必须全部都有！")
    public ServerResponse<Integer> insertFoster(@ApiParam @Validated @RequestBody FosterEntity fosterEntity){
        if (!StringUtils.isNotBlank(fosterEntity.getCardingConditions())||!StringUtils.isNotBlank(fosterEntity.getConditionsofadoption())||!StringUtils.isNotBlank(fosterEntity.getRemarks())
                ||!StringUtils.isNotBlank(fosterEntity.getServiceCharge())||!StringUtils.isNotBlank(fosterEntity.getStory())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "发布信息参数不完整！");
        }
        fosterEntity.setId(UUID.randomUUID().toString());
        fosterEntity.setCreate_date(new Date());
        return ServerResponse.createBySuccess("新增发布信息成功！", fosterService.insertFoster(fosterEntity));
    }
    /**
     * 删除寄养信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @DeleteMapping("/foster")
    @Timed
    @ApiOperation(value = "删除寄养信息",notes = "删除FosterEntity说明：根据id删除数据,还有最后操作人id！")
    public ServerResponse<Integer> deleteFoster(@ApiParam @Validated @RequestBody FosterEntity fosterEntity){
        if (!StringUtils.isNotBlank(fosterEntity.getId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "id不能为空！！");
        }else if (!StringUtils.isNotBlank(fosterEntity.getLastModel_by())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "最后操作人不能为空！");
        }
        fosterEntity.setLastModel_date(new Date());
        return ServerResponse.createBySuccess("删除发布信息成功！", fosterService.deleteFoster(fosterEntity));
    }
    
    
    /**
     * 修改寄养信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PutMapping("/foster")
    @Timed
    @ApiOperation(value = "修改寄养信息",notes = "修改FosterEntity说明：根据id修改数据,id不能为空！")
    public ServerResponse<Integer> updateFoster(@ApiParam @Validated @RequestBody FosterEntity fosterEntity){
        if (!StringUtils.isNotBlank(fosterEntity.getId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "id不能为空！！");
        }
        fosterEntity.setUpdate_date(new Date());
        return ServerResponse.createBySuccess("修改发布信息成功！", fosterService.updateFoster(fosterEntity));
    }
    
    /**
     * 查询数据
     * @author 谷春
     * @param 
     * @return ServerResponse<ReleasePetEntity>
     * @throws Exception
     */
    @GetMapping("/foster/all")
    @Timed
    @ApiOperation(value = "查看所有",notes = "查看所有！")
    public ServerResponse<List<FosterEntity >> selectAllFoster(){
        return ServerResponse.createBySuccess(fosterService.selectAllFoster());
    }
    
    
    /**
     * 模糊分页查询
     * @author 谷春
     * @param 
     * @return ServerResponse<ReleasePetEntity>
     * @throws Exception
     */
    @PostMapping("/foster/likepage")
    @Timed
    @ApiOperation(value = "模糊分页查询",notes = "模糊分页查询")
    public ServerResponse<PageEntity<FosterEntity >> selectLikeFoster(@ApiParam @Validated @RequestBody FosterEntity fosterEntity){
        PageEntity pageEntity = new PageEntity();
        //封装总记录数
            int totalCount = fosterService.selectCount();
            pageEntity.setTotalCount(totalCount);
        if (fosterEntity.getCurrentPage() != null) {
           //封装当前页数
           pageEntity.setCurrPage(fosterEntity.getCurrentPage());
           //每页显示的数据
           int pageSize=5;
           pageEntity.setPageSize(pageSize);
           //封装总页数
           double tc = totalCount;
           Double num =Math.ceil(tc/pageSize);//向上取整
           pageEntity.setTotalPage(num.intValue());
           fosterEntity.setCurrIndex((fosterEntity.getCurrentPage() -1)*pageSize);
           fosterEntity.setPageSize(pageSize);
         //封装每页显示的数据
           List<FosterEntity> lists = fosterService.selectLikeFoster(fosterEntity);
           pageEntity.setLists(lists);
       }else {
           List<FosterEntity> lists = fosterService.selectLikeFoster(fosterEntity);
           pageEntity.setLists(lists);
       }
        return ServerResponse.createBySuccess(pageEntity);
        
    }
}
