package com.agricultural.bean;

public class Sales {
    private int productId;
    private int totalSales;

    public Sales() {
    }

    public Sales(int productId, int totalSales) {
        this.productId = productId;
        this.totalSales = totalSales;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }
}
