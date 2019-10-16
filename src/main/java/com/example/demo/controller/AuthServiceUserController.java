package com.example.demo.controller;

import com.example.demo.httpClientUser.AuthServiceUser;
import com.example.demo.model.po.TestPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: controller 类
 */
@RestController
public class AuthServiceUserController {

    @Autowired
    private AuthServiceUser authServiceUser;

    @RequestMapping(value = "/testpoAll",method = RequestMethod.GET)
    public List<TestPo> getTestPoList() {
        List<TestPo> testPoList = authServiceUser.getTestPoList();
        if(CollectionUtils.isEmpty(testPoList)){
            return new ArrayList<TestPo>();
        }
        return testPoList;
    }

    @RequestMapping(value = "/byId/{planNo}",method = RequestMethod.GET)
    public TestPo getTestPo(@PathVariable("planNo")String planNo) {
        TestPo testPo = authServiceUser.getTestPo(planNo);
        if(StringUtils.isEmpty(testPo)){
            return testPo;
        }
        return testPo;
    }

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public String getTestPo() throws Exception {
        TestPo testPo=new TestPo();
        testPo.setCreateTime("2019-10-08");
        testPo.setEndTime("2019-10-08 10:34:25");
        testPo.setStatTime("2019-10-08 10:34:25");
        String addTestPo = authServiceUser.addTestPo(testPo);
        if(StringUtils.isEmpty(addTestPo)){
            throw new Exception("添加失败");
        }
        return addTestPo;
    }
}
