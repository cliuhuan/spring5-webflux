package com.liuhuan.study.handler;

import com.liuhuan.study.webflux.dao.model.User;
import com.liuhuan.study.webflux.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

/**
 * @author LiuHuan
 * @date 2020-07-27 11:10
 * @desc
 */
public class UserHandler {

    private final UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    /**
     * 根据id查询
     * @param request
     * @return
     */
    public Mono<ServerResponse> getUserById(ServerRequest request) {
        //获取id值
        int userId = Integer.valueOf(request.pathVariable("id"));
        //空值处理
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        //调用service方法得到数据
        Mono<User> userMono = userService.getUserById(userId);
        //把userMono进行转换返回
        //使用Reactor操作符flatMap
        return
            userMono
                .flatMap(person -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                    .body(fromObject(person)))
                .switchIfEmpty(notFound);
    }

    /**
     * 查询所有
     * @param request
     * @return
     */
    public Mono<ServerResponse> getAllUsers(ServerRequest request) {
        //调用service得到结果
        Flux<User> users = this.userService.getAllUser();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(users,User.class);
    }

    /**
     * 添加
     * @param request
     * @return
     */
    public Mono<ServerResponse> saveUser(ServerRequest request) {
        //得到user对象
        Mono<User> userMono = request.bodyToMono(User.class);
        return ServerResponse.ok().build(this.userService.saveUserInfo(userMono));
    }

}
