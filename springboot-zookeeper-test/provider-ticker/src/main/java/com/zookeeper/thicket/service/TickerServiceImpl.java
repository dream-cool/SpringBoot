package com.zookeeper.thicket.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;


@Component
@Service
public class TickerServiceImpl implements TickerService {

    @Override
    public String getTicker(){
        return "厉害了";
    }
}
