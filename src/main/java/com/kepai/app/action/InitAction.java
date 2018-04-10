package com.kepai.app.action;

import com.kepai.app.service.UserService;
import com.kepai.base.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huang
 * @ProjectName base
 * @Copyright Hangzhou ShuoChuang Technology Co.,Ltd All Right Reserved
 * @Description 这里是对文件的描述
 * @data 2018/4/4
 * @note 这里写文件的详细功能和改动
 * @note
 */
@RestController
public class InitAction extends AppAction {

    @Autowired
    UserService userService;

    @RequestMapping("")
    public HttpResult<String> empty() {
        return HttpResult.respOK("");
    }


}
