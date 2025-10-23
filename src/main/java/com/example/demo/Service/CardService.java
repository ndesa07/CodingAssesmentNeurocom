package com.example.demo.Service;

import com.example.demo.Repo.CardRepo;
import com.example.demo.dto.AddCardRequest;
import com.example.demo.dto.CardResponse;
import com.example.demo.encryption.EncryptionService;
import com.example.demo.encryption.EncryptionService.EncResult;
import com.example.demo.model.Card;
import com.example.demo.util.Masking;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.util.List;

//Validate input
//Get last 4 digits
//Encrypt Pan
//Convert DB entities to API DTOs
@Service
public class CardService 
{
  private final CardRepo repo;
  private final EncryptionService crypto;
  public CardService(CardRepo repo, EncryptionService crypto) 
  {
    this.repo = repo; this.crypto = crypto;
  }

  @Transactional
  public CardResponse add(AddCardRequest req) throws Exception 
  {
    //get PAN number 
    var enc = crypto.encrypt(req.pan());
    // extract last 4 digits
    String last4 = req.pan().substring(req.pan().length() - 4);

    //initialize object card with values
    Card c = new Card();
    c.setCardHolderName(req.cardholderName());
    c.setPanEnc(enc.cipher());
    c.setPanIv(enc.iv());
    c.setPanLast4(last4);
    c.setCreatedAt(Instant.now());
    repo.save(c);

    // return card requirments into table
    return new CardResponse(c.getCardHolderName(), Masking.maskedFromLast4(last4), c.getCreatedAt());
  }

  //collecting list of PAN with same last 4 digits based on REPO class
  @Transactional(readOnly = true)
  public List<CardResponse> searchByLast4(String last4) 
  {
    return repo.findByPanLast4(last4).stream()
      .map(c -> new CardResponse(c.getCardHolderName(), Masking.maskedFromLast4(c.getPanLast4()), c.getCreatedAt()))
      .toList();
  }
}
