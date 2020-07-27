package com.liuhuan.study.client;

import com.liuhuan.study.webflux.dao.model.User;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

/**
 * @author LiuHuan
 * @date 2020-07-27 11:11
 * @desc
 */
public class Client {

    public static void main(String[] args) {
        //调用服务器地址
        WebClient webClient = WebClient.create("http://127.0.0.1:52772");

        //根据id查询
        String id = "1";
        User user = webClient.get().uri("/users/{id}", id)
            .accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(User.class)
            .block();
        System.out.println(user.getName());

        //查询所有
        Flux<User> results = webClient.get().uri("/users")
            .accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(User.class);

        results.map(User::getName)
            .buffer().doOnNext(System.out::println).blockFirst();
    }

}
