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
import com.lingfeng.pets.entity.AssistEntity;
import com.lingfeng.pets.entity.PageEntity;
import com.lingfeng.pets.entity.ReleasePetEntity;
import com.lingfeng.pets.entity.UserEntity;
import com.lingfeng.pets.service.AssistService;
import com.lingfeng.pets.service.ReleasePetService;
import com.lingfeng.pets.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author 谷春
 *
 */
@RestController("AssistController")
@RequestMapping("/api")
@CrossOrigin
@Api(value = "/assistController", description = "助养表的接口")
public class AssistController {
    
    /**
     * 注入助养表的实现类
     */
    @Autowired
    private AssistService assistService;
    
    /**
     * 注入发布表的实现类
     */
    @Autowired
    private ReleasePetService releasePetService;
    
    /**
     * 注入用户表的实现类
     * 
     */
    @Autowired
    private UserService userService;
    
    /**
     * 新增助养表信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PostMapping("/assist")
    @Timed
    @ApiOperation(value = "新增助养表信息",notes = "新增AssistEntity说明：参数必须全部都有！")
    public ServerResponse<Integer> insertAssist(@ApiParam @Validated @RequestBody AssistEntity assistEntity){
        if (!StringUtils.isNotBlank(assistEntity.getUserId())) {
            return ServerResponse.createByErrorMessage("用户id为空！！");
        }else if(!StringUtils.isNotBlank(assistEntity.getReleaseId())) {
            return ServerResponse.createByErrorMessage("发布 id为空！！");
        }
        //判断用户是否注册
        UserEntity userEntity = userService.selectUserByid(assistEntity.getUserId());
        if (userEntity == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_REGISTER.getCode(), "用户表没查询到数据，请检查userId！");
        }
        if (!StringUtils.isNotBlank(userEntity.getPhone())||!StringUtils.isNotBlank(userEntity.getUserName())) {
            return ServerResponse.createByErrorMessage("需要注册才能评论！！");
        }
        ReleasePetEntity releasePetEntity = new ReleasePetEntity();
        releasePetEntity.setId(assistEntity.getReleaseId());
        List<ReleasePetEntity> releasePetEntities = releasePetService.selectLikeReleasePet(releasePetEntity);
        if(releasePetEntities.isEmpty()) return ServerResponse.createByErrorMessage("发布表没查询到数据！！");
        
        assistEntity.setServiceCharge(releasePetEntities.get(0).getServiceCharge());
        assistEntity.setId(UUID.randomUUID().toString());
        assistEntity.setCreate_date(new Date());
        return ServerResponse.createBySuccess("助养请求成功！", assistService.insertAssist(assistEntity));
    }
    /**
     * 删除助养表信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @DeleteMapping("/assist")
    @Timed
    @ApiOperation(value = "删除助养表信息",notes = "删除AssistEntity说明：根据id删除数据,还有最后操作人id！")
    public ServerResponse<Integer> deleteAssist(@ApiParam @Validated @RequestBody AssistEntity assistEntity){
        if (!StringUtils.isNotBlank(assistEntity.getId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "id不能为空！！");
        }else if (!StringUtils.isNotBlank(assistEntity.getLastModel_by())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "最后操作人不能为空！");
        }
        assistEntity.setLastModel_date(new Date());
        return ServerResponse.createBySuccess("删除发布信息成功！", assistService.deleteAssist(assistEntity));
    }
    
    
    /**
     * 删除助养表信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @DeleteMapping("/assistbatch")
    @Timed
    @ApiOperation(value = "删除助养表信息",notes = "删除AssistEntity说明：根据id删除数据,还有最后操作人id！")
    public ServerResponse<Integer> deleteBatchAssist(@ApiParam @Validated @RequestBody AssistEntity assistEntity){
        if (!StringUtils.isNotBlank(assistEntity.getId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "id不能为空！！");
        }else if (!StringUtils.isNotBlank(assistEntity.getLastModel_by())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "最后操作人不能为空！");
        }
        String [] ids = assistEntity.getId().split(",");
        Integer result = 0;
        for (String string : ids) {
            assistEntity.setLastModel_date(new Date());
            assistEntity.setId(string);
            assistService.deleteAssist(assistEntity);
            if(assistService.deleteAssist(assistEntity) > 0) result++;
        }
        return ServerResponse.createBySuccess("删除发布信息成功！", result);
    }
    
    /**
     * 修改助养表信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PutMapping("/assist")
    @Timed
    @ApiOperation(value = "删除助养表信息",notes = "修改AssistEntity说明：根据id修改数据,id不能为空！")
    public ServerResponse<Integer> updateAssist(@ApiParam @Validated @RequestBody AssistEntity assistEntity){
        if (!StringUtils.isNotBlank(assistEntity.getId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "id不能为空！！");
        }
        assistEntity.setUpdate_date(new Date());
        return ServerResponse.createBySuccess("修改发布信息成功！", assistService.updateAssist(assistEntity));
    }
    
    /**
     * 查询数据
     * @author 谷春
     * @param 
     * @return ServerResponse<ReleasePetEntity>
     * @throws Exception
     */
    @GetMapping("/assist/all")
    @Timed
    @ApiOperation(value = "查看所有",notes = "查看所有！")
    public ServerResponse<List<AssistEntity >> selectAllAssist(){
        return ServerResponse.createBySuccess(assistService.selectAllAssist());
    }
    
    /**
     * 根据发布id查询数据
     * @author 谷春
     * @param 
     * @return ServerResponse<ReleasePetEntity>
     * @throws Exception
     */
    @GetMapping("/assist/{releaseId}")
    @Timed
    @ApiOperation(value = "根据发布id查询数据",notes = "接口说明 ：发布id不能为空！")
    public ServerResponse<List<AssistEntity >> selectAssisByreleaseId(@PathVariable String releaseId){
        if (!StringUtils.isNotBlank(releaseId)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "发布id不能为空！！");
        }   
        List<AssistEntity> assistList= assistService.selectAssistByreleaseId(releaseId);
        for (AssistEntity assistEntity : assistList) {
            UserEntity userEntity= userService.selectUserByid(assistEntity.getUserId());
            assistEntity.setImage(userEntity.getImage());
        }
        return ServerResponse.createBySuccess(assistList);
    }
    
    /**
     * 助养订单     根据用户id和状态查询数据
     * @author 谷春
     * @param 
     * @return ServerResponse<ReleasePetEntity>
     * @throws Exception
     */
    @GetMapping("/assistuser/{userId}/{state}")
    @Timed
    @ApiOperation(value = "助养订单",notes = "接口说明 ：发布id不能为空！")
    public ServerResponse<List<ReleasePetEntity >> selectAssisByuserId(@PathVariable String userId,@PathVariable Integer state){
        if (!StringUtils.isNotBlank(userId)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "发布id不能为空！！");
        }
        //创建一个集合用户装结果
        List<ReleasePetEntity> result = new ArrayList<ReleasePetEntity>();
        
        AssistEntity assistEntity = new AssistEntity();
        assistEntity.setUserId(userId);
        List<AssistEntity> assistList =  assistService.selectLikeAssist(assistEntity);
        for (AssistEntity assistEntity2 : assistList) {
            ReleasePetEntity releasePetEntity =  releasePetService.selectReleasePetByuserIdandstate(assistEntity2.getReleaseId(), state);
            result.add(releasePetEntity);  
        }
        return ServerResponse.createBySuccess(result);
    }
    /**
     * 助养同意接口
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @GetMapping("/assist/{assistId}/{fosterId}")
    @Timed
    @ApiOperation(value = "助养同意接口",notes = "接口说明 ：助养id和寄养id不能为空！")
    public ServerResponse<Integer> updateAssistAndFoster(@PathVariable String assistId,@PathVariable String fosterId){
        if (!StringUtils.isNotBlank(assistId)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "助养id不能为空！！");
        }else if (!StringUtils.isNotBlank(fosterId)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "寄养id不能为空！！");
        }
        System.out.println("assistId="+assistId+"fosterId="+fosterId);
        AssistEntity assistEntity = new AssistEntity();
        assistEntity.setId(assistId);
        assistEntity.setState(2);
        assistEntity.setUpdate_date(new Date());
        Integer res = assistService.updateAssist(assistEntity); 
        if (res<=0)  return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "修改助养状态失败！");
        ReleasePetEntity releasePetEntity = new ReleasePetEntity();
        releasePetEntity.setId(fosterId);
        releasePetEntity.setState(2);
        releasePetEntity.setUpdate_date(new Date());
        Integer result =releasePetService.updateReleasePet(releasePetEntity);
        if (res<=0)  return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "修改寄养状态失败！");
        return ServerResponse.createBySuccessMessage("已同意！");
    }
    
    /**
     * 查询申请列表的接口
     * @author 谷春
     * @param 
     * @return ServerResponse<List<AssistEntity>>
     * @throws Exception
     */
    @PostMapping("/assist/apply")
    @Timed
    @ApiOperation(value = "申请列表的接口",notes = "接口说明 ：只要传releaseId！")
    public ServerResponse<List<AssistEntity >> selectAssisByreleaseIds(String releaseId){
        if (!StringUtils.isNotBlank(releaseId)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "发布id不能为空！！");
        }
        AssistEntity assistEntity = new AssistEntity();
        assistEntity.setReleaseId(releaseId);
        assistEntity.setIs_del(0);
        return ServerResponse.createBySuccess(assistService.selectLikeAssist(assistEntity));
    }
    
    /**
     * 模糊分页查询
     * @author 谷春
     * @param 
     * @return ServerResponse<List<AssistEntity>>
     * @throws Exception
     */
    @PostMapping("/assist/likePage")
    @Timed
    @ApiOperation(value = "模糊分页查询",notes = "模糊分页查询！")
    public ServerResponse<PageEntity<AssistEntity >> selectAssisByreleaseIds(@ApiParam @Validated @RequestBody AssistEntity assistEntity){
        PageEntity pageEntity = new PageEntity();
        //封装总记录数
        int totalCount = assistService.selectCount();
        pageEntity.setTotalCount(totalCount);
        if (assistEntity.getCurrentPage() != null) {
            //封装当前页数
            pageEntity.setCurrPage(assistEntity.getCurrentPage());
            //每页显示的数据
            int pageSize=5;
            pageEntity.setPageSize(pageSize);
            //封装总页数
            double tc = totalCount;
            Double num =Math.ceil(tc/pageSize);//向上取整
            pageEntity.setTotalPage(num.intValue());
            assistEntity.setCurrIndex((assistEntity.getCurrentPage() -1)*pageSize);
            assistEntity.setPageSize(pageSize);
          //封装每页显示的数据
            List<AssistEntity> lists = assistService.selectLikeAssist(assistEntity);
            pageEntity.setLists(lists);
        }else {
            List<AssistEntity> lists = assistService.selectLikeAssist(assistEntity);
            pageEntity.setLists(lists);
        }
        return ServerResponse.createBySuccess();
    }
    
    /**
     * 评价的接口（根据发布id进行修改）
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PostMapping("/assist/evaluate")
    @Timed
    @ApiOperation(value = "评价的接口",notes = "接口说明 ：fosterId为寄养id！")
    public ServerResponse<Integer> updateAssist(String fosterId,Integer score,String writtenWords){
        if (!StringUtils.isNotBlank(score+"")) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "星级评分不能为空！！");
        }
        else if (!StringUtils.isNotBlank(writtenWords)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "文字评价不能为空！！");      
        }
        else if (!StringUtils.isNotBlank(fosterId)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "寄养id不能为空！！");
        }
        //根据助养id查询用户id
        List<AssistEntity> assList = assistService.selectAssistByreleaseId(fosterId);
        if (assList.isEmpty()) return ServerResponse.createByErrorMessage("没查询到数据！！！");
        //根据用户id查询到用户积分
        UserEntity userEntitys = userService.selectUserByid(assList.get(0).getUserId());
        Integer integral = userEntitys.getIntegral();
        //积分算法  ： 一颗星 差评 用户积分减2 两颗星  一般  不增不减  三个星 积分加2 四个星 积分加5
        UserEntity userEntity = new UserEntity();
        userEntity.setUpdate_date(new Date());
        userEntity.setId(assList.get(0).getUserId());
        switch (score) {
        case 1:
            userEntity.setIntegral(integral-2);
            userService.updateUser(userEntity);
            break;
        case 3:
            userEntity.setIntegral(integral+2);
            userService.updateUser(userEntity);
            break;
        case 4:
            userEntity.setIntegral(integral+5);
            userService.updateUser(userEntity);
          break;
        }
        AssistEntity assistEntity = new AssistEntity();
        assistEntity.setReleaseId(fosterId);
        assistEntity.setScore(score);
        assistEntity.setWrittenWords(writtenWords);
        assistService.updateAssist(assistEntity);
        return ServerResponse.createBySuccess("评价成功！", assistService.updateAssist(assistEntity));
    }
    
    /**
     * 联系主人
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @GetMapping("/assist/contact/{orderId}")
    @Timed
    @ApiOperation(value = "联系主人",notes = "接口说明 联系主人")
    public ServerResponse<String> contact(@PathVariable String orderId){
        if (!StringUtils.isNotBlank(orderId)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "订单id为空！！");
        }
        return ServerResponse.createBySuccess(releasePetService.selectphone(orderId));
    }
}
