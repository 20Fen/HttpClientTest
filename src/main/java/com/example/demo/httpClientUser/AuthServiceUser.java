package com.example.demo.httpClientUser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.model.po.TestPo;
import com.example.demo.util.HttpClientUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description: 从认证服务获取用户信息
 */
@Log4j2
@Service
public class AuthServiceUser {

//    get无参数
    public List<TestPo>  getTestPoList() {
        List<TestPo> testPoList = null;
        String getResult = HttpClientUtil.doGet("http://localhost:9998/findAll?page=1&pageSize=12");
        if (getResult != null && !"".equals(getResult)) {
            if (JSON.parseObject(getResult).getString("list") != null) {
                testPoList = JSON.parseArray(JSON.parseObject(getResult).getString("list"), TestPo.class);
            }else {
                log.error("获取数据失败");
            }
        } else {
            log.error("获取数据失败");
        }
        return testPoList;
    }

    //    get有参数
    public TestPo getTestPo(String planNo) {
        TestPo testPo = null;
        String getResult = HttpClientUtil.doGet("http://localhost:9998/byId"+"/"+ planNo);
        if (getResult != null && !"".equals(getResult)) {
            if (JSON.parseObject(getResult).getString("results") != null) {
                JSONObject resultJson = JSONObject.parseObject(getResult);
                JSONObject results = resultJson.getJSONObject("results");
                testPo = JSONObject.parseObject(results.toString(), TestPo.class);
            }else {
                log.error("获取用户数据失败");
            }
        } else {
            log.error("获取数据失败");
        }
        return testPo;
    }

    //    post有参数
    public String addTestPo(TestPo testPo) {
        String getResult = HttpClientUtil.doPost("http://localhost:9998/test",testPo);
        if (getResult != null && !"".equals(getResult)) {
        } else {
            log.error("获取数据失败");
        }
        return getResult;
    }
}
