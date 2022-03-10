package com.cjc.crow.handler;

import com.cjc.crow.api.MySqlRemoteService;
import com.cjc.crow.config.OSSProperties;
import com.cjc.crow.constant.CrowdConstant;
import com.cjc.crow.entity.*;
import com.cjc.crow.util.CrowdUtil;
import com.cjc.crow.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/11/13
 * Time: 19:39
 * To change this template use File | Settings | File Templates.
 **/
@Controller
public class ProjectHandler {

    @Autowired
    private OSSProperties ossProperties;

    @Resource
    private MySqlRemoteService mySqlRemoteService;

    private Logger logger = LoggerFactory.getLogger(ProjectHandler.class);

    @RequestMapping("/create/project/information")
    public String saveProjectBasicInfo(

            ProjectVO projectVO,

            // 接受头图
            MultipartFile headerPicture,

            // 详情图
            List<MultipartFile> detailPictureList,

            //
            HttpSession session,

            // 上传失败后，返回上一个页面，并且挈带错误信息
            ModelMap modelMap
    ) throws IOException {

        // 上传头图片

        boolean isEmpty = headerPicture.isEmpty();

        // 用户没有上传头图片
        if(isEmpty){

            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,CrowdConstant.MESSAGE_NAME_HEADER_PICTURE_EMPTY);

            return "project-launch";

        } else{  // 用户上传了头图片

            // 将图片上传到服务器
            ResultEntity<String> uploadResultEntity = CrowdUtil.uploadFileToOss(
                    ossProperties.getEndPoint(),
                    ossProperties.getAccessKeyId(),
                    ossProperties.getAccessKeySecret(),
                    headerPicture.getInputStream(),
                    ossProperties.getBucketName(),
                    ossProperties.getBucketDomain(),
                    headerPicture.getOriginalFilename()
            );

            String result = uploadResultEntity.getResult();

            // 图片上传成功
            if (ResultEntity.SUCCESS.equals(result)) {

                // 返回图片的访问连接
                String headerPicturePath = uploadResultEntity.getData();

                // 将图片链接存入projectVo
                projectVO.setHeaderPicturePath(headerPicturePath);


            } else { // 图片上传失败


                modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_HEADER_MESSAGE_UPLOAD_FAILED);

                return "project-launch";
            }

        }

        // 上传详情图片

        // 判断详情图是否为空
        if(detailPictureList==null || detailPictureList.size()==0){

            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,
                    CrowdConstant.MESSAGE_DETAIL_PICTURE_EMPTY);

            return "project-launch";

        }


        List<String> detailPicturePathList = new ArrayList<String>();

        // 遍历详情图片列表，逐一上传
        for (MultipartFile detailPicture : detailPictureList) {

            if (!detailPicture.isEmpty()) {

                ResultEntity<String> resultEntity = CrowdUtil.uploadFileToOss(
                        ossProperties.getEndPoint(),
                        ossProperties.getAccessKeyId(),
                        ossProperties.getAccessKeySecret(),
                        headerPicture.getInputStream(),
                        ossProperties.getBucketName(),
                        ossProperties.getBucketDomain(),
                        headerPicture.getOriginalFilename()
                );

                String detailResult = resultEntity.getResult();

                if (ResultEntity.SUCCESS.equals(detailResult)) {

                    String path = resultEntity.getData();

                    detailPicturePathList.add(path);
                }
            }

        }

        projectVO.setDetailPicturePathList(detailPicturePathList);


        // 把project放到sessino中，先不做保存操作
        session.setAttribute(CrowdConstant.ATTR_NAME_TEMPLE_PROJECT, projectVO);

        // 跳转到回报页面
        return "redirect:http://localhost:80/project/return/protocol/page.html";

    }



    // JavaScript代码：formData.append("returnPicture", file);
    // returnPicture是请求参数的名字
    // file是请求参数的值，也就是要上传的文件
    @ResponseBody
    @RequestMapping("/create/upload/return/picture.json")
    public ResultEntity<String> uploadReturnPicture(

            // 接收用户上传的文件
            @RequestParam("returnPicture") MultipartFile returnPicture) throws IOException {

        // 1.执行文件上传
        ResultEntity<String> uploadReturnPicResultEntity = CrowdUtil.uploadFileToOss(
                ossProperties.getEndPoint(),
                ossProperties.getAccessKeyId(),
                ossProperties.getAccessKeySecret(),
                returnPicture.getInputStream(),
                ossProperties.getBucketName(),
                ossProperties.getBucketDomain(),
                returnPicture.getOriginalFilename());

        // 2.返回上传的结果
        return uploadReturnPicResultEntity;
    }

    @ResponseBody
    @RequestMapping("/create/save/return.json")
    public ResultEntity<String> saveReturn(ReturnVO returnVO, HttpSession session) {

        try {

            // 1.从session域中读取之前缓存的ProjectVO对象
            ProjectVO projectVO = (ProjectVO) session.getAttribute(CrowdConstant.ATTR_NAME_TEMPLE_PROJECT);

            // 2.判断projectVO是否为null
            if(projectVO == null) {
                return ResultEntity.failed(CrowdConstant.MESSAGE_TEMPLE_PROJECT_MISSING);
            }

            // 3.从projectVO对象中获取存储回报信息的集合
            List<ReturnVO> returnVOList = projectVO.getReturnVOList();

            // 4.判断returnVOList集合是否有效
            if(returnVOList == null || returnVOList.size() == 0) {

                // 5.创建集合对象对returnVOList进行初始化
                returnVOList = new ArrayList<ReturnVO>();
                // 6.为了让以后能够正常使用这个集合，设置到projectVO对象中
                projectVO.setReturnVOList(returnVOList);
            }

            // 7.将收集了表单数据的returnVO对象存入集合
            returnVOList.add(returnVO);

            projectVO.setReturnVOList(returnVOList);

            logger.info(projectVO.toString());

            // 8.更新session的数据,把数据有变化的ProjectVO对象重新存入Session域，以确保新的数据最终能够存入Redis
            session.setAttribute(CrowdConstant.ATTR_NAME_TEMPLE_PROJECT, projectVO);

            // 9.所有操作成功完成返回成功
            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            e.printStackTrace();

            return ResultEntity.failed(e.getMessage());
        }

    }

    /**
     * 保存确定信息是发布的最后异步，所以保存项目信息都在这一步进行
     * @param modelMap
     * @param session
     * @param memberConfirmInfoVO
     * @return
     */
    @RequestMapping("/create/confirm")
    public String saveConfirm(ModelMap modelMap, HttpSession session, MemberConfirmInfoVO memberConfirmInfoVO) {

        // 1.从Session域读取之前临时存储的ProjectVO对象
        ProjectVO projectVO = (ProjectVO) session.getAttribute(CrowdConstant.ATTR_NAME_TEMPLE_PROJECT);

        // 2.如果projectVO为null
        if(projectVO == null) {
            throw new RuntimeException(CrowdConstant.MESSAGE_TEMPLE_PROJECT_MISSING);
        }

        // 3.将确认信息数据设置到projectVO对象中
        projectVO.setMemberConfirmInfoVO(memberConfirmInfoVO);

        // 4.从Session域读取当前登录的用户
        MemberLoginVO memberLoginVO = (MemberLoginVO) session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_MEMBER);

        Integer memberId = memberLoginVO.getId();

        // 5.调用远程方法保存projectVO对象
        ResultEntity<String> saveResultEntity = mySqlRemoteService.saveProjectVORemote(projectVO, memberId);

        // 6.判断远程的保存操作是否成功
        String result = saveResultEntity.getResult();
        if(ResultEntity.FAILED.equals(result)) {

            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, saveResultEntity.getMessage());

            return "project-confirm";
        }

        // 7.将临时的ProjectVO对象从Session域移除
        session.removeAttribute(CrowdConstant.ATTR_NAME_TEMPLE_PROJECT);

        // 8.如果远程保存成功则跳转到最终完成页面
        return "redirect:http://localhost:80/project/create/success.html";
    }


    @RequestMapping("/show/detail/project/{id}")
    public String showDetailProject(@PathVariable("id") Integer id,ModelMap modelMap){

        ResultEntity<ProjectDetailVO> projectDetailByProjectId = mySqlRemoteService.getProjectDetailByProjectId(id);

        ProjectDetailVO projectDetailVO= projectDetailByProjectId.getData();

        modelMap.addAttribute(CrowdConstant.ATTR_NAME_DETAIL_PROJECT,projectDetailVO);

        return "project-detail";

    }

}
