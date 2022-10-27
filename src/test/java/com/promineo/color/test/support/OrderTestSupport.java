/**
 * 
 */
package com.promineo.color.test.support;

/**
 * @author pbuda
 *
 */
public class OrderTestSupport extends BaseTest{
  protected String createOrderBody() {
    // @formatter: off
    return "{\n"
        + " \"customer\":\"PETROV_ALEX\",\n" 
        + " \"brand\":\"NEVSKAYA_PALITRA\",\n"
        + " \"type\":\"Watercolor\",\n"
        + " \"options\":[\n"
        + " \"TABLE_COVER_DISPOSABLE_SET\",\n"
        + " \"KOLINSKY_ART_SET\",\n"
        + " \"WATERCALOR_SET_60\",\n"
        + " \"BESTOP_TRUNK\",\n"
        + " \"PIGMENT_DARK_BLUE\"\n"
        + " ]\n"
        + "}";
    // @formatter: on
  }

}
