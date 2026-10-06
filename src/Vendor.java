public class Vendor {
    private int ticketsAddedPerMillisecond;
    private int numVendors;

    public Vendor(int ticketsAddedPerMillisecond, int numVendors) {
        this.ticketsAddedPerMillisecond = ticketsAddedPerMillisecond;
        this.numVendors = numVendors;
    }

    public int addTicketsToPool() {
        return ticketsAddedPerMillisecond * numVendors;
    }
}
