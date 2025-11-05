package com.ecommerce.shop.ping.controllers;

import com.ecommerce.shop.ping.entities.PingEntity;
import com.ecommerce.shop.ping.repositories.PingRepository;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/ping")
public class PingController {

    private final Logger logger = LoggerFactory.getLogger(PingController.class);
    private final PingRepository pingRepository;
    private final MyRequestBean myRequestBean;

    public PingController(PingRepository pingRepository, MyRequestBean myRequestBean) {
        this.pingRepository = pingRepository;
        this.myRequestBean = myRequestBean;
    }

    @WithSpan(value = "greeting span")
    @GetMapping("/")
    public String Ping() {
        logger.info("Ping");
        logger.info(myRequestBean.getValue());

        pingRepository.save(new PingEntity());

        return "Hello World";
    }

    @Bean
    @WithSpan(value = "greeting span")
    @GetMapping("/count")
    public long Count() {
        long count = pingRepository.count();

        logger.atInfo()
                .addKeyValue("ping_count", count)
                .log("Ping count is {}.", count);

        return count;
    }
}

