package com.razanpardazesh.onlinestore.data;

/**
 * Created by Torabi on 9/17/2016.
 */

public class BasketStatistics {
    private double totalCounts = 0d;
    private double totalPrices = 0d;

    public double getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(double totalCounts) {
        this.totalCounts = totalCounts;
    }

    public double getTotalPrices() {
        return totalPrices;
    }

    public void setTotalPrices(double totalPrices) {
        this.totalPrices = totalPrices;
    }
}
