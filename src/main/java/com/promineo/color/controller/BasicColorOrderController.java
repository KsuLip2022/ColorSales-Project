/**
 * 
 */
package com.promineo.color.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineo.color.entity.Option;
import com.promineo.color.entity.Order;
import com.promineo.color.entity.OrderRequest;
import com.promineo.color.service.ColorOrderService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author pbuda
 *
 */
@Slf4j
@RestController
public class BasicColorOrderController implements ColorOrderController {

  @Autowired
  private ColorOrderService colorOrderService;
  
  @Override
  public Order createOrder(OrderRequest orderRequest) {
   log.info("Order = {}", orderRequest);
    return colorOrderService.createOrder(orderRequest);
  }

  @Override
  public Option updateOption(Option option) {
    log.info("Update option {}", option);
    return colorOrderService.updateOption(option);
  }

  @Override
  public Map<String, Object> deleteOption(String optionId) {
   colorOrderService.deleteOption(optionId);
   return Map.of("Message", "Option with ID = " + optionId + " successfully deleted");
  }

}
