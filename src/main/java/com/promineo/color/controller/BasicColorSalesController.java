/**
 * 
 */
package com.promineo.color.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineo.color.entity.Color;
import com.promineo.color.entity.ColorBrand;
import com.promineo.color.service.ColorSalesService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author pbuda
 *
 */
@Slf4j
@RestController
public class BasicColorSalesController implements ColorSalesController {
  
  @Autowired
  private ColorSalesService colorSalesSeevice;

  @Override
  public List<Color> fetchColors(ColorBrand brand, String type) {
   log.debug("brand = {}, type = {}", brand, type);
    return colorSalesSeevice.fetchColors(brand, type);
  }

}
