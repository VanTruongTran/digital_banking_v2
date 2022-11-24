/**
 * purpose: quản lý giao dịch
 * date created: 29/9/2022
 * author: Van Truong
 * version: 1.0.0
 */

package digitalbanking.model;

import java.util.UUID;

public class Transaction {
    /*ATTRIBUTES*/
    private String id;
    private String accountNumber;
    private double amount;
    private String time;
    private boolean status;

    /*GETTERS & SETTERS*/
    public String getId() {
        return this.id;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public double getAmount() {
        return this.amount;
    }

    public String getTime() {
        return this.time;
    }

    public boolean getStatus() {
        return this.status;
    }

    /*CONSTRUCTORS*/
    public Transaction() {

    }

    public Transaction(String accountNumber, double amount, String time, boolean status) {
        this.id = String.valueOf(UUID.randomUUID());
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.time = time;
        this.status = status;
    }

    /*SERVICE METHODS*/
    //phương thức trả về thông tin giao dịch
    @Override
    public String toString() {
        return String.format("[GD] %7s | %,-16.2fđ | %21s", this.accountNumber, this.amount, this.time);
    }
}
