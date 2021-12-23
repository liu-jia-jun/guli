package com.service_base.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 刘佳俊
 */
@Data
@AllArgsConstructor// 有参构造
@NoArgsConstructor// 无参构造
public class GuliException extends RuntimeException{

    private Integer code;// 状态码

    private String msg;// 报错信息

}
