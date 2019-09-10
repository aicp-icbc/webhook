package com.aicp.icbc.webhook.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: 吴开云
 * @Date: 2019/8/21 0021
 * @Version： 1.0
 */
@Component
@Slf4j
public class DataLoadingDao implements CommandLineRunner {

    @Override
    @Order(1)
    public void run(String... args) throws Exception {
//        log.info("程序启动模拟 ------ 准备读取接口数据");
//        log.info("程序启动模拟 ------ 接口数据读取完毕");
    }


}
