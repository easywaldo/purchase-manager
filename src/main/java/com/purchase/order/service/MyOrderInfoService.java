package com.purchase.order.service;

import com.purchase.order.command.SelectOrderCommand;
import com.purchase.order.response.OrderViewModel;
import com.purchase.querygenerator.OrderQueryGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MyOrderInfoService {
    private final OrderQueryGenerator orderQueryGenerator;

    @Autowired
    public MyOrderInfoService(OrderQueryGenerator orderQueryGenerator) {
        this.orderQueryGenerator = orderQueryGenerator;
    }

    @Transactional(readOnly = true)
    public List<OrderViewModel> selectOrderProduct(SelectOrderCommand selectOrderCommand) {
        return this.orderQueryGenerator.selectOrderProductDetail(selectOrderCommand);
    }

    @Transactional(readOnly = true)
    public List<OrderViewModel> selectOrderList(SelectOrderCommand selectOrderCommand) {
        return this.orderQueryGenerator.selectOrderList(selectOrderCommand);
    }


}
