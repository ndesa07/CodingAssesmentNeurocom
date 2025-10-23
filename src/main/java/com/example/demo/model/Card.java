package com.example.demo.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
//Basic card model
@Entity
@Table(name = "cards", indexes = @Index(name = "idx_last4", columnList = "panLast4"))
public class Card {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 100)
    private String cardHolderName;

    // PAN ecrypted holder, dont show PAN number
    @Lob
    @Column(nullable = false)
    private byte[] panEnc;

    // The encryption code
    @Lob
    @Column(nullable = false)
    private byte[] panIv;

    @Column(nullable = false, length = 4)
    private String panLast4;

    private Instant createdAt = Instant.now();

    // Getters & Setters
    public UUID getId() 
    { 
        return id; 
    }

    public String getCardHolderName() { return cardHolderName; }
    public void setCardHolderName(String cardHolderName) 
    { 
        this.cardHolderName = cardHolderName; 
    }
    public byte[] getPanEnc() 
    { 
        return panEnc; 
    }
    public void setPanEnc(byte[] panEnc) 
    { 
        this.panEnc = panEnc; 
    }
    public byte[] getPanIv() 
    { 
        return panIv; 
    }
    public void setPanIv(byte[] panIv) 
    { 
        this.panIv = panIv; 
    }
    public String getPanLast4() 
    { 
        return panLast4;
    }
    public void setPanLast4(String panLast4) 
    {
        this.panLast4 = panLast4; 
    }
    public Instant getCreatedAt() 
    {
        return createdAt; 
    }
    public void setCreatedAt(Instant createdAt) 
    { 
        this.createdAt = createdAt; 
    }
}
