package com.fujieid.httpapi.demo.service;


import com.fujieid.jap.core.JapUser;
import com.fujieid.jap.core.JapUserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JapHttpApiUserServiceImpl implements JapUserService {

    private static List<JapUser> userDatas = new ArrayList<>();

    @Override
    public JapUser getById(String userId) {
        return userDatas.stream().filter((user)->user.getUserId().equals(user)).findFirst().orElse(null);
    }

    @Override
    public JapUser getByName(String username) {
        return userDatas.stream().filter((user)->user.getUsername().equals(username)).findFirst().orElse(null);
    }

    @Override
    public boolean validPassword(String password, JapUser user) {
        return user.getPassword().equals(password);
    }

    @Override
    public JapUser getByPlatformAndUid(String platform, String uid) {
        return null;
    }


    @Override
    public JapUser createAndGetSocialUser(Object userInfo) {
        return null;
    }

    @Override
    public JapUser createAndGetOauth2User(String platform, Map<String, Object> userInfo, Object tokenInfo) {
        return null;
    }

    @Override
    public JapUser createAndGetHttpApiUser(JapUser japUser) {
        // 删除数据库中已经存在的该用户
        userDatas = userDatas.stream().filter((japUser1 -> {
            if(japUser1.getUsername().equals(japUser.getUsername())){
                return false;
            }else{
                return true;
            }
        })).collect(Collectors.toList());
        userDatas.add(japUser);
        return japUser;
    }
}
