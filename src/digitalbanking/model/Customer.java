/**
 * purpose: class quản lý thông tin khách hàng
 * date created: 29/9/2022
 * author: Van Truong
 * version: 1.0.0
 */

package digitalbanking.model;

import digitalbanking.io.CustomerIdValidator;

import java.util.ArrayList;
import java.util.Scanner;

public class Customer extends User {
    /*ATTRIBUTES*/
    protected ArrayList<Account> accountsList;

    /*GETTERS & SETTERS*/
    public ArrayList<Account> getAccountsList() {
        return this.accountsList;
    }

    /*CONSTRUCTORS*/
    public Customer() {
        super();
        this.accountsList = new ArrayList<Account>();
    }

    public Customer(String name, String customerId) {
        super(name, customerId);
        this.accountsList = new ArrayList<Account>();
    }

    /*SERVICE METHODS*/
    //phương thức nhập dữ liệu
    public void input(Scanner scanner) {
        System.out.print("Nhập tên khách hàng: ");
        this.name = scanner.nextLine();
        do {
            System.out.print("Nhập số CCCD: ");
            this.customerId = scanner.nextLine();
        } while (!CustomerIdValidator.validateCustomerId(this.customerId));
    }

    //phương thức trả về chuỗi thông tin khách hàng
    @Override
    public String toString() {
        return String.format("%-12s | %-7s | %-7s | %,20.2fđ", this.customerId, this.name, isCustomerPremium() ? "Premium" : "Normal", getTotalAccountBalance());
    }

    //phương thức hiển thị chi tiết thông tin khách hàng
    public void displayInformation() {
        System.out.println(toString());

        for (int i = 0; i < this.accountsList.size(); i++) {
            System.out.printf("%-5d %s\n", i + 1, this.accountsList.get(i).toString());
        }
    }

    //phương thức hiển thị chi tiết thông tin khách hàng có giao dịch
    public void displayTransactionInformation() {
        displayInformation();

        if (!this.accountsList.isEmpty()) {//có tài khoản trong danh sách
            for (Account account : this.accountsList) {
                account.displayTransactionsList();
            }
        } else {//không có tài khoản trong danh sách
            System.out.println("Khách hàng không có tài khoản nào, thao tác không thành công");
        }
    }

    //phương thức tính tổng số dư tất cả tài khoản của khách hàng
    public double getTotalAccountBalance() {
        double totalAccountBalance = 0;
        for (Account account : this.accountsList) {
            totalAccountBalance += account.getBalance();
        }
        return totalAccountBalance;
    }

    //phương thức thêm tài khoản vào danh sách tài khoản của khách hàng
    public void addAccount(Account newAccount) {
        this.accountsList.add(newAccount);

        if (newAccount instanceof SavingsAccount) {
            System.out.printf("Đã thêm tài khoản ATM %s vào danh sách tài khoản của khách hàng %s\n", newAccount.getAccountNumber(), this.customerId);
        } else if (newAccount instanceof LoansAccount) {
            System.out.printf("Đã thêm tài khoản tín dụng %s vào danh sách tài khoản của khách hàng %s\n", newAccount.getAccountNumber(), this.customerId);
        } else {
            System.out.printf("Đã thêm tài khoản %s vào danh sách tài khoản của khách hàng %s\n", newAccount.getAccountNumber(), this.customerId);
        }
    }

    //phương thức kiểm tra tài khoản đã tồn tại trong danh sách tài khoản của khách hàng hay chưa
    public boolean isAccountExisted(Account newAccount) {
        for (Account account : this.accountsList) {
            if (account.getAccountNumber().equals(newAccount.getAccountNumber())) {
                return true;//STK của tài khoản mới trùng với STK của một tài khoản trong danh sách tài khoản
            }
        }
        return false;
    }

    //phương thức kiểm tra khách hàng có phải là premium hay không
    public boolean isCustomerPremium() {
        for (Account account : this.accountsList) {
            if (account.isAccountPremium()) {
                return true;//khách hàng có ít nhất 1 tài khoản là premium
            }
        }
        return false;
    }

    //phương thức trả về tài khoản theo STK
    public Account getAccountByAccountNumber(String accountNumber) {
        for (Account account : this.accountsList) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;//tìm thấy tài khoản
            }
        }
        return null;
    }
}
