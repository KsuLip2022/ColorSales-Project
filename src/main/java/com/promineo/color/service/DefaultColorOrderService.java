/**
 * 
 */
package com.promineo.color.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.promineo.color.dao.ColorOrderDao;
import com.promineo.color.entity.Color;
import com.promineo.color.entity.Customer;
import com.promineo.color.entity.Option;
import com.promineo.color.entity.Order;
import com.promineo.color.entity.OrderRequest;


/**
 * @author pbuda
 *
 */
@Service
public class DefaultColorOrderService implements ColorOrderService {

  @Autowired
  private ColorOrderDao colorOrderDao;
  
  @Transactional
  @Override
  public Order createOrder(OrderRequest orderRequest) { 
    
    Customer customer = getCustomer(orderRequest);   
    Color color = getColor(orderRequest);
    BigDecimal price = color.getBasePrice();
    List<Option> options = getOption(orderRequest);
    
    for(Option option: options) {
      price = price.add(option.getPrice());
    }
    
    return colorOrderDao.saveOrder(customer, color, price, options);

  }

  /**
   * @param orderRequest
   * @return
   */
  private List<Option> getOption(OrderRequest orderRequest) {
   
    return colorOrderDao.fetchOptions(orderRequest.getOptions());
  }

  /**
   * @param orderRequest
   * @return
   */
  protected Color getColor(OrderRequest orderRequest) {
    return colorOrderDao.fetchBrand(orderRequest.getBrand(), orderRequest.getType())
        .orElseThrow(() -> new NoSuchElementException("Brand with ID = "
            + orderRequest.getBrand() + ", type =" + orderRequest.getType() + "was not found"));
  }

  /**
   * @param orderRequest
   * @return
   */
  protected Customer getCustomer(OrderRequest orderRequest) {
    return colorOrderDao.fetchCustomer(orderRequest.getCustomer())
        .orElseThrow(() -> new NoSuchElementException("Customer with ID = "
            + orderRequest.getCustomer() + "was not found"));
  }

  @Override
  @Transactional(readOnly = false)
  public void deleteOption(String optionId) {
     if (!colorOrderDao.deleteOption(optionId)) {
       throw new NoSuchElementException("Option with Id = " + optionId + " does not exist.");
     }
  }
  
  @Override
  public Option updateOption(Option option) {
    if (!colorOrderDao.updateOption(option)) {
      throw new NoSuchElementException("Option with Id= " + option.getOptionId() + "does not exist.");
  }
    return option;
  }  
  
  
}
