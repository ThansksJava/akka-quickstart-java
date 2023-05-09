package com.example10;

import org.springframework.stereotype.Service;

/**
 * @author Feng Jie
 * @date 2023/5/9 20:13
 */
@Service
public class UserServiceImpl implements UserService{
    @Override
    public void saveMsg(String msg) {
        System.out.println("存储msg:" + msg);
    }
}
