package com.liuhuan.study.webflux.service;

import com.liuhuan.study.webflux.dao.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author LiuHuan
 * @date 2020-07-27 10:48
 * @desc 用户操作接口
 */
public interface UserService {

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    Mono<User> getUserById(int id);

    /**
     * 查询所有用户
     * @return
     */
    Flux<User> getAllUser();

    /**
     * 添加用户
     * @param user
     * @return
     */
    Mono<Void> saveUserInfo(Mono<User> user);

}
