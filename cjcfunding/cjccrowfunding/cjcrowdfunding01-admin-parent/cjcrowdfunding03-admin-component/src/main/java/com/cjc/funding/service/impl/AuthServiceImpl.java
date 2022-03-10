package com.cjc.funding.service.impl;

import com.cjc.funding.mapper.AuthMapper;
import com.cjc.funding.service.api.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/10/16
 * Time: 16:27
 * To change this template use File | Settings | File Templates.
 **/
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthMapper authMapper;

}
