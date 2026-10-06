# Ticketing System CLI

A simple Java command-line app where vendors add tickets to a pool and customers buy them.

## Run

1. Open the project in IntelliJ IDEA
2. Run `TicketingSystemCLI`
3. Enter the values it asks for:
   - Total tickets
   - Ticket pool capacity
   - Customer rate
   - Vendor rate
   - Number of vendors

The run history is saved to `config.txt`.

## Classes

- `TicketingSystemCLI` - main program
- `Configuration` - stores settings
- `TicketPool` - shared ticket pool
- `Vendor` - adds tickets
- `Customer` - buys tickets
