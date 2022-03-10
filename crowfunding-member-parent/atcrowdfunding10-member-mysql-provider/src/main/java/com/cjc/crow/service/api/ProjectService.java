package com.cjc.crow.service.api;

import com.cjc.crow.entity.PortalTypeVO;
import com.cjc.crow.entity.ProjectDetailVO;
import com.cjc.crow.entity.ProjectVO;

import java.util.List;

public interface ProjectService {

    void saveProject(ProjectVO projectVO,Integer memberId);

    List<PortalTypeVO> getPortalTypeVOList();

    ProjectDetailVO getDetailProjectVO(Integer id);

}
