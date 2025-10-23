package com.example.demo.dto;

import jakarta.validation.constraints.*;

// only allow digits between 12-19
public record AddCardRequest(
  @NotBlank String cardholderName,
  @Pattern(regexp="\\d{12,19}", message="PAN must be 12–19 digits") String pan
) {}