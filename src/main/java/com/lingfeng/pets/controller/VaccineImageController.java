/**
 * 
 */
package com.lingfeng.pets.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.lingfeng.pets.config.ResponseCode;
import com.lingfeng.pets.config.ServerResponse;
import com.lingfeng.pets.entity.PageEntity;
import com.lingfeng.pets.entity.VaccineImageEntity;
import com.lingfeng.pets.service.VaccineImageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author 谷春
 *
 */
@RestController("VaccineImageController")
@RequestMapping("/api")
@CrossOrigin
@Api(value = "/vaccineImageController", description = "疫苗证明图片表的接口")
public class VaccineImageController {
    
    /**
     * 注入疫苗证明图片表的实现类
     */
    @Autowired
    private VaccineImageService vaccineService;
    
    /**
     * 新增疫苗证明图片表信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PostMapping("/vaccineImage")
    @Timed
    @ApiOperation(value = "新增疫苗证明图片表信息",notes = "新增VaccineEntity说明：参数必须全部都有！")
    public ServerResponse<Integer> insertVaccine(@ApiParam @Validated @RequestBody VaccineImageEntity vaccineEntity){
        vaccineEntity.setId(UUID.randomUUID().toString());
        vaccineEntity.setCreate_date(new Date());
        return ServerResponse.createBySuccess("新增发布信息成功！", vaccineService.insertVaccine(vaccineEntity));
    }
    
    /**
     * 批量新增
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PostMapping("/vaccine/addBatch")
    @Timed
    @ApiOperation(value = "批量新增疫苗证明图片表信息",notes = "批量新增VaccineEntity说明：要带参数，releaseId,vaccineImage！")
    public ServerResponse<Integer> insertBatchVaccine(@ApiParam @Validated @RequestBody VaccineImageEntity vaccineEntity){
        if (!StringUtils.isNotBlank(vaccineEntity.getReleaseId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "发布id不能为空！");
        }
        vaccineEntity.setCreate_date(new Date());
        String[] Vaccine = vaccineEntity.getVaccineImage().split(",");
        List<VaccineImageEntity> vaccineList = new ArrayList<VaccineImageEntity>();
        for (String string : Vaccine) {
            vaccineEntity.setReleaseId(string);
            vaccineEntity.setId(UUID.randomUUID().toString());
            vaccineList.add(vaccineEntity);
        }
        return ServerResponse.createBySuccess("新增发布信息成功！", vaccineService.insertBatchVaccine(vaccineList));
    }
    /**
     * 删除疫苗证明图片表信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @DeleteMapping("/vaccine")
    @Timed
    @ApiOperation(value = "删除疫苗证明图片表信息",notes = "删除VaccineEntity说明：根据id删除数据,还有最后操作人id！")
    public ServerResponse<Integer> deleteVaccine(@ApiParam @Validated @RequestBody VaccineImageEntity vaccineEntity){
        if (!StringUtils.isNotBlank(vaccineEntity.getId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "id不能为空！！");
        }else if (!StringUtils.isNotBlank(vaccineEntity.getLastModel_by())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "最后操作人不能为空！");
        }
        vaccineEntity.setLastModel_date(new Date());
        return ServerResponse.createBySuccess("删除发布信息成功！", vaccineService.deleteVaccine(vaccineEntity));
    }
    
    
    /**
     * 修改疫苗证明图片表信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PutMapping("/vaccine")
    @Timed
    @ApiOperation(value = "修改疫苗证明图片表信息",notes = "修改VaccineEntity说明：根据id修改数据,id不能为空！")
    public ServerResponse<Integer> updateVaccine(@ApiParam @Validated @RequestBody VaccineImageEntity vaccineEntity){
        if (!StringUtils.isNotBlank(vaccineEntity.getId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "id不能为空！！");
        }
        vaccineEntity.setUpdate_date(new Date());
        return ServerResponse.createBySuccess("修改发布信息成功！", vaccineService.updateVaccine(vaccineEntity));
    }
    
    /**
     * 查询数据
     * @author 谷春
     * @param 
     * @return ServerResponse<ReleasePetEntity>
     * @throws Exception
     */
    @GetMapping("/Vaccine/all")
    @Timed
    @ApiOperation(value = "查看所有",notes = "查看所有！")
    public ServerResponse<List<VaccineImageEntity>> selectAllVaccine(){
        return ServerResponse.createBySuccess(vaccineService.selectAllVaccine());
    }
    
    /**
     * 根据发布id查询数据
     * @author 谷春
     * @param 
     * @return ServerResponse<List<VaccineEntity>>
     * @throws Exception
     */
    @GetMapping("/vaccine/{releaseId}")
    @Timed
    @ApiOperation(value = "根据发布id查询数据",notes = "接口说明：发布id不能为空！")
    public ServerResponse<List<VaccineImageEntity>> selectVaccineByreleaseId(@PathVariable String releaseId){
        if (!StringUtils.isNotBlank(releaseId)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "发布id不能为空！！");
        }
        return ServerResponse.createBySuccess(vaccineService.selectVaccineByreleaseId(releaseId));
    }
    
    /**
     * 模糊分页查询
     * @author 谷春
     * @param 
     * @return ServerResponse<List<VaccineEntity>>
     * @throws Exception
     */
    @PostMapping("/vaccine/likepage")
    @Timed
    @ApiOperation(value = "模糊分页查询",notes = "模糊分页查询")
    public ServerResponse<PageEntity<VaccineImageEntity>> selectLikeVaccine(@ApiParam @Validated @RequestBody VaccineImageEntity vaccineEntity){
        PageEntity pageEntity = new PageEntity();
        //封装总记录数
            int totalCount = vaccineService.selectCount();
            pageEntity.setTotalCount(totalCount);
        if (vaccineEntity.getCurrentPage() != null) {
           //封装当前页数
           pageEntity.setCurrPage(vaccineEntity.getCurrentPage());
           //每页显示的数据
           int pageSize=5;
           pageEntity.setPageSize(pageSize);
           //封装总页数
           double tc = totalCount;
           Double num =Math.ceil(tc/pageSize);//向上取整
           pageEntity.setTotalPage(num.intValue());
           vaccineEntity.setCurrIndex((vaccineEntity.getCurrentPage() -1)*pageSize);
           vaccineEntity.setPageSize(pageSize);
         //封装每页显示的数据
           List<VaccineImageEntity> lists = vaccineService.selectLikeVaccine(vaccineEntity);
           pageEntity.setLists(lists);
       }else {
           List<VaccineImageEntity> lists = vaccineService.selectLikeVaccine(vaccineEntity);
           pageEntity.setLists(lists);
       }
        return ServerResponse.createBySuccess(pageEntity);
    }

}
