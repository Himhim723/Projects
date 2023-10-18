package com.hkjava.demo.demofinnhub.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.hkjava.demo.demofinnhub.exception.FinnhubException;
import com.hkjava.demo.demofinnhub.infra.ApiResp;
import com.hkjava.demo.demofinnhub.model.dto.web.req.SymbolReqDTO;
import com.hkjava.demo.demofinnhub.model.dto.web.resp.StockDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Validated
public interface StockOperation {

  @Operation(summary = "Get Finnhub Stock Data",
      description = "This endpoint retrieves a stock data from Finnhub Endpoints (/stock).",
      tags = "Get a Stock",
      parameters = {@Parameter(name = "SymbolReqDTO",
          description = "Stock Symbol", required = true)})
  @ApiResponses({
      @ApiResponse(responseCode = "200",
          content = {@Content(schema = @Schema(implementation = StockDTO.class),
              mediaType = "application/json")}),
      @ApiResponse(responseCode = "404",
          content = {@Content(schema = @Schema())}),
      @ApiResponse(responseCode = "500",
          content = {@Content(schema = @Schema())})})
  @GetMapping(value = "/stock")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResp<StockDTO> stockInfo(
      // @SymbolCheck
      @RequestParam("symbol") SymbolReqDTO symbol) throws FinnhubException;

  // @GetMapping(value = "/stockfromdb")
  // @ResponseStatus(value = HttpStatus.OK)
  // ApiResponse<StockDTO> stockInfoFromDb(@RequestParam("symbol") String symbol)
  // throws FinnhubException;

}
