Card Vault Card Vault
A minimal Java/Spring Boot app to add and search cardholder records securely.

- Stores card numbers and encrypted

- Exposes simple REST endpoints.

- Includes a basic HTML page with modals to add/search.

- Uses H2 database

Features

- Add card: cardholderName + PAN

- Search by last 4 digits

- Return: name, masked PAN, created time

- Never logs or stores plaintext PAN

- One-file frontend with “Add” & “Search” popups

Tech Stack

Java 17+ (or 21)

Spring Boot 3.5.6

H2 

Maven

How To Run:

0) Prereqs

Java 17+ (21 recommended) → java -version

Maven → mvn -v

(Optional) Git if you’re cloning → git --version

1) Get the code
# clone or cd into your project folder
git clone https://github.com/<you>/CodingAssessmentNeurocom.git
cd CodingAssessmentNeurocom

2) (Optional, recommended) Set AES key

This keeps the same encryption key across restarts.

# macOS/Linux
export CARD_AES_KEY_BASE64=$(openssl rand -base64 32)

# Windows PowerShell
setx CARD_AES_KEY_BASE64 ([Convert]::ToBase64String((1..32 | % {Get-Random -Max 256})))

3) Run the app
mvn spring-boot:run


You should see logs like: Tomcat started on port(s): 8080.

4) Open the UI

App: http://localhost:8080/

You’ll see the simple HTML page with “Add Card” and “Search” modals.

5) (Optional) Use the H2 database console

H2 Console: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:file:./data/cardvault

User: sa | Password: (leave empty)

Click Connect to view the CARDS table.

6) Quick API checks (optional)
# Add a card
curl -X POST http://localhost:8080/api/cards \
  -H "Content-Type: application/json" \
  -d '{"cardholderName":"Ada Lovelace","pan":"4111111111111234"}'

# Search by last 4
curl "http://localhost:8080/api/cards?last4=1234"

7) Build a runnable JAR (optional)
mvn clean package
java -jar target/*.jar


