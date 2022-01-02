package com.purchase.order.command;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class OrderValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return OrderProductCommand.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        OrderProductCommand command = (OrderProductCommand) target;
        if(command.getOrderItems().size() > 30) {
            errors.reject("구매오류", "최대 30개의 상품만 한번에 구매가 가능합니다.");
        }
    }
}
