package com.example.demo.model.po;

import lombok.Data;

import java.util.List;

/**
 * Description: 数据库model
 */
@Data
public class TestPo1{

//    @NotBlank(message = "id不能为空")
    private String id;
    private String planNo;
    private String endTime;
    private String statTime;
    private String createTime;
    private String doc;
    private List<image> list;

}
