package com.example.demo.controller;
import com.example.demo.Service.CardService;
import com.example.demo.dto.AddCardRequest;
import com.example.demo.dto.CardResponse;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


// Sends teh information from the user to the service class
@RestController
@RequestMapping("/api/cards")
public class CardApiController 
{
  private final CardService service;
  public CardApiController(CardService service){ this.service = service; }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CardResponse add(@Valid @RequestBody AddCardRequest req) throws Exception 
  {
    return service.add(req);
  }

  @GetMapping
  public List<CardResponse> search(@RequestParam("last4") String last4) 
  {
    if (!last4.matches("\\d{4}")) throw new IllegalArgumentException("last4 must be 4 digits");
    return service.searchByLast4(last4);
  }
}
