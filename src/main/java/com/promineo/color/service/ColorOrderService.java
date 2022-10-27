/**
 * 
 */
package com.promineo.color.service;

import javax.validation.Valid;
import com.promineo.color.entity.Option;
import com.promineo.color.entity.Order;
import com.promineo.color.entity.OrderRequest;

/**
 * @author pbuda
 *
 */
public interface ColorOrderService {

  /**
   * @param orderRequest
   * @return
   */
  Order createOrder(OrderRequest orderRequest);

  

  /**
   * @param option
   * @return
   */
  Option updateOption(Option option);



  /**
   * @param optionId
   */
  void deleteOption(String optionId);

  

  
}
