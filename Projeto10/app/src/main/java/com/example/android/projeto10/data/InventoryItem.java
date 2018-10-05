package com.example.android.projeto10.data;

/**
 * Created by Pedro on 09/02/2018.
 */

public class InventoryItem {
    private final String mProductTitle;
    private final String mPrice;
    private final int mQuantity;
    private final String mSupplierName;
    private final String mSupplierPhone;
    private final String mSupplierEmail;
    private final String mImage;

    public InventoryItem(String productTitle, String price, int quantity, String supplierName, String supplierPhone, String supplierEmail, String image) {
        mProductTitle = productTitle;
        mPrice = price;
        mQuantity = quantity;
        mSupplierName = supplierName;
        mSupplierPhone = supplierPhone;
        mSupplierEmail = supplierEmail;
        mImage = image;
    }

    public String getProductTitle() {
        return mProductTitle;
    }

    public String getPrice() {
        return mPrice;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public String getSupplierName() {
        return mSupplierName;
    }

    public String getSupplierPhone() {
        return mSupplierPhone;
    }

    public String getSupplierEmail() {
        return mSupplierEmail;
    }

    public String getImage() {
        return mImage;
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "product='" + mProductTitle + '\'' +
                ", mPrice='" + mPrice + '\'' +
                ", mQuantity=" + mQuantity +
                ", mSupplierName='" + mSupplierName + '\'' +
                ", mSupplierPhone='" + mSupplierPhone + '\'' +
                ", mSupplierEmail='" + mSupplierEmail + '\'' +
                '}';
    }
}
