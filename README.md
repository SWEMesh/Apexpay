#  Mini ApexPayCLI — Core P2P Wallet & Ledger Engine  

[![Java](https://img.shields.io/badge/Java-21%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Architecture](https://img.shields.io/badge/Architecture-Clean%20OOP%20%2F%20Domain--Driven-blue?style=for-the-badge)](https://en.wikipedia.org/wiki/Domain-driven_design)
[![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)](LICENSE)

An in memory financial ledger and peer to peer (P2P) transaction engine built in **modern Java 21**. Inspired by real world digital payment platforms (such as **STC Pay** and **Apple Cash**), ApexPay enforces strict atomic balance mutations, fail-fast financial guard clauses, and an immutable audit trail.

---

## 📌 Architecture & Design Highlights

* **Atomic P2P Money Transfers:** Implements a two phase balance mutation pattern (sender deduction $\rightarrow$ recipient credit) guarded by comprehensive solvency and account status checks.
* **$O(1)$ High Throughput User Lookups:** Core service boundary uses hash based mapping (`Map<String, User>`) ensuring constant time access during high volume transaction processing.
* **Immutable Audit Ledger:** All transaction attempts both successful transfers and rejected operations—are appended to an immutable chronological audit trail (`List<Transaction>`) for compliance and debugging.
* **Fail-Fast Exception Architecture:** Eliminates silent failures by defining a strongly typed domain exception hierarchy (`AccountNotActiveException`, `InsufficientBalanceException`).
* **Strict State Lifecycles:** Account statuses (`ACTIVE`, `FROZEN`, `CLOSED`) and transaction outcomes (`SUCCESS`, `FAILED`) are enforced via strongly typed Java enums.

---

##  System Architecture

```text
       ┌────────────────────────┐
       │      Main (Runner)     │
       └───────────┬────────────┘
                   │ invokes
                   ▼
       ┌────────────────────────┐
       │     WalletService      │ ── manages ──► [ User Registry: Map<String, User> ]
       └───────────┬────────────┘ ── appends ──► [ Audit Ledger: List<Transaction> ]
                   │
         ┌─────────┴─────────┐
         ▼                   ▼
   ┌───────────┐       ┌───────────┐
   │   User    │       │Transaction│
   │  (Entity) │       │  (Record) │
   └─────┬─────┘       └─────┬─────┘
         │ uses              │ uses
         ▼                   ▼
   [AccountStatus]     [TransactionStatus]



Project Directory Structure

ApexPayCLI/
├── src/
│   ├── exception/
│   │   ├── AccountNotActiveException.java
│   │   └── InsufficientBalanceException.java
│   ├── model/
│   │   ├── AccountStatus.java
│   │   ├── Transaction.java
│   │   ├── TransactionStatus.java
│   │   └── User.java
│   ├── service/
│   │   └── WalletService.java
│   └── Main.java
└── README.md

Compilation & Execution

Clone the repository:

bash
git clone https://github.com/SWEMesh/ApexPayCLI.git
cd ApexPayCLI

Compile all Java source files:

bash
javac -d bin src/**/*.java src/*.java

Run the integration test runner:

bash
java -cp bin Main



Future Roadmap
 Transition from CLI runner to a Spring Boot 3 RESTful API.
 Replace in memory structures with persistent PostgreSQL schema using Hibernate / Spring Data JPA.
 Implement pessimistic/optimistic database locking (SELECT ... FOR UPDATE) to eliminate race conditions under concurrent workloads.
 Secure API endpoints using JWT authentication and Spring Security.
