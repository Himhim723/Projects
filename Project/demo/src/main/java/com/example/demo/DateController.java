package com.example.demo;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1")
public class DateController {

  @Autowired
  private DateRepository dateRepository;

  @PostMapping(value = "/data")
  public LocalDate add(){
    return dateRepository.save(new People()).getDate();
  }

  @PostMapping(value = "/date")
  public LocalDate add(@RequestBody People date){
    return dateRepository.save(date).getDate();
  }
}
