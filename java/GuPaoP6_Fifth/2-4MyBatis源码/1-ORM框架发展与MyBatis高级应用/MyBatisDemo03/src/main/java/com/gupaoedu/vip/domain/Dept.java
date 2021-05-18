package com.gupaoedu.vip.domain;

import lombok.Data;

import java.util.List;

@Data
public class Dept {
    private Integer dId;

    private String dName;

    private String dDesc;

    private List<User> users;
}
