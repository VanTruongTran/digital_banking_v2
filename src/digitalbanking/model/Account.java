/**
 * purpose: class quản lý thông tin tài khoản khách hàng
 * date created: 29/9/2022
 * author: Van Truong
 * version: 1.0.0
 */

package digitalbanking.model;

import digitalbanking.io.DateTimeCreator;

import java.util.ArrayList;
import java.util.Scanner;

public class Account {
    /*ATTRIBUTES*/
    protected String accountNumber;
    protected double balance;
    protected ArrayList<Transaction> transactionsList;

    /*GETTERS & SETTERS*/
    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        if (validateAccount(accountNumber)) {
            this.accountNumber = accountNumber;
        }
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        if (balance >= 50_000) {
            this.balance = balance;
        }
    }

    public ArrayList<Transaction> getTransactionsList() {
        return this.transactionsList;
    }

    /*CONSTRUCTORS*/
    public Account() {
        this.transactionsList = new ArrayList<Transaction>();
    }

    public Account(String accountNumber) {
        if (validateAccount(accountNumber)) {
            this.accountNumber = accountNumber;
        }
        this.transactionsList = new ArrayList<Transaction>();
    }

    public Account(String accountNumber, double balance) {
        if (validateAccount(accountNumber)) {
            this.accountNumber = accountNumber;
        }
        if (balance >= 50_000) {
            this.balance = balance;
        }
        this.transactionsList = new ArrayList<Transaction>();
    }

    /*SERVICE METHODS*/
    //phương thức nhập dữ liệu
    public void input(Scanner scanner) {
        do {
            System.out.print("Nhập số tài khoản gồm 6 chữ số: ");
            this.accountNumber = scanner.nextLine();
        } while (!validateAccount(this.accountNumber));
        do {
            System.out.print("Nhập số dư tài khoản >= 50000đ: ");
            this.balance = Double.parseDouble(scanner.nextLine());
        } while (this.balance < 50_000);

        createTransaction(this.accountNumber, this.balance, DateTimeCreator.getDateTime(), true);//tạo giao dịch đầu tiên
    }

    //phương thức trả về chuỗi thông tin tài khoản
    @Override
    public String toString() {
        return String.format("%-6s | %,40.2fđ", this.accountNumber, this.balance);
    }

    //phương thức kiểm tra tài khoản có phải là premium hay không
    public boolean isAccountPremium() {
        return this.balance >= 10_000_000;
    }

    //phương thức kiểm tra số tài khoản hợp lệ
    public boolean validateAccount(String accountNumber) {
        if (accountNumber.length() != 6) {//kiểm tra kích thước chuỗi = 6
            return false;
        }

        for (int i = 0; i < accountNumber.length(); i++) {//kiểm tra chuỗi có phải chuỗi số
            try {
                Integer.parseInt(accountNumber.substring(i, i + 1));
            } catch (Exception error) {
                return false;//chuỗi có một ký tự không phải số nguyên
            }
        }
        return true;
    }

    //phương thức tạo giao dịch
    public void createTransaction(String accountNumber, double amount, String time, boolean status) {
        Transaction transaction = new Transaction(accountNumber, amount, time, status);
        this.transactionsList.add(transaction);
    }

    //phương thức hiển thị lịch sử giao dịch
    public void displayTransactionsList() {
        for (Transaction transaction : this.transactionsList) {
            System.out.println(transaction.toString());
        }
    }
}
