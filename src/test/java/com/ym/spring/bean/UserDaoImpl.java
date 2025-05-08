package com.ym.spring.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YinMing
 * @since 2025/5/7 16:37
 */
public class UserDaoImpl implements UserDao {

    private static final Map<String, String> userMap = new HashMap<>();

    static {
        userMap.put("赵钱孙", "上海市");
    }

    @Override
    public String queryUserName(String userName) {
        return userMap.get(userName);
    }
}
