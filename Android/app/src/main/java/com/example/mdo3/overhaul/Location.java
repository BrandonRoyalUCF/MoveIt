package com.example.mdo3.overhaul;

/**
 * Created by Royal on 3/26/2018.
 */

public class Location {

    private int transactionId;
    private String startAddress;
    private String endAddress;
    private boolean isCompleted;

    public Location(int IdTransaction, String StartAddress, String EndAddress, boolean IsCompleted)
    {
        this.transactionId = IdTransaction;
        this.startAddress = StartAddress;
        this.endAddress = EndAddress;
        this.isCompleted = IsCompleted;
    }

    public String getStartAddress() { return this.startAddress; }
    public String getEndAddress() { return this.endAddress; }
    public boolean getIsCompleted(){ return this.isCompleted; }

}
