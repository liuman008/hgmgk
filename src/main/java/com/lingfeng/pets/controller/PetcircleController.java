/**
 * 
 */
package com.lingfeng.pets.controller;

import java.text.SimpleDateFormat;
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
import com.lingfeng.pets.entity.PetcircleEntity;
import com.lingfeng.pets.entity.ReleaseImageEntity;
import com.lingfeng.pets.entity.UserEntity;
import com.lingfeng.pets.service.PetcircleService;
import com.lingfeng.pets.service.ReleaseImageService;
import com.lingfeng.pets.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author 谷春
 *
 */
@RestController("PetcircleController")
@RequestMapping("/api")
@CrossOrigin
@Api(value = "/petcircleController", description = "宠物圈的接口")
public class PetcircleController {
    
    /**
     * 注入宠物圈表的实现类
     */
    @Autowired
    private PetcircleService petcircleService;
    
    /**
     * 注入用户表的实现类
     */
    @Autowired
    private UserService userService;
    
    /**
     * 注入发布图片表的实现类
     */
    @Autowired
    private ReleaseImageService releaseImageService;
    
    /**
     * 新增宠物圈表信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PostMapping("/petcircle")
    @Timed
    @ApiOperation(value = "新增宠物圈表信息",notes = "新增PetcircleEntity说明:发布宠物圈 type为1 打卡为 2")
    public ServerResponse<Integer> insertPetcircle(@ApiParam @Validated @RequestBody PetcircleEntity petcircleEntity){
        if (!StringUtils.isNotBlank(petcircleEntity.getUserId())||!StringUtils.isNotBlank(petcircleEntity.getAddress())||!StringUtils.isNotBlank(petcircleEntity.getContent())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "发布信息不全！！！");
        }
        SimpleDateFormat sdfDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String releaseId = UUID.randomUUID().toString();
        petcircleEntity.setId(releaseId);
        petcircleEntity.setCreate_date(sdfDateFormat.format(new Date()));
        petcircleEntity.setCommentNum(0);
        petcircleEntity.setPointRatio(0);
        if (StringUtils.isNotBlank(petcircleEntity.getImage())) {
            String [] images = petcircleEntity.getImage().split(",");
            List<ReleaseImageEntity> iList = new ArrayList<ReleaseImageEntity>();
            for (String string : images) {
                ReleaseImageEntity releaseImageEntity = new ReleaseImageEntity();
                releaseImageEntity.setId(UUID.randomUUID().toString());
                releaseImageEntity.setCreate_date(new Date());
                releaseImageEntity.setImage(string);
                releaseImageEntity.setReleaseId(releaseId);
                iList.add(releaseImageEntity);
            }
            releaseImageService.insertBatchReleaseImage(iList);
        }
       
        return ServerResponse.createBySuccess("新增发布信息成功！", petcircleService.insertPetcircle(petcircleEntity));
    }
    
    
    /**
     * 删除宠物圈表信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @DeleteMapping("/petcircle")
    @Timed
    @ApiOperation(value = "删除宠物圈表信息",notes = "删除PetcircleEntity说明：根据id删除数据,还有最后操作人id！")
    public ServerResponse<Integer> deletePetcircle(@ApiParam @Validated @RequestBody PetcircleEntity petcircleEntity){
        if (!StringUtils.isNotBlank(petcircleEntity.getId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "id不能为空！！");
        }else if (!StringUtils.isNotBlank(petcircleEntity.getLastModel_by())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "最后操作人不能为空！");
        }
        petcircleEntity.setLastModel_date(new Date());
        return ServerResponse.createBySuccess("删除发布信息成功！", petcircleService.deletePetcircle(petcircleEntity));
    }
    
    
    /**
     * 修改宠物圈表信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PutMapping("/petcircle")
    @Timed
    @ApiOperation(value = "修改宠物圈表信息",notes = "修改PetcircleEntity说明：根据id修改数据,id不能为空！")
    public ServerResponse<Integer> updatePetcircle(@ApiParam @Validated @RequestBody PetcircleEntity petcircleEntity){
        if (!StringUtils.isNotBlank(petcircleEntity.getId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "id不能为空！！");
        }
        petcircleEntity.setUpdate_date(new Date());
        return ServerResponse.createBySuccess("修改发布信息成功！", petcircleService.updatePetcircle(petcircleEntity));
    }
    
    /**
     * 查询数据
     * @author 谷春
     * @param 
     * @return ServerResponse<ReleasePetEntity>
     * @throws Exception
     */
    @PostMapping("/petcircle/all")
    @Timed
    @ApiOperation(value = "查看所有",notes = "查看所有！")
    public ServerResponse<List<PetcircleEntity >> Petcircle(){
        return ServerResponse.createBySuccess(petcircleService.selectAllPetcircle());
    }
    
    
    /**
     *模糊查询
     * @author 谷春
     * @param 
     * @return ServerResponse<List<PetcircleEntity>>
     * @throws Exception
     */
    @PostMapping("/petcircle/like")
    @Timed
    @ApiOperation(value = "模糊查询",notes = "模糊查询！")
    
    public ServerResponse<PageEntity<PetcircleEntity>> seleectlikePetcircle(@ApiParam @Validated @RequestBody PetcircleEntity petcircleEntity){
        PageEntity pageEntity = new PageEntity();
        //封装总记录数
            int totalCount = petcircleService.selectCount();
            pageEntity.setTotalCount(totalCount);
        if (petcircleEntity.getCurrentPage() != null) {
           //封装当前页数
           pageEntity.setCurrPage(petcircleEntity.getCurrentPage());
           //每页显示的数据
           int pageSize=5;
           pageEntity.setPageSize(pageSize);
           //封装总页数
           double tc = totalCount;
           Double num =Math.ceil(tc/pageSize);//向上取整
           pageEntity.setTotalPage(num.intValue());
           petcircleEntity.setCurrIndex((petcircleEntity.getCurrentPage() -1)*pageSize);
           petcircleEntity.setPageSize(pageSize);
         //封装每页显示的数据
           List<PetcircleEntity> lists = petcircleService.selectlikePetcircle(petcircleEntity);
           pageEntity.setLists(lists);
       }else {
           List<PetcircleEntity> lists = petcircleService.selectlikePetcircle(petcircleEntity);
           pageEntity.setLists(lists);
       }
        return ServerResponse.createBySuccess(pageEntity);
       }
    
    /**
     * 查询热门数据
     * @author 谷春
     * @param 
     * @return ServerResponse<List<PetcircleEntity>>
     * @throws Exception
     */
    @GetMapping("/petcircle/popular/{longitude}/{latitude}/{currentPage}")
    @Timed
    @ApiOperation(value = "查询热门数据",notes = "接口说明：要带参数当前经纬度")
    public ServerResponse<PageEntity<PetcircleEntity>> selectByuserIdPetcircle(@PathVariable double longitude,@PathVariable double latitude,@PathVariable int currentPage){
        if (!StringUtils.isNotBlank(currentPage+"")) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "当前页不能为空！！");
        }
        PageEntity pageEntity = new PageEntity();
        //封装总记录数
        int totalCount = petcircleService.selectCount();
        pageEntity.setTotalCount(totalCount);
        //封装当前页数
        pageEntity.setCurrPage(currentPage);
        //每页显示的数据
        int pageSize=5;
        pageEntity.setPageSize(pageSize);
        //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageEntity.setTotalPage(num.intValue());
      //封装每页显示的数据
        List<PetcircleEntity> resultList  = new ArrayList<PetcircleEntity>();
        PetcircleEntity petcircleEntity = new PetcircleEntity();
        petcircleEntity.setIs_popular(2);
        petcircleEntity.setCurrIndex((currentPage -1)*pageSize);
        petcircleEntity.setPageSize(pageSize);
        List<PetcircleEntity> petcircleEntitys= petcircleService.selectlikePetcircle(petcircleEntity);
        for (PetcircleEntity petcircleEntity2 : petcircleEntitys) {
            //得到用户名和头像
            UserEntity userEntity = userService.selectUserByid(petcircleEntity2.getUserId());
            petcircleEntity2.setUserName(userEntity.getUserName());
            petcircleEntity2.setHeadPortrait(userEntity.getImage());
            //得到距离
            double sum;
            //算出距离 公式（（经度1 -经度2 ）*（经度1 -经度2 ）+（纬度1-纬度2）*（纬度1-纬度2）
            sum=Math.sqrt((longitude-petcircleEntity2.getLatitude())*(longitude-petcircleEntity2.getLatitude())+(latitude-petcircleEntity2.getLongitude())*(latitude-petcircleEntity2.getLongitude()));
            List<ReleaseImageEntity> imagelist = releaseImageService.selectReleaseImageByreleaseId(petcircleEntity2.getId());
            //得到发布图片
            String image = "";
             for (int i = 0; i < imagelist.size(); i++) {
                if (i!=imagelist.size()-1) {
                    image = image+imagelist.get(i).getImage()+",";
                }else {
                    image = image+imagelist.get(i).getImage();
                }
            }
            petcircleEntity2.setImage(image);
            petcircleEntity2.setDistance(sum);
            resultList.add(petcircleEntity2);
        }
        pageEntity.setLists(resultList);
        return ServerResponse.createBySuccess(pageEntity);
    }
    
    /**
     * 查询同城数据
     * @author 谷春
     * @param 
     * @return ServerResponse<List<PetcircleEntity>>
     * @throws Exception
     */
    @GetMapping("/petcircle/city/{address}/{longitude}/{latitude}/{currentPage}")
    @Timed
    @ApiOperation(value = "查询同城数据",notes = "查询同城数据")
    public ServerResponse<PageEntity<PetcircleEntity>> selectBycityPetcircle(@PathVariable String address,@PathVariable double longitude,@PathVariable double latitude,@PathVariable int currentPage){
        if (!StringUtils.isNotBlank(currentPage+"")) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "当前页不能为空！！");
        }else if(!StringUtils.isNotBlank(address)){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "地址不能为空！！");
        }
        System.out.println("longitude="+longitude+"latitude="+latitude);
        PageEntity pageEntity = new PageEntity();
        //封装总记录数
        int totalCount = petcircleService.selectCount();
        pageEntity.setTotalCount(totalCount);
        //封装当前页数
        pageEntity.setCurrPage(currentPage);
        //每页显示的数据
        int pageSize=5;
        pageEntity.setPageSize(pageSize);
        //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageEntity.setTotalPage(num.intValue());
        
        List<PetcircleEntity> resultList  = new ArrayList<PetcircleEntity>();
        PetcircleEntity petcircleEntity = new PetcircleEntity();
        petcircleEntity.setAddress(address);
        petcircleEntity.setCurrIndex((currentPage -1)*pageSize);
        petcircleEntity.setPageSize(pageSize);
        List<PetcircleEntity> petcircleEntitys= petcircleService.selectlikePetcircle(petcircleEntity);
        if (petcircleEntitys == null) return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "没数据！");
        for (PetcircleEntity petcircleEntity2 : petcircleEntitys) {
            double sum;
            //得到用户名和头像
            UserEntity userEntity = userService.selectUserByid(petcircleEntity2.getUserId());
            System.out.println("userEntity="+userEntity.toString());
            System.out.println("userName="+userEntity.getUserName());
            petcircleEntity2.setUserName(userEntity.getUserName());
            petcircleEntity2.setHeadPortrait(userEntity.getImage());
            //算出距离 公式（（经度1 -经度2 ）*（经度1 -经度2 ）+（纬度1-纬度2）*（纬度1-纬度2）
            sum=Math.sqrt((longitude-petcircleEntity2.getLatitude())*(longitude-petcircleEntity2.getLatitude())+(latitude-petcircleEntity2.getLongitude())*(latitude-petcircleEntity2.getLongitude()));
            List<ReleaseImageEntity> imagelist = releaseImageService.selectReleaseImageByreleaseId(petcircleEntity2.getId());
            String image = "";
             for (int i = 0; i < imagelist.size(); i++) {
                if (i!=imagelist.size()-1) {
                    image = image+imagelist.get(i).getImage()+",";
                }else {
                    image = image+imagelist.get(i).getImage();
                }
            }
            System.out.println("dddddd="+image);
            petcircleEntity2.setImage(image);
            petcircleEntity2.setDistance(sum);
            resultList.add(petcircleEntity2);
        }
        pageEntity.setLists(resultList);
        return ServerResponse.createBySuccess(pageEntity);
    }
    
    /**
     * 查询我的数据
     * @author 谷春
     * @param 
     * @return ServerResponse<List<PetcircleEntity>>
     * @throws Exception
     */
    @GetMapping("/petcircle/user/{userId}/{currentPage}")
    @Timed
    @ApiOperation(value = "查询我的数据",notes = "查询我的数据")
    public ServerResponse<PageEntity<PetcircleEntity>> selectByuserPetcircle(@PathVariable String userId,@PathVariable int currentPage){
        if (!StringUtils.isNotBlank(currentPage+"")) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "当前页不能为空！！");
        }
        PageEntity pageEntity = new PageEntity();
        //封装总记录数
        int totalCount = petcircleService.selectCount();
        pageEntity.setTotalCount(totalCount);
        //封装当前页数
        pageEntity.setCurrPage(currentPage);
        //每页显示的数据
        int pageSize=5;
        pageEntity.setPageSize(pageSize);
        //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageEntity.setTotalPage(num.intValue());
        List<PetcircleEntity> resultList  = new ArrayList<PetcircleEntity>();
        PetcircleEntity petcircleEntity = new PetcircleEntity();
        petcircleEntity.setUserId(userId);
        petcircleEntity.setCurrIndex((currentPage -1)*pageSize);
        petcircleEntity.setPageSize(pageSize);
        List<PetcircleEntity> petcircleEntitys= petcircleService.selectlikePetcircle(petcircleEntity);
        for (PetcircleEntity petcircleEntity2 : petcircleEntitys) {
            List<ReleaseImageEntity> imagelist = releaseImageService.selectReleaseImageByreleaseId(petcircleEntity2.getId());
            String image = "";
             for (int i = 0; i < imagelist.size(); i++) {
                if (i!=imagelist.size()-1) {
                    image = image+imagelist.get(i).getImage()+",";
                }else {
                    image = image+imagelist.get(i).getImage();
                }
            }
            petcircleEntity2.setImage(image);
            resultList.add(petcircleEntity2);
        }
        pageEntity.setLists(resultList);
        return ServerResponse.createBySuccess(pageEntity);
    }
}
