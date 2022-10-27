/**
 * 
 */
package com.promineo.color;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.promineo.ComponentScanMarker;

/**
 * @author kdubrovskaya
 *
 */
@SpringBootApplication(scanBasePackageClasses = {ComponentScanMarker.class})
public class ColorSales { 

  /**
   * @param args
   */
  public static void main(String[] args) {
    SpringApplication.run(ColorSales.class, args);

  }

}
