package com.cjc.crow.handler;

import com.cjc.crow.constant.CrowdConstant;
import com.cjc.crow.entity.*;
import com.cjc.crow.service.api.ProjectService;
import com.cjc.crow.util.ResultEntity;
import com.cjc.crow.util.SmallUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/11/15
 * Time: 12:45
 * To change this template use File | Settings | File Templates.
 **/
@Controller
public class ProjectProviderHandler {


    @Autowired
    private ProjectService projectService;

    @ResponseBody
    @RequestMapping("/get/portal/type/VO/remote")
    public ResultEntity<List<PortalTypeVO>> getPortalTypeVORemote(){


        try {
            List<PortalTypeVO> portalTypeVOList = projectService.getPortalTypeVOList();
            ResultEntity<List<PortalTypeVO>> listResultEntity = ResultEntity.successWithData(portalTypeVOList);
            return listResultEntity;
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResultEntity.failed(exception.getMessage());
        }


    }


    @ResponseBody
    @RequestMapping("/save/projectVO")
    public ResultEntity<String>  saveProjectVORemote(

            @RequestBody ProjectVO projectVO,
            @RequestParam("memberId") Integer memberId
    ){

        try {
            projectService.saveProject(projectVO,memberId);

            return ResultEntity.successWithoutData();

        } catch (Exception exception) {

            exception.printStackTrace();

            return ResultEntity.failed(exception.getMessage());

        }


    }



    @ResponseBody
    @RequestMapping("/get/project/detail/by/project/id/remote/{id}")
    public ResultEntity<ProjectDetailVO> getProjectDetailByProjectId(@PathVariable("id") Integer projectId){

        try {

            ProjectDetailVO projectDetailVO = projectService.getDetailProjectVO(projectId);

            if(projectDetailVO==null){
                return ResultEntity.failed(CrowdConstant.MESSAGE_RESOURCE_VISIT_EXCEPTION);
            }

            // 1.设置follower假数据
            projectDetailVO.setFollower(SmallUtils.getRandom(SmallUtils.TEN));

            // 2.设置回报的支持人数
            List<ReturnDetailVO> returnDetailVOList = projectDetailVO.getReturnDetailVOList();

            // 2.1定义回报支持的总人数
            Integer projectSupportCount=0;

            for (ReturnDetailVO returnDetailVO : returnDetailVOList) {
                // 获取随机数
                Integer randomReturnSupporterCount = SmallUtils.getRandom(SmallUtils.TEN);
                // 加入总回报人数
                projectSupportCount = projectSupportCount + randomReturnSupporterCount;

                // 设置回报人数
                returnDetailVO.setSupporterCount(randomReturnSupporterCount);

            }


            // 4.设置剩余时间
            Integer totalDay = projectDetailVO.getDay();
            // 当前时间时间戳
            Date currentDate = new Date();
            long currentDateTime = currentDate.getTime();

            // 获取发布众筹时间戳
            String deployDateString = projectDetailVO.getDeployDate();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date deployDate = simpleDateFormat.parse(deployDateString);
            long deployDateTime = deployDate.getTime();

            // 计算过了多少天
            long pastDays=(currentDateTime-deployDateTime)/1000/60/60/24;

            // 计算剩余时间
            Integer lastDay = (int)(totalDay - pastDays);

            projectDetailVO.setLastDay(lastDay);

            return ResultEntity.successWithData(projectDetailVO);



        } catch (Exception exception) {

            exception.printStackTrace();
            return ResultEntity.failed(exception.getMessage());
        }

    }

}
