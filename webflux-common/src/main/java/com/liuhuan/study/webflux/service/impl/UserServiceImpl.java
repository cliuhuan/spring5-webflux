package com.liuhuan.study.webflux.service.impl;

import com.liuhuan.study.webflux.dao.model.User;
import com.liuhuan.study.webflux.service.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LiuHuan
 * @date 2020-07-27 10:51
 * @desc
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * 创建map集合存储数据
     */
    private final Map<Integer,User> users = new HashMap<>();

    public UserServiceImpl() {
        users.put(1,new User("lucy","nan",20));
        users.put(2,new User("mary","nv",30));
        users.put(3,new User("jack","nv",50));
    }

    @Override
    public Mono<User> getUserById(int id) {
        return Mono.justOrEmpty(users.get(id));
    }

    @Override
    public Flux<User> getAllUser() {
        return Flux.fromIterable(users.values());
    }

    @Override
    public Mono<Void> saveUserInfo(Mono<User> user) {
        return user.doOnNext(person -> {
            //向map集合里面放值
            int id = users.size()+1;
            users.put(id,person);
        }).thenEmpty(Mono.empty());
    }
}
