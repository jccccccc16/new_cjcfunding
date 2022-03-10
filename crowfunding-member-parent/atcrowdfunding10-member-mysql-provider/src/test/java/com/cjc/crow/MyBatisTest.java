package com.cjc.crow;

import com.cjc.crow.entity.PortalProjectVO;
import com.cjc.crow.entity.PortalTypeVO;
import com.cjc.crow.entity.ProjectDetailVO;
import com.cjc.crow.entity.ReturnDetailVO;
import com.cjc.crow.mapper.ProjectPOMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/10/28
 * Time: 20:32
 * To change this template use File | Settings | File Templates.
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisTest {

    @Autowired
    private DataSource datasource;

    @Resource
    private ProjectPOMapper projectPOMapper;

    private Logger logger = LoggerFactory.getLogger(MyBatisTest.class);


    @Test
    public void testConnection() throws SQLException {

        Connection connection = datasource.getConnection();
        logger.info(connection.toString());

    }

    @Test
    public void testProjectPOMapper(){
        List<PortalProjectVO> portalProjectVOS =
                projectPOMapper.selectPortalProjectVOByTypeId(4);
        System.out.println(portalProjectVOS);

    }

    @Test
    public void selectPortalTypeVOList(){
        List<PortalTypeVO> portalTypeVOS = projectPOMapper.selectPortalTypeVOList();
        for (PortalTypeVO portalTypeVO : portalTypeVOS) {
            System.out.println(portalTypeVO);
//            for (PortalProjectVO portalProjectVO : portalTypeVO.getPortalProjectVOList()) {
//                System.out.println(portalProjectVO);
//            }
        }
    }

    @Test
    public void selectDetailProjectVO(){
        ProjectDetailVO projectDetailVO = projectPOMapper.selectDetailProjectVO(5);
        System.out.println(projectDetailVO);
        List<String> detailPicturePathList = projectDetailVO.getDetailPicturePathList();
        for (String s : detailPicturePathList) {
            System.out.println(s);
        }

        List<ReturnDetailVO> returnDetailVOList = projectDetailVO.getReturnDetailVOList();
        for (ReturnDetailVO returnDetailVO : returnDetailVOList) {
            System.out.println(returnDetailVO);
        }


    }


}
