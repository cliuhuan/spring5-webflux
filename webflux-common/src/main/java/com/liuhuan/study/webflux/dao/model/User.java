package com.liuhuan.study.webflux.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiuHuan
 * @date 2020-07-27 10:44
 * @desc 实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String name;
    private String gender;
    private Integer age;

}
