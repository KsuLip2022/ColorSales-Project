/**
 * 
 */
package com.promineo.color.dao;

import java.util.List;
import com.promineo.color.entity.Color;
import com.promineo.color.entity.ColorBrand;

/**
 * @author pbuda
 *
 */
public interface ColorSalesDao {

  /**
   * @param brand
   * @param type
   * @return
   */
  List<Color> fetchColors(ColorBrand brand, String type);

}
