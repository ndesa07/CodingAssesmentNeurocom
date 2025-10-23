# Card Vault 
A minimal Java/Spring Boot app to add and search cardholder records securely.

- Stores card numbers and encrypted

- Exposes simple REST endpoints.

- Includes a basic HTML page with modals to add/search.

- Uses H2 database

# Features

- Add card: cardholderName + PAN

- Search by last 4 digits

- Return: name, masked PAN, created time

- Never logs or stores plaintext PAN

- One-file frontend with “Add” & “Search” popups

# Tech Stack

- Java 17+ (or 21)

- Spring Boot 3.5.6

- H2 

- Maven

# How To Run:
-----------------------------------
# Prereqs

Java 17+ (21 recommended) → java -version

Maven → mvn -v

Git if you’re cloning → git --version

# 1) Get the code
git clone https://github.com/<you>/CodingAssessmentNeurocom.git
cd CodingAssessmentNeurocom

# 2) Run the app
mvn spring-boot:run


You should see logs like: Tomcat started on port(s): 8080.

# 3) Open the UI

App: http://localhost:8080/

You’ll see the simple HTML page with “Add Card” and “Search” modals.

# Database Structure

For this project I’m using a single-table design:

Table: cards

Columns:

card_number – mandatory and unique (ensures the same card isn’t stored twice)

cardholder_name – mandatory

created_at – timestamp, unique, records when the card was added

Cardholder-to-card relation: one cardholder can have multiple cards.

DDL (example):

CREATE TABLE cards (
  card_number    VARCHAR(19) NOT NULL UNIQUE,
  cardholder_name VARCHAR(100) NOT NULL,
  created_at      TIMESTAMP NOT NULL UNIQUE
);

