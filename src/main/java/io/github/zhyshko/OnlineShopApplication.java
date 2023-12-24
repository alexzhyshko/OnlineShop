package io.github.zhyshko;

import io.github.zhyshko.mapper.wsdto.order.OrderEntryWsDtoMapper;
import io.github.zhyshko.wsdto.order.OrderEntryWsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableFeignClients
public class OnlineShopApplication {



    public static void main(String[] args) {
        SpringApplication.run(OnlineShopApplication.class, args);
    }

}
