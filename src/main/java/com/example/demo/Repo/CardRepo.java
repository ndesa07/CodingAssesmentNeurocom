package com.example.demo.Repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Card;

import java.util.*;
//Interface to call in card service to find all cards with same last 4
public interface CardRepo extends JpaRepository<Card, UUID>
{
    List<Card> findByPanLast4(String last4);
}
