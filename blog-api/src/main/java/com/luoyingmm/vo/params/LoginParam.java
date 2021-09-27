package com.luoyingmm.vo.params;

import lombok.Data;

import javax.sql.rowset.serial.SerialStruct;

@Data
public class LoginParam {
    private String account;
    private String password;
}
