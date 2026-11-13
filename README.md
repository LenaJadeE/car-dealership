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

## Classes

- `Vehicle` holds the data for one vehicle. The price is a `BigDecimal`.
- `Dealership` holds the dealership info and an `ArrayList` of vehicles, with the search, add, and remove methods.
- `DealershipFileManager` reads `inventory.csv` into a `Dealership` and writes it back.
- `UserInterface` shows the menu, reads what the user types, and calls the dealership.
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