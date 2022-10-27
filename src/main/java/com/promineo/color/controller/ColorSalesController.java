/**
 * 
 */
package com.promineo.color.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineo.color.entity.Color;
import com.promineo.color.entity.ColorBrand;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * @author pbuda
 *
 */
@RequestMapping("/colors")
@OpenAPIDefinition(info = @Info(title = "Color Sales Service"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})
public interface ColorSalesController {
  
    //formatter: off
  @Operation(
      summary = "Returns a list of Colors",
      description = "Return a list of Colors given an optional brand and/or type",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "A list of Colors is returned",
              content = @Content(
                 mediaType = "application/json",
                 schema = @Schema(implementation = Color.class))),
          @ApiResponse(responseCode = "400", description = "The request parametrs are invalid", 
              content = @Content(
              mediaType = "application/json")),
          @ApiResponse(responseCode = "404", description = "No Colors were found with the input criteria", 
              content = @Content(
              mediaType = "application/json")),
          @ApiResponse(responseCode = "500", description = "An unplanned error occured",  
              content = @Content(
              mediaType = "application/json"))
              },
      parameters = {
          @Parameter(
              name = "brand",
              allowEmptyValue = false,
              required = false,
              description = "The brand name (i.e., 'NEVSKAYA_PALITRA')"),
          @Parameter(
              name = "type",
              allowEmptyValue = false,
              required = false,
              description = "The type name (i.e., 'Watercolor')"),          
      }
    )
    
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
    List<Color> fetchColors(
        @RequestParam(required = false)
        ColorBrand brand, 
        @RequestParam(required = false)
        String type);
  //formatter: on
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}
