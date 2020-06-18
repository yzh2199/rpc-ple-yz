package com.yz.rpc.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户类
 *
 * @author yz
 * create at 2020/4/5
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private String userName;

}
