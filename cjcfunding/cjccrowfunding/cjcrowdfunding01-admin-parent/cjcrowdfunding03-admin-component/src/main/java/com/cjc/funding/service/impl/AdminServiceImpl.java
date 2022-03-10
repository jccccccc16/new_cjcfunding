package com.cjc.funding.service.impl;


import com.cjc.funding.entity.Admin;
import com.cjc.funding.entity.AdminExample;
import com.cjc.funding.mapper.AdminMapper;
import com.cjc.funding.service.api.AdminService;
import com.cjc.funding.util.constant.CrowConstant;
import com.cjc.funding.util.exception.AdminNotExistDeleteFailedException;
import com.cjc.funding.util.exception.LoginAccountAlreadyExistException;
import com.cjc.funding.util.utils.CrowUtils;
import com.cjc.funding.util.exception.LoginFailedException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    private Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
    /**
     * 保存一个用户
     *
     * @param admin
     */
    public void save(Admin admin) {
        String md5Userpswd = CrowUtils.md5(admin.getUserPswd());
        // 1.对密码进行加密
        admin.setUserPswd(md5Userpswd);
        // 2.添加插入时间
        Date date = new Date();
        String dateString = CrowUtils.DataToStringConverter(date, CrowConstant.ATTR__PATTERN_DATE_YYYY_MM_DD_HH_MM_SS);
        admin.setCreateTime(dateString);
        adminMapper.insert(admin);
        logger.info("插入成功！登录名: "+admin.getLoginAcct());
    }

    /**
     * 查找全部admin
     *
     * @return
     */
    public List<Admin> getAll() {
        return adminMapper.selectByExample(new AdminExample());
    }

    public Admin getById(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    /**
     * 登录查询用户
     *
     * @param loginAcct
     * @param userPswd
     * @return
     */
    public Admin getAdminByAccount(String loginAcct, String userPswd) {
        // 1.查询admin得到对象
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        // 2.加入查询条件
        criteria.andLoginAcctEqualTo(loginAcct);
        List<Admin> adminList = adminMapper.selectByExample(adminExample);
        // 3.对表单的密码进行加密
        String md5UserPswd = CrowUtils.md5(userPswd);
        // 4.判断adminList是否合法
        if (adminList.size() == 0 || adminList==null){
            // 4.1 判断是否为空，为空抛出异常，登录失败
            throw new LoginFailedException(CrowConstant.MESSAGE_LOGIN_FAILED);
        }

        // 5.匹配密码
        Admin admin = adminList.get(0);
        logger.info("userPswdMd5: "+md5UserPswd+",admin.pswdMd5: "+admin.getUserPswd());

        if(CrowUtils.equalTo(md5UserPswd,admin.getUserPswd())){
            // 5.1 密码正确，返回admin
            return admin;
        }else{
            // 5.2 密码不正确，抛出异常，登录失败
            throw new LoginFailedException(CrowConstant.MESSAGE_LOGIN_FAILED);
        }

    }

    /**
     * 用于ajax，loginAcct是否存在
     * @param loginAcct
     * @return
     */
    public boolean getAdminByLoginAcct(String loginAcct) {
        // 1.获取AdminExample
        AdminExample adminExample = new AdminExample();
        // 2.获取Criteria对象，添加查询条件
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andLoginAcctEqualTo(loginAcct);
        // 3.调用adminMapper查询
        List<Admin> adminList = adminMapper.selectByExample(adminExample);
        // 4.判断adminList如果=1，抛出异常
        System.out.println("");
        if(adminList.size()==1){
            throw new LoginAccountAlreadyExistException(CrowConstant.MESSAGE_LOGIN_ACCOUNT_ALREADY_EXIST);
        }
        logger.info("该登录账号可用： "+loginAcct);
        return true;
    }

    /**
     * 用户查询admin分页
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        // 1.调用pageHelper进行分页
        // 非侵入式
        PageHelper.startPage(pageNum, pageSize);
        // 2.调用adminMapper，分页是PageHelper帮我们做了，其实返回的是Page
        List<Admin> adminList = adminMapper.selectAdminByKeyWord(keyword);
        // 3.把Page封装到pageInfo
        return new PageInfo<Admin>(adminList);
    }


    /**
     * 根据id删除
     * @param id
     * @return  返回影响的行数,如果返回的为0则表示用户不存在
     */
    public void deleteById(Integer id) {
        // 1.调用mapper执行删除操作
        int influencedRows  = adminMapper.deleteByPrimaryKey(id);
        // 2.判断influencedRows是否为0
        if(influencedRows<=0){
            // 3.不存在该用户，抛出用户不存在异常
            throw new AdminNotExistDeleteFailedException(CrowConstant.MESSAGE_ADMIN_NOT_EXIST);
        }
        // 3.删除成功
        logger.info("删除成功，admin.id = "+id);

    }

    /**
     * 修改admin
     * @param admin
     */
    public void edit(Admin admin) {
        logger.info("newAdmin: "+admin.toString());
        try{
            // 填充admin
            // 获取oldAdmin
            Admin oldAdmin = getById(admin.getId());
            logger.info("oldAdmin :"+oldAdmin);
            // 填充
            CrowUtils.completeModel(admin, oldAdmin, Admin.class);
            adminMapper.updateByPrimaryKeySelective(admin);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("异常全类名="+e.getClass().getName());
            if(e instanceof LoginAccountAlreadyExistException){
                throw new LoginAccountAlreadyExistException(CrowConstant.MESSAGE_LOGIN_ACCOUNT_ALREADY_EXIST);
            }
        }

    }

    @Override
    public void saveAdminRoleRelationship(Integer adminId, List<Integer> roleIdList) {

        // 1.删除该管理员旧的角色关联
        adminMapper.deleteOldRelationship(adminId);

        // 2.根据roleList和adminId保存角色关联
        // 3判断roleList是否为空
        if(roleIdList!=null || roleIdList.size()!=0) {
            // 3.2 roleList不为空，插入roleList中的角色到该管理员中
            adminMapper.insertNewRoleRelationship(adminId,roleIdList);
            logger.info("roleIdList: +"+roleIdList);
        }
        // 3.1 roleList为空，为删除该管理员的所有角色关联关系，又由于步骤1已经删除所有角色关联关系，
        // 所以不做操作


    }
}
