public class Configuration {
    private int totalTickets;
    private int customerRate;
    private int vendorRate;
    private int ticketPoolCapacity;
    private int numVendors;

    public Configuration(int totalTickets, int customerRate, int vendorRate, int ticketPoolCapacity, int numVendors) {
        this.totalTickets = totalTickets;
        this.customerRate = customerRate;
        this.vendorRate = vendorRate;
        this.ticketPoolCapacity = ticketPoolCapacity;
        this.numVendors = numVendors;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public int getCustomerRate() {
        return customerRate;
    }

    public int getVendorRate() {
        return vendorRate;
    }

    public int getTicketPoolCapacity() {
        return ticketPoolCapacity;
    }

    public int getNumVendors() {
        return numVendors;
    }
}
