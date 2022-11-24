/**
 * purpose: class quản lý thông tin khách hàng tiềm năng
 * date created: 29/9/2022
 * author: Van Truong
 * version: 1.0.0
 */

package digitalbanking.model;

import java.util.Scanner;

public class DigitalCustomer extends Customer {
    /*CONSTRUCTORS*/
    public DigitalCustomer() {
        super();
    }

    public DigitalCustomer(String name, String customerId) {
        super(name, customerId);
    }

    /*SERVICE METHODS*/
    //phương thức rút tiền theo tài khoản
    public void withdraw(String accountNumber, double amount) {
        if (!this.accountsList.isEmpty()) {//có tài khoản trong danh sách
            Account account = getAccountByAccountNumber(accountNumber);//tìm tài khoản theo STK
            if (account != null) {//tìm thấy tài khoản
                if (account instanceof SavingsAccount) {//tài khoản tiết kiệm
                    ((SavingsAccount) account).withdraw(amount);
                } else if (account instanceof LoansAccount) {//tài khoản tín dụng
                    ((LoansAccount) account).withdraw(amount);
                }
            } else {//không tìm thấy tài khoản
                System.out.printf("Không tìm thấy tài khoản %s trong danh sách khách hàng %s, rút tiền không thành công\n", accountNumber, this.customerId);
            }
        } else {//không có tài khoản trong danh sách
            System.out.println("Khách hàng không có tài khoản nào, thao tác không thành công");
        }
    }

    public void withdraw(Account account, double amount) {
        if (!this.accountsList.isEmpty()) {//có tài khoản trong danh sách
            if (isAccountExisted(account)) {//tìm thấy tài khoản
                if (account instanceof SavingsAccount) {//tài khoản tiết kiệm
                    ((SavingsAccount) account).withdraw(amount);
                } else if (account instanceof LoansAccount) {//tài khoản tín dụng
                    ((LoansAccount) account).withdraw(amount);
                }
            } else {//không tìm thấy tài khoản
                System.out.printf("Không tìm thấy tài khoản %s trong danh sách khách hàng %s, rút tiền không thành công\n", account.getAccountNumber(), this.customerId);
            }
        } else {//không có tài khoản trong danh sách
            System.out.println("Khách hàng không có tài khoản nào, thao tác không thành công");
        }
    }

    public void withdraw(Scanner scanner) {
        if (!this.accountsList.isEmpty()) {//có tài khoản trong danh sách
            Account account;
            double amount;
            do {
                System.out.print("Nhập số tài khoản: ");
                account = getAccountByAccountNumber(scanner.nextLine());
            } while (account == null);
            do {
                System.out.print("Nhập số tiền rút: ");
                amount = Double.parseDouble(scanner.nextLine());
            } while (amount <= 0);

            if (account instanceof SavingsAccount) {//tài khoản tiết kiệm
                ((SavingsAccount) account).withdraw(amount);
            } else if (account instanceof LoansAccount) {//tài khoản tín dụng
                ((LoansAccount) account).withdraw(amount);
            }
        } else {//không có tài khoản trong danh sách
            System.out.println("Khách hàng không có tài khoản nào, thao tác không thành công");
        }
    }
}
