package com.example.demo.dto;

import java.time.Instant;
// Basic display information
public record CardResponse(
  String cardholderName,
  String maskedPan,
  Instant createdTime
) {}