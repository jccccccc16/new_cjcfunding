package com.cjc.funding.mvc.conifg;

import com.cjc.funding.util.utils.CrowUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/10/18
 * Time: 11:04
 * To change this template use File | Settings | File Templates.
 *  spring security 登录时用于明文加密
 **/
@Component
public class Mp5PassEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        // 1. 获取源的字符串
        String password = charSequence.toString();

        // 2.加密
        String encodedPassword = CrowUtils.md5(password);

        return encodedPassword;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {

        String result = CrowUtils.md5(rawPassword.toString());
        return Objects.equals(result,encodedPassword);

    }
}
