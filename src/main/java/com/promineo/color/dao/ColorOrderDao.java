/**
 * 
 */
package com.promineo.color.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import com.promineo.color.entity.Color;
import com.promineo.color.entity.ColorBrand;
import com.promineo.color.entity.Customer;
import com.promineo.color.entity.Option;
import com.promineo.color.entity.Order;


/**
 * @author pbuda
 *
 */
public interface ColorOrderDao {

  /**
   * @param customer
   * @return
   */
  Optional <Customer> fetchCustomer(@NotNull String customer);

  /**
   * @param brand
   * @param type
   * @return
   */
  Optional <Color> fetchBrand(@NotNull ColorBrand brand, @NotNull String type);

  /**
   * @param customer
   * @param color
   * @param price
   * @param options 
   * @return
   */
  Order saveOrder(Customer customer, Color color, BigDecimal price, List<Option> options);

  /**
   * @param options
   * @return
   */
  List<Option> fetchOptions(List<String> optionsId);

  /**
   * @param optionId
   * @return
   */
  boolean deleteOption(String optionId);

  
  
  boolean updateOption(Option option);

  /**
   * @param orderRequest
   * @return
   */


 
}
