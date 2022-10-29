/**
 * 
 */
package com.promineo.color.test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import com.promineo.color.entity.Color;
import com.promineo.color.entity.ColorBrand;
import com.promineo.color.test.support.FetchColorSupport;

/**
 * @author pbuda
 *
 */
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql(scripts = {
    "classpath:flyway/migrations/Script-4.sql",
    "classpath:flyway/migrations/Script-3.sql"},
    config = @SqlConfig(encoding = "UTF-8"))


class FetchColorTest extends FetchColorSupport{

  @Test
  void testDb() {   
  }

  @Test
  void testThatAnErrorMessageIsReturnedWhenAValidTypeIsSupplied() {
    // Given: a valid brand, type and URI
          ColorBrand brand = ColorBrand.NEVSKAYA_PALITRA;
          String type = "Invalid Value";
          String uri = String.format("%s?brand=%s&type=%s",
             getBaseUriForColors(), brand, type);
          
    // When: a connection is made to the URI                 
          ResponseEntity<?> response = 
              getRestTemplate().exchange(uri, HttpMethod.GET, null, 
                  new ParameterizedTypeReference<>() {});        

          
    // Then: a not found (404) status code is returned
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
       
    // And: the actual list returned is the same as the expected list
       
  }
 

  
  
}
