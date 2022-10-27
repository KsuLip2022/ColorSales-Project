/**
 * 
 */
package com.promineo.color.service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.promineo.color.dao.ColorSalesDao;
import com.promineo.color.entity.Color;
import com.promineo.color.entity.ColorBrand;
import lombok.extern.slf4j.Slf4j;

/**
 * @author pbuda
 *
 */
@Service
@Slf4j

public class DefaultColorSalesService implements ColorSalesService {
  
  @Autowired
  private ColorSalesDao colorSalesDao;
  
  
  @Transactional(readOnly = true)
  @Override
  public List<Color> fetchColors(ColorBrand brand, String type) {
  log.info("The fetchColors method was called with brand = {} and type = {}",
      brand, type);
  
  List<Color> colors = colorSalesDao.fetchColors(brand, type);
  
  if(colors.isEmpty()) {
    String msg = String.format("No colors found with brand = %s, type = %s", 
        brand, type);
    
    throw new NoSuchElementException(msg);
  }
  
  Collections.sort(colors);
    return colors;
  }

}
 