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
    public JapUser getByName(String username) {
        return userDatas.stream().filter((user)->user.getUsername().equals(username)).findFirst().orElse(null);
    }

    @Override
    public void saveHttpAuthedJapUser(JapUser japUser) {
        // 删除数据库中已经存在的该用户
        userDatas = userDatas.stream().filter((japUser1 -> {
            return japUser1.getUsername().equals(japUser.getUsername());
        })).collect(Collectors.toList());
        userDatas.add(japUser);
    }
}
