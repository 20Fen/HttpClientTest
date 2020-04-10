package com.example.demo.httpClientUser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.model.po.TestPo;
import com.example.demo.util.HttpClientUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 从认证服务获取用户信息
 */
@Log4j2
@Service
public class AuthServiceUser {

//    get无参数
    public List<TestPo>  getTestPoList() {
        List<TestPo> testPoList = null;
        log.info("开始调用服务" + LocalDateTime.now().toString());
        String getResult = HttpClientUtil.doGet("http://localhost:9998/findAll?page=1&pageSize=12");
        log.info("调用服务结束" + LocalDateTime.now().toString());
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
        log.info("开始调用服务" + LocalDateTime.now().toString());
        String getResult = HttpClientUtil.doGet("http://localhost:9998/byId"+"/"+ planNo);
        log.info("调用服务结束" + LocalDateTime.now().toString());
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
    public static void main(String[] args) {

        String url= "http://localhost:8086/api/test/index";
        Map<String,Object> map=new HashMap<>();
        map.put("123",123);
        map.put("234",23);
        map.put("345",3);

        try {
            URIBuilder builder1 = new URIBuilder(url);

            for (String o : map.keySet()) {
                builder1.setParameter(o, String.valueOf(map.get(o)));
            }
            URI build = builder1.build();
//            HttpGet get=new HttpGet(build);
           String uri= String.valueOf(builder1.build());
            HttpClientUtil.doGet(uri);
            System.out.println(build);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }
    //    post有参数
    public String addTestPo(TestPo testPo) {
        log.info("开始调用服务" + LocalDateTime.now().toString());
        String getResult = HttpClientUtil.doPost("http://localhost:9998/test",testPo);
        log.info("调用服务结束" + LocalDateTime.now().toString());
        if (getResult != null && !"".equals(getResult)) {
        } else {
            log.error("获取数据失败");
        }
        return getResult;
    }
}
