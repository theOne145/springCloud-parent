package com.theOne.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private String id;

    private String userName;

    private String userPwd;

    private String userPhone;

    private Date createTime;

    private Date modifyTime;

    private String remark;
}
