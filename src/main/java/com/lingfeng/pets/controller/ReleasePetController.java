/**
 * 
 */
package com.lingfeng.pets.controller;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.lingfeng.pets.config.ResponseCode;
import com.lingfeng.pets.config.ServerResponse;
import com.lingfeng.pets.entity.AssistEntity;
import com.lingfeng.pets.entity.FosterEntity;
import com.lingfeng.pets.entity.PageEntity;
import com.lingfeng.pets.entity.ReleaseAllEntity;
import com.lingfeng.pets.entity.ReleaseImageEntity;
import com.lingfeng.pets.entity.ReleasePetEntity;
import com.lingfeng.pets.entity.UserEntity;
import com.lingfeng.pets.entity.VaccineImageEntity;
import com.lingfeng.pets.entity.WalkDogEntity;
import com.lingfeng.pets.service.AssistService;
import com.lingfeng.pets.service.FosterService;
import com.lingfeng.pets.service.ReleaseImageService;
import com.lingfeng.pets.service.ReleasePetService;
import com.lingfeng.pets.service.UserService;
import com.lingfeng.pets.service.VaccineImageService;
import com.lingfeng.pets.service.WalkDogService;
import com.lingfeng.pets.service.imp.ChargeServiceImpl;
import com.lingfeng.pets.service.imp.WalkDogChargeServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author 谷春
 *
 */
@RestController("ReleasePetController")
@RequestMapping("/api")
@CrossOrigin
@Api(value = "/releasePetController", description = "发布表的接口")
@ApiImplicitParams({
        @ApiImplicitParam(value = "0",name = "执行成功！"),
        @ApiImplicitParam(value = "1",name = "执行失败")
})
public class ReleasePetController {
    
    /**
     * 注入发布表的是实现类
     */
    @Autowired
    private ReleasePetService releasePetService;
    
    /**
     * 注入寄养表的实线类
     */
    @Autowired
    private FosterService fosterService;
    
    /**
     * 注入用户表的实现类
     */
    @Autowired
    private UserService userService;
    
    /**
     * 注入遛狗的实现类
     */
    @Autowired
    private WalkDogService walkDogService;
    
    /**
     * 注入疫苗证明表的实现类
     */
    @Autowired
    private VaccineImageService vaccineService; 
    
    /**
     * 注入发布图片表的实现类
     */
    @Autowired
    private ReleaseImageService releaseImageService;
    
    /**
     * 注入助养表的是实现类
     */
    @Autowired
    private AssistService assistService;
    
    @Autowired
    private ChargeServiceImpl chargeServiceImpl;
    
    @Autowired
    private WalkDogChargeServiceImpl walkDogChargeServiceImpl;
    
    /**
     * 新增发布表信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PostMapping("/releasePet")
    @Timed
    @ApiOperation(value = "新增发布表信息",notes = "新增releasePet说明：参数必须全部都有！")
    public ServerResponse<Integer> insertRelease(@ApiParam @Validated @RequestBody ReleasePetEntity releasePetEntity){
        if (!StringUtils.isNotBlank(releasePetEntity.getAllergy())||!StringUtils.isNotBlank(releasePetEntity.getEstrus())||!StringUtils.isNotBlank(releasePetEntity.getMaleandfemale())||
                !StringUtils.isNotBlank(releasePetEntity.getModels())||!StringUtils.isNotBlank(releasePetEntity.getName())||!StringUtils.isNotBlank(releasePetEntity.getVarieties())||
                !StringUtils.isNotBlank(releasePetEntity.getWeight())||!StringUtils.isNotBlank(releasePetEntity.getAge()+"")) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "发布信息参数不完整！");
        }
        releasePetEntity.setId(UUID.randomUUID().toString());
        releasePetEntity.setCreate_date(new Date());
        return ServerResponse.createBySuccess("新增发布信息成功！", releasePetService.insertReleasePet(releasePetEntity));
    }
    /**
     * 删除发布表信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @DeleteMapping("/releasePet")
    @Timed
    @ApiOperation(value = "删除发布表信息",notes = "删除releasePet说明：根据id删除数据,还有最后操作人id！")
    public ServerResponse<Integer> deleteRelease(@ApiParam @Validated @RequestBody ReleasePetEntity releasePetEntity){
        if (!StringUtils.isNotBlank(releasePetEntity.getId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "id不能为空！！");
        }else if (!StringUtils.isNotBlank(releasePetEntity.getLastModel_by())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "最后操作人不能为空！");
        }
        releasePetEntity.setLastModel_date(new Date());
        return ServerResponse.createBySuccess("删除发布信息成功！", releasePetService.deleteReleasePet(releasePetEntity));
    }
    
    
    /**
     * 修改发布表信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PutMapping("/releasePet")
    @Timed
    @ApiOperation(value = "修改发布表信息",notes = "修改releasePet说明：根据id修改数据,id不能为空！")
    public ServerResponse<Integer> updateRelease(@ApiParam @Validated @RequestBody ReleasePetEntity releasePetEntity){
        if (!StringUtils.isNotBlank(releasePetEntity.getId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "id不能为空！！");
        }
        releasePetEntity.setUpdate_date(new Date());
        return ServerResponse.createBySuccess("修改发布信息成功！", releasePetService.updateReleasePet(releasePetEntity));
    }
    
    /**
     * 查询数据
     * @author 谷春
     * @param 
     * @return ServerResponse<ReleasePetEntity>
     * @throws Exception
     */
    @GetMapping("/releasePet/all")
    @Timed
    @ApiOperation(value = "查看所有",notes = "查看所有！")
    public ServerResponse<List<ReleasePetEntity>> selectAllReleasePet(){
        return ServerResponse.createBySuccess(releasePetService.selectAllReleasePet());
    }
    
    /**
     * 页面新增发布信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PostMapping("/releasePet/addByPage")
    @Timed
    @ApiOperation(value = "页面新增发布信息",notes = "新增发布信息说明 ：新增寄养信息 type为1 新增遛狗信息 type为 2 ,userId为当前用户id"
            + "疫苗图片路径和发布图片路径String的格式用‘，’拼接")
    public ServerResponse<String> addReleasePage(@ApiParam @Validated @RequestBody ReleaseAllEntity releaseAllEntity){
        System.out.println("type="+releaseAllEntity.getType());
        if (!StringUtils.isNotBlank(releaseAllEntity.getUserId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "当前用户id为空！");
        }
        //调用用户表根据id查询数据的方法（判断用户是否注册）
        UserEntity userEntity = userService.selectUserByid(releaseAllEntity.getUserId());
        if (userEntity == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_REGISTER.getCode(), "用户表没查询到数据，请检查userId！");
        }
        if (!StringUtils.isNotBlank(userEntity.getPhone())&&!StringUtils.isNotBlank(userEntity.getUserName())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_REGISTER.getCode(), "请先进行注册！");
        }
        //判断基本发布信息是否填写完整
        if (!StringUtils.isNotBlank(releaseAllEntity.getAllergy())||!StringUtils.isNotBlank(releaseAllEntity.getEstrus())||!StringUtils.isNotBlank(releaseAllEntity.getMaleandfemale())||
                !StringUtils.isNotBlank(releaseAllEntity.getModels())||!StringUtils.isNotBlank(releaseAllEntity.getName())||!StringUtils.isNotBlank(releaseAllEntity.getAge()+"")
                ||!StringUtils.isNotBlank(releaseAllEntity.getVarieties())||!StringUtils.isNotBlank(releaseAllEntity.getWeight())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "基本发布信息参数不完整！");
        }
        ReleasePetEntity releasePetEntity = new ReleasePetEntity();
        //新增发布表信息
        String releaseId = UUID.randomUUID().toString();
        releasePetEntity.setId(releaseId);
        releasePetEntity.setCreate_date(new Date());
        releasePetEntity.setAge(releaseAllEntity.getAge());
        releasePetEntity.setAllergy(releaseAllEntity.getAllergy());
        releasePetEntity.setEstrus(releaseAllEntity.getEstrus());
        releasePetEntity.setIs_insectRepellent(releaseAllEntity.getIs_insectRepellent());
        releasePetEntity.setIs_vaccine(releaseAllEntity.getIs_vaccine());
        releasePetEntity.setMaleandfemale(releaseAllEntity.getMaleandfemale());
        releasePetEntity.setModels(releaseAllEntity.getModels());
        releasePetEntity.setName(releaseAllEntity.getName());
        releasePetEntity.setVarieties(releaseAllEntity.getVarieties());
        releasePetEntity.setWeight(releaseAllEntity.getWeight());
        releasePetEntity.setLatitude(releaseAllEntity.getLatitude());
        releasePetEntity.setLongitude(releaseAllEntity.getLongitude());
        releasePetEntity.setType(releaseAllEntity.getType());
        releasePetEntity.setUserId(releaseAllEntity.getUserId());
        releasePetEntity.setAddress(releaseAllEntity.getAddress());
        releasePetEntity.setImage(releaseAllEntity.getReleaseImage());
        releasePetEntity.setContent(releaseAllEntity.getContent());
        releasePetEntity.setState(1);
        
        //判断是新增发布信息 1 寄养  2 遛狗
        switch (releaseAllEntity.getType()+"") {
        case "1":
            double money = chargeServiceImpl.selectCharge(releaseAllEntity.getDays(), releaseAllEntity.getWeight().toString());
            releasePetEntity.setServiceCharge(money);
            releasePetService.insertReleasePet(releasePetEntity);
            Integer res = 0;
            if (!StringUtils.isNotBlank(releaseAllEntity.getCardingConditions())||!StringUtils.isNotBlank(releaseAllEntity.getConditionsofadoption())||!StringUtils.isNotBlank(releaseAllEntity.getRemarks())
                    ||!StringUtils.isNotBlank(releaseAllEntity.getServiceCharge())||!StringUtils.isNotBlank(releaseAllEntity.getStory())) {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "寄养详情发布信息参数不完整！");
            }
           //新增寄养表信息
            FosterEntity fosterEntity = new FosterEntity();
            fosterEntity.setId(UUID.randomUUID().toString());
            fosterEntity.setCardingConditions(releaseAllEntity.getCardingConditions());
            fosterEntity.setConditionsofadoption(releaseAllEntity.getConditionsofadoption());
            fosterEntity.setDays(releaseAllEntity.getDays());
            fosterEntity.setRemarks(releaseAllEntity.getRemarks());
            fosterEntity.setServiceCharge(releaseAllEntity.getServiceCharge());
            fosterEntity.setStory(releaseAllEntity.getStory());
            fosterEntity.setWashNum(releaseAllEntity.getWashNum());
            fosterEntity.setCertificatesImage(releaseAllEntity.getCertificatesImage());
            fosterEntity.setReleaseId(releaseId);
            fosterEntity.setCreate_date(new Date());
            Integer resInteger = fosterService.insertFoster(fosterEntity);
            //新增疫苗证明图片表
            VaccineImageEntity vaccineEntity = new VaccineImageEntity();
            vaccineEntity.setCreate_date(new Date());
            vaccineEntity.setReleaseId(releaseId);
            String[] vaccine = releaseAllEntity.getVaccineImage().split(",");
            List<VaccineImageEntity> vaccineList = new ArrayList<VaccineImageEntity>();
            for (String string : vaccine) {
                vaccineEntity.setId(UUID.randomUUID().toString());
                vaccineEntity.setVaccineImage(string);
                vaccineService.insertVaccine(vaccineEntity);
            }
            
            //新增发布图片表信息
            List<ReleaseImageEntity> releaseImageList = new ArrayList<ReleaseImageEntity>();
          //得到发布图片并装到数组
            String[] releaseImage = releaseAllEntity.getReleaseImage().split(",");
            for (String string : releaseImage) {
                ReleaseImageEntity releaseImageEntity = new ReleaseImageEntity();
                releaseImageEntity.setCreate_date(new Date());
                releaseImageEntity.setReleaseId(releaseId);
                releaseImageEntity.setId(UUID.randomUUID().toString());
                releaseImageEntity.setImage(string);
                releaseImageList.add(releaseImageEntity);
            }
            releaseImageService.insertBatchReleaseImage(releaseImageList);
            return ServerResponse.createBySuccess("新增成功！", releaseId);
        case "2":
            double moneys = walkDogChargeServiceImpl.selecWalkDogCharge(Integer.parseInt(releaseAllEntity.getHoursDays()), releaseAllEntity.getWeight());
            releasePetService.insertReleasePet(releasePetEntity);
            releasePetEntity.setServiceCharge(moneys);
            WalkDogEntity walkDogEntity = new WalkDogEntity();
            String walkDogId = UUID.randomUUID().toString();
            walkDogEntity.setId(walkDogId);
            walkDogEntity.setHoursDays(releaseAllEntity.getHoursDays());
            walkDogEntity.setHoursMouth(releaseAllEntity.getHoursMouth());
            walkDogEntity.setHoursWeeks(releaseAllEntity.getHoursWeeks());
            walkDogEntity.setReleaseId(releaseId);
            walkDogEntity.setCreate_date(new Date());
            walkDogService.insertWalkDog(walkDogEntity);
            //新增发布图片表信息
            String[] releaseImages = releaseAllEntity.getReleaseImage().split(",");
            List<ReleaseImageEntity> releaseImageLists = new ArrayList<ReleaseImageEntity>();
            for (String string : releaseImages) {
                ReleaseImageEntity releaseImageEntitys = new ReleaseImageEntity();
                releaseImageEntitys.setCreate_date(new Date());
                releaseImageEntitys.setReleaseId(walkDogId);
                releaseImageEntitys.setImage(string);
                releaseImageEntitys.setId(UUID.randomUUID().toString());
                releaseImageLists.add(releaseImageEntitys);
            }
            releaseImageService.insertBatchReleaseImage(releaseImageLists);
            return ServerResponse.createBySuccess("新增成功！", releaseId);
        default :
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "发布类型有误！");
        }
    }
    
    /**
     * 首页查询遛狗或寄养发布信息
     * @author 谷春
     * @param 
     * @return ServerResponse<List<ReleasePetEntity>>
     * @throws Exception
     */
    @GetMapping("/releasePet/type/{longitude}/{latitude}/{type}/{address}")
    @Timed
    @ApiOperation(value = "首页查询遛狗或寄养发布信息",notes = "接口说明：当前经纬度，type不能为空!")
    public ServerResponse<List<ReleasePetEntity>> selectFosterBypage(@PathVariable double longitude,@PathVariable double latitude,@PathVariable Integer type,@PathVariable String address){
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("type", type);
        maps.put("address", address);
        List<ReleasePetEntity> list =releasePetService.selectReleasePetBytype(maps);
        for (ReleasePetEntity releasePetEntity : list) {
            double sum;
            //算出距离 公式（（经度1 -经度2 ）*（经度1 -经度2 ）+（纬度1-纬度2）*（纬度1-纬度2）
            sum=Math.sqrt((longitude-releasePetEntity.getLatitude())*(longitude-releasePetEntity.getLatitude())+(latitude-releasePetEntity.getLongitude())*(latitude-releasePetEntity.getLongitude()));
          //  ReleasePetEntity releasePetEntitys = new ReleasePetEntity();
            releasePetEntity.setDistance(sum);
           /* releasePetEntitys.setId(releasePetEntity.getId());
            releasePetService.updateReleasePet(releasePetEntitys);*/
        }
        //根据
        return ServerResponse.createBySuccess(releasePetService.selectReleasePetBytype(maps));
    }
    
    /**
     * 个人中心根据状态和用户id查询数据
     * @author 谷春
     * @param 
     * @return ServerResponse<List<ReleasePetEntity>>
     * @throws Exception
     */
    @GetMapping("/releasePet/fosterBystate/{userId}/{state}/{type}")
    @Timed
    @ApiOperation(value = "个人中心根据状态和用户id查询数据（寄养/遛狗）",notes = "接口说明：state 1待领养  2领养中 3 已完成!")
    public ServerResponse<List<ReleasePetEntity>> selectFosterBypage(@PathVariable String userId,@PathVariable Integer state,@PathVariable Integer type){
        if (!StringUtils.isNotBlank(userId)||!StringUtils.isNotBlank(state+"")) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "参数不全！");
        }
        ReleasePetEntity releasePetEntity = new ReleasePetEntity();
        releasePetEntity.setUserId(userId);
        releasePetEntity.setState(state);
        releasePetEntity.setType(type);
        return ServerResponse.createBySuccess(releasePetService.selectLikeReleasePet(releasePetEntity));
    }
    
    /**
     *模糊分页查询
     * @author 谷春
     * @param 
     * @return ServerResponse<PageEntity<ReleasePetEntity>>
     * @throws Exception
     */
    public ServerResponse<PageEntity<ReleasePetEntity>> selectFosterByLikepage(@ApiParam @Validated @RequestBody ReleasePetEntity releasePetEntity){
        PageEntity pageEntity = new PageEntity();
        //封装总记录数
            int totalCount = releaseImageService.selectCount();
            pageEntity.setTotalCount(totalCount);
        if (releasePetEntity.getCurrentPage() != null) {
           //封装当前页数
           pageEntity.setCurrPage(releasePetEntity.getCurrentPage());
           //每页显示的数据
           int pageSize=5;
           pageEntity.setPageSize(pageSize);
           //封装总页数
           double tc = totalCount;
           Double num =Math.ceil(tc/pageSize);//向上取整
           pageEntity.setTotalPage(num.intValue());
           releasePetEntity.setCurrIndex((releasePetEntity.getCurrentPage() -1)*pageSize);
           releasePetEntity.setPageSize(pageSize);
         //封装每页显示的数据
           List<ReleasePetEntity> lists = releasePetService.selectLikeReleasePet(releasePetEntity);
           pageEntity.setLists(lists);
       }else {
           List<ReleasePetEntity> lists = releasePetService.selectLikeReleasePet(releasePetEntity);
           pageEntity.setLists(lists);
       }
        return ServerResponse.createBySuccess(pageEntity);
    }
    
    
    
    /**
     * 寄养完成的接口（根据寄养id修改寄养表的状态和助养表的状态）
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @GetMapping("/releasePet/success/{releaseId}")
    @Timed
    @ApiOperation(value = "寄养完成的接口",notes = "接口说明:releaseId为寄养id，不能为空!")
    public ServerResponse<Integer> updateReleaseState(String releaseId){
        if (!StringUtils.isNotBlank(releaseId)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "参数为空！");  
        }
        ReleasePetEntity releasePetEntity = new ReleasePetEntity();
        releasePetEntity.setId(releaseId);
        releasePetEntity.setState(3);
        releasePetService.updateReleasePet(releasePetEntity);
        AssistEntity assistEntity = new AssistEntity();
        assistEntity.setState(3);
        assistEntity.setReleaseId(releaseId);
        assistService.updateAssist(assistEntity);
        return ServerResponse.createBySuccessMessage("订单完成！");
    }
    
    /**
     * 查看详情接口
     */
    @GetMapping("/releasePet/fosterOrwalkdog/{releaseId}/{type}/{userId}")
    @Timed
    @ApiOperation(value = "查看遛狗或寄养查看的接口",notes = "接口说明:releaseId为发布id type 1寄养  2 遛狗!")
    public ServerResponse<List<ReleaseAllEntity>> selectReleaseStates(@PathVariable String releaseId,@PathVariable String type,@PathVariable String userId){
        if (!StringUtils.isNotBlank(userId)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "当前用户id为空！");
        }
        //调用用户表根据id查询数据的方法（判断用户是否注册）
        UserEntity userEntity = userService.selectUserByid(userId);
        if (userEntity == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_REGISTER.getCode(), "用户表没查询到数据，请检查userId！");
        }
        if (!StringUtils.isNotBlank(userEntity.getPhone())&&!StringUtils.isNotBlank(userEntity.getUserName())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_REGISTER.getCode(), "请先进行注册！");
        }
        if (!StringUtils.isNotBlank(type)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "type为空！");  
        }
        switch (type) {
        case "1":
          return ServerResponse.createBySuccess(releasePetService.selectReleasePetFoster(releaseId));
        case "2":
            return ServerResponse.createBySuccess(releasePetService.selectReleasePetWalkDog(releaseId));
        default:
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "type有误！");
        }
    }
}
