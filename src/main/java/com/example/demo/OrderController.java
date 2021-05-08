package com.example.demo;


import com.example.demo.mapper.OrderMapper;
import com.example.demo.mapper.TacoOrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
    private OrderMapper orderMapper;
    private TacoOrderMapper tacoOrderMapper;
    @Autowired
    public OrderController(OrderMapper orderMapper,TacoOrderMapper tacoOrderMapper){
        this.orderMapper=orderMapper;
        this.tacoOrderMapper=tacoOrderMapper;
    }

    @GetMapping("/current")
    public String orderForm(Model model){
//        model.addAttribute("order",new Order());
        return "OrderForm";
    }

    @PostMapping
    public String processOrder(@Valid  @ModelAttribute Order order, Errors errors,
                               SessionStatus sessionStatus) {
       if(errors.hasErrors()){
           return "OrderForm";
       }
        save(order);
        sessionStatus.setComplete();
        log.info("Order submitted: " + order);
        return "redirect:/";
    }

    public Order save(Order order){
        order.setPlacedAt(new Date());
        orderMapper.saveOrderDetails(order);
        long orderId=order.getId();
//        order.setId(orderId);
        List<Taco> tacos=order.getTacos();
        for(Taco taco:tacos){
            tacoOrderMapper.saveTacoToOrder(taco.getId(),orderId);

        }
        return order;
    }

}
