import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TicketingSystemCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalTickets = 0;
        int ticketPoolCapacity = 0;
        int totalTicketsInPool = 0;
        boolean continueProcessing = true;
        String fileName = "config.txt";

        while (continueProcessing) {
            // Get total tickets and ticket pool capacity only if it's the first iteration
            if (totalTickets == 0) {
                totalTickets = getValidatedIntegerInput(scanner, "Enter the total number of tickets: ");
                ticketPoolCapacity = getValidatedIntegerInput(scanner, "Enter the ticket pool capacity: ");
            }

            // Get other inputs
            int customerRate = getValidatedIntegerInput(scanner, "Enter the customer rate (tickets per millisecond): ");
            int vendorRate = getValidatedIntegerInput(scanner, "Enter the vendor rate (tickets added to pool per millisecond): ");
            int numVendors = getValidatedIntegerInput(scanner, "Enter the number of vendors: ");

            TicketPool ticketPool = new TicketPool(ticketPoolCapacity);
            Vendor vendor = new Vendor(vendorRate, numVendors);
            Customer customer = new Customer(customerRate);

            int remainingTickets = totalTicketsInPool != 0 ? totalTicketsInPool : totalTickets;
            totalTicketsInPool = remainingTickets;
            int time = 1;

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                // Write the header to the file
                writer.write("Ticketing System History\n");
                writer.write("Total tickets: " + totalTickets + "\n");
                writer.write("Ticket pool capacity: " + ticketPool.getTicketPoolCapacity() + "\n");
                writer.write("--------------------------------------------------\n");

                System.out.println("Starting ticket processing...");
                System.out.println("Total tickets: " + totalTickets);
                System.out.println("Ticket pool capacity: " + ticketPool.getTicketPoolCapacity());

                // Ticket processing loop
                while (remainingTickets > 0) {
                    int ticketsAddedByVendors = vendor.addTicketsToPool();
                    remainingTickets -= customer.getTicketsPerMillisecond();
                    totalTicketsInPool = remainingTickets + ticketsAddedByVendors;

                    if (totalTicketsInPool > ticketPool.getTicketPoolCapacity()) {
                        System.out.println("Error: Total tickets exceed pool capacity!");
                        break;
                    }

                    String statusMessage = "Time: " + time + "ms";
                    if (remainingTickets >= 0) {
                        statusMessage += " | Remaining tickets: " + remainingTickets;
                    }
                    statusMessage += " | Tickets added by vendors: " + ticketsAddedByVendors +
                            " | Total tickets in pool: " + totalTicketsInPool;

                    System.out.println(statusMessage);
                    writer.write(statusMessage + "\n");
                    time++;
                }

                if (remainingTickets <= 0) {
                    System.out.println("No remaining tickets. Stopping...");
                }

                System.out.println("History has been saved to config.txt.");
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }

            // Ask if the user wants to do another configuration
            if (!getYesNoInput(scanner, "Do you want to do another configuration? (yes/no): ")) {
                System.out.println("Goodbye!");
                continueProcessing = false;
            } else {
                System.out.println("Reusing the last configuration...");
            }
        }

        scanner.close();
    }

    // Utility method for validating integer input
    private static int getValidatedIntegerInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Error: Please enter a valid integer.");
                scanner.next(); // Consume the invalid input
            }
        }
    }

    // Utility method for validating yes/no input
    private static boolean getYesNoInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.next().toLowerCase();
            if (input.equals("yes")) {
                return true;
            } else if (input.equals("no")) {
                return false;
            } else {
                System.out.println("Error: Please enter 'yes' or 'no'.");
            }
        }
    }
}
