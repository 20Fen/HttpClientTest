package com.example.demo.model.po;

import lombok.Data;

/**
 * Description: 数据库model
 */
@Data
public class TestPo{

//    @NotBlank(message = "id不能为空")
    private String id;
    private String planNo;
    private String endTime;
    private String statTime;
    private String createTime;
    private String doc;
    private String url;

}
