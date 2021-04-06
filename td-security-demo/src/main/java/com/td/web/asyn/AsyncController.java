package com.td.web.asyn;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

/**
 * @className: AsyncController
 * @description:
 * @author: cyd
 * @date: 2019/12/1 下午5:54
 **/
@RestController
public class AsyncController {

    private Logger log = LoggerFactory.getLogger(AsyncController.class);

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    /**
     * 同步线程处理
     * @return
     * @throws Exception
     */
    @RequestMapping("/order")
    public String order() throws Exception {
        log.info("主线程开始");
        Thread.sleep(1000);
        log.info("主线程结束");
        return "success";
    }

    /**
     * 同步线程处理
     * @return
     * @throws Exception
     */
    @RequestMapping("/callableOrder")
    public Callable<String> callableOrder() throws Exception {
        log.info("主线程开始");
        Callable<String> result = new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("副线程开始");
                Thread.sleep(1000);
                log.info("副线程返回");
                return "success";
            }
        };
        log.info("主线程返回");
        return result;
    }

    /**
     * 异步线程处理
     * @return
     */
    @RequestMapping("/sysorder")
    public DeferredResult<String> sysorder() throws Exception {
        log.info("主线程开始");


        // 方式1 使用callable实现异步处理
//        Callable<String> result = new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                log.info("副线程开始");
//                Thread.sleep(10000);
//                log.info("副线程返回");
//                return null;
//            }
//        };
//        return result;
        // 方式二，使用deferredResult，里面会有3个线程
        // 线程一，接收http请求
        String orderNumber = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumber);
        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNumber,result);

        log.info("主线程结束");
        return result;
    }
}
