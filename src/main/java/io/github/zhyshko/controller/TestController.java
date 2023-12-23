package io.github.zhyshko.controller;

import io.github.zhyshko.DatabasePopulator;
import io.github.zhyshko.dto.order.OrderData;
import io.github.zhyshko.dto.order.OrderEntryData;
import io.github.zhyshko.mapper.dto.order.OrderMapper;
import io.github.zhyshko.mapper.wsdto.order.OrderEntryWsDtoMapper;
import io.github.zhyshko.mapper.wsdto.order.OrderWsDtoMapper;
import io.github.zhyshko.model.order.Order;
import io.github.zhyshko.wsdto.order.OrderEntryWsDto;
import io.github.zhyshko.wsdto.order.OrderWsDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private OrderWsDtoMapper orderWsDtoMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private DatabasePopulator databasePopulator;

    @GetMapping
    @ResponseBody
    public OrderWsDto t(HttpSession httpSession) {
        OrderData o = databasePopulator.getOrder();

        orderMapper.toModel(o);

        return orderWsDtoMapper.toWsDto(o);
    }

}
