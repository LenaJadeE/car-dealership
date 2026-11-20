# Car Dealership

A console application for a used car dealership sales manager. It loads the dealership and its vehicles from inventory.csv, lets the user search the inventory, and saves the file every time a vehicle is added or removed.

## Menu

| Command | What it does |
|---|---|
| 1 | Find vehicles within a price range |
| 2 | Find vehicles by make / model |
| 3 | Find vehicles by year range |
| 4 | Find vehicles by color |
| 5 | Find vehicles by mileage range |
| 6 | Find vehicles by type (car, truck, SUV, van) |
| 7 | List all vehicles |
| 8 | Add a vehicle |
| 9 | Remove a vehicle |
| 10 | Search by make, maximum price and color (leave any answer blank to skip that filter) |
| 99 | Quit |

## Selling or leasing a vehicle

Command 11 asks for the VIN of the vehicle, then the customer's name and email, and whether it is a sale or a lease.

- **Sale:** 5% sales tax, a $100 recording fee, and a processing fee of $295 for vehicles under $10,000 or $495 for all others. The customer can finance it. Financed loans are 4.25% for 48 months if the vehicle costs $10,000 or more, and 5.25% for 24 months if it costs less.
- **Lease:** the expected ending value is 50% of the price and the lease fee is 7%. The total is the two added together, financed at 4.0% for 36 months. A vehicle over 3 years old can't be leased.

After it shows the total price and the monthly payment, the app saves the contract to `contracts.csv` and removes the vehicle from `inventory.csv`.

## Project structure

The code is in `com.pluralsight.dealershipmanager`, sorted by the job each class does.

```text
car-dealership
├── pom.xml
├── contracts.csv
└── src
    ├── main/java/com/pluralsight/dealershipmanager
    │   ├── Program.java
    │   ├── model
    │   │   ├── Vehicle.java
    │   │   ├── Dealership.java
    │   │   ├── Contract.java
    │   │   ├── SalesContract.java
    │   │   ├── LeaseContract.java
    │   │   └── LoanCalculator.java
    │   ├── data
    │   │   ├── DealershipFileManager.java
    │   │   └── ContractDataManager.java
    │   └── ui
    │       └── UserInterface.java
    └── test/java/com/pluralsight
        └── DealershipTest.java
```

## Classes

- `model` holds the things:
    - `Vehicle` holds the data for one vehicle. The price is a `BigDecimal`.
    - `Dealership` holds the dealership info and an `ArrayList` of vehicles, with the search, add and remove methods.
    - `Contract` is an abstract class for what every contract has: the date, the customer's name and email, the vehicle, and the total price and monthly payment.
    - `SalesContract` and `LeaseContract` extend `Contract` and work out their own total and monthly payment.
    - `LoanCalculator` works out a monthly loan payment.
- `data` holds the files:
    - `DealershipFileManager` reads `inventory.csv` into a `Dealership` and writes it back.
    - `ContractDataManager` adds one line for each contract to the end of `contracts.csv`.
- `ui` holds the screen:
    - `UserInterface` shows the menu, reads what the user types, and calls the dealership. It is the only class that prints or reads input.
- `Program` starts the application.

## Inventory file

`inventory.csv` is pipe-delimited. The first line is the dealership, every other line is a vehicle:

```
D & B Used Cars|111 Old Benbrook Rd|817-555-5555
10112|1993|Ford|Explorer|SUV|Red|525123|995.00
```

Vehicle fields are `vin|year|make|model|type|color|odometer|price`. `inventory-backup.csv` is a copy of the starting data.

## Tests

`DealershipTest` has JUnit 6 tests for every `Dealership` search method, including the range limits, capital letters, empty results, and the multi-filter search. Run it from IntelliJ by right-clicking `DealershipTest` and choosing Run.

## Run it

Run `Program.main()` from IntelliJ with the project folder as the working directory, so `inventory.csv` is found.