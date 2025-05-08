package com.ym.spring.bean;

/**
 * @author YinMing
 * @since 2025/5/7 16:30
 */
public class UserService {

    private String name;

    private UserDao userDao;

    public void init() {
        System.out.println("UserService Init");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String queryUserInfo() {
        return userDao.queryUserName(name);
    }
}
