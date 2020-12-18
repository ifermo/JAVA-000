package pers.qingxuan.rpcfx.provider.service;

import pers.qingxuan.rpcfx.api.User;
import pers.qingxuan.rpcfx.api.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public User findById(Integer id) {
        return new User(id, "User:" + System.currentTimeMillis());
    }
}
