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

            // 1.??????follower?????????
            projectDetailVO.setFollower(SmallUtils.getRandom(SmallUtils.TEN));

            // 2.???????????????????????????
            List<ReturnDetailVO> returnDetailVOList = projectDetailVO.getReturnDetailVOList();

            // 2.1??????????????????????????????
            Integer projectSupportCount=0;

            for (ReturnDetailVO returnDetailVO : returnDetailVOList) {
                // ???????????????
                Integer randomReturnSupporterCount = SmallUtils.getRandom(SmallUtils.TEN);
                // ?????????????????????
                projectSupportCount = projectSupportCount + randomReturnSupporterCount;

                // ??????????????????
                returnDetailVO.setSupporterCount(randomReturnSupporterCount);

            }


            // 4.??????????????????
            Integer totalDay = projectDetailVO.getDay();
            // ?????????????????????
            Date currentDate = new Date();
            long currentDateTime = currentDate.getTime();

            // ???????????????????????????
            String deployDateString = projectDetailVO.getDeployDate();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date deployDate = simpleDateFormat.parse(deployDateString);
            long deployDateTime = deployDate.getTime();

            // ?????????????????????
            long pastDays=(currentDateTime-deployDateTime)/1000/60/60/24;

            // ??????????????????
            Integer lastDay = (int)(totalDay - pastDays);

            projectDetailVO.setLastDay(lastDay);

            return ResultEntity.successWithData(projectDetailVO);



        } catch (Exception exception) {

            exception.printStackTrace();
            return ResultEntity.failed(exception.getMessage());
        }

    }

}
