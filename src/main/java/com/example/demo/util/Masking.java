package com.example.demo.util;

// class to mask last 4 digits
// created since masking is used in multiple places (formatting logic in one place)
public final class Masking 
{
  private Masking(){}
  // static function so can be accessed anywhere
  public static String maskedFromLast4(String last4){ return "**** **** " + last4; }
}