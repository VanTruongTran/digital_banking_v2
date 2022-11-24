/**
 * purpose: class quản lý thông tin ngân hàng tiềm năng
 * date created: 29/9/2022
 * author: Van Truong
 * version: 1.0.0
 */

package digitalbanking.model;

import digitalbanking.io.CustomerIdValidator;

import java.util.Scanner;

public class DigitalBank extends Bank {
    /*CONSTRUCTORS*/
    public DigitalBank() {
        super();
    }

    /*SERVICE METHODS*/
    //phương thức rút tiền theo tài khoản
    public void withdraw(String customerId, String accountNumber, double amount) {
        if (!this.customersList.isEmpty()) {//có khách hàng trong danh sách
            Customer customer = getCustomerById(customerId);
            if (customer != null) {//tìm thấy khách hàng
                ((DigitalCustomer) customer).withdraw(accountNumber, amount);
            } else {//không tìm thấy khách hàng
                System.out.printf("Không tìm thấy khách hàng %s, rút tiền không thành công\n", customerId);
            }
        } else {//không có khách hàng trong danh sách
            System.out.println("Ngân hàng không có khách hàng nào, thao tác không thành công");
        }
    }

    public void withdraw(Scanner scanner, String customerId) {
        if (!this.customersList.isEmpty()) {//có khách hàng trong danh sách
            Customer customer = getCustomerById(customerId);
            if (customer != null) {//tìm thấy khách hàng
                customer.displayInformation();//xuất danh sách tài khoản của khách hàng
                ((DigitalCustomer) customer).withdraw(scanner);
            } else {//không tìm thấy khách hàng
                System.out.printf("Không tìm thấy khách hàng %s, rút tiền không thành công\n", customerId);
            }
        } else {//không có khách hàng trong danh sách
            System.out.println("Ngân hàng không có khách hàng nào, thao tác không thành công");
        }
    }

    //phương thức thêm khách hàng vào danh sách khách hàng
    public void addCustomer(String customerId, String name) {
        if (CustomerIdValidator.validateCustomerId(customerId)) {//CCCD hợp lệ
            Customer customer = new Customer(name, customerId);
            if (!isCustomerExisted(customer)) {//khách hàng chưa tồn tại trong danh sách
                this.customersList.add(customer);
                System.out.printf("Đã thêm khách hàng %s vào danh sách khách hàng\n", customer.getCustomerId());
            } else {//khách hàng đã tồn tại trong danh sách
                System.out.printf("Khách hàng %s đã tồn tại, thêm khách hàng không thành công\n", customer.getCustomerId());
            }
        } else {//CCCD không hợp lệ
            System.out.println("Số CCCD không hợp lệ, thêm khách hàng không thành công");
        }
    }

    //phương thức trả về khách hàng theo id
    @Override
    public Customer getCustomerById(String customerId) {
        return super.getCustomerById(customerId);
    }

    //phương thức hiển thị thông tin khách hàng
    public void showCustomerInformation(String customerId) {
        if (!this.customersList.isEmpty()) {//có khách hàng trong danh sách
            Customer customer = getCustomerById(customerId);
            if (customer != null) {//tìm thấy khách hàng
                customer.displayInformation();
            } else {//không tìm thấy khách hàng
                System.out.printf("Không tìm thấy khách hàng %s, thao tác không thành công\n", customerId);
            }
        } else {//không có khách hàng trong danh sách
            System.out.println("Ngân hàng không có khách hàng nào, thao tác không thành công");
        }
    }

    //phương thức thêm tài khoản tiết kiệm cho khách hàng
    public void addSavingsAccount(Scanner scanner, String customerId) {
        if (!this.customersList.isEmpty()) {//có khách hàng trong danh sách
            Customer customer = getCustomerById(customerId);//tìm kiếm khách hàng theo id
            if (customer != null) {//tìm thấy khách hàng
                Account account = new SavingsAccount();
                account.input(scanner);

                if (!isAccountExisted(account)) {//tài khoản chưa tồn tại
                    this.accountsList.add(account);
                    customer.addAccount(account);
                } else {//tài khoản đã tồn tại
                    System.out.printf("Tài khoản %s đã tồn tại, thêm tài khoản ATM không thành công\n", account.getAccountNumber());
                }
            } else {//không tìm thấy khách hàng
                System.out.printf("Không tìm thấy khách hàng %s, tác vụ không thành công\n", customerId);
            }
        } else {//không có khách hàng trong danh sách
            System.out.println("Ngân hàng không có khách hàng nào, thao tác không thành công");
        }
    }

    //phương thức thêm tài khoản tín dụng cho khách hàng
    public void addLoansAccount(Scanner scanner, String customerId) {
        if (!this.customersList.isEmpty()) {//có khách hàng trong danh sách
            Customer customer = getCustomerById(customerId);//tìm kiếm khách hàng theo id
            if (customer != null) {//tìm thấy khách hàng
                Account account = new LoansAccount();
                account.input(scanner);

                if (!isAccountExisted(account)) {//tài khoản chưa tồn tại
                    this.accountsList.add(account);
                    customer.addAccount(account);
                } else {//tài khoản đã tồn tại
                    System.out.printf("Tài khoản %s đã tồn tại, thêm tài khoản tín dụng không thành công\n", account.getAccountNumber());
                }
            } else {//không tìm thấy khách hàng
                System.out.printf("Không tìm thấy khách hàng %s, tác vụ tín dụng không thành công\n", customerId);
            }
        } else {//không có khách hàng trong danh sách
            System.out.println("Ngân hàng không có khách hàng nào, thao tác không thành công");
        }
    }

    //phương thức hiển thị lịch sử giao dịch của khách hàng
    public void displayTransactionInformation(String customerId) {
        if (!this.customersList.isEmpty()) {//có khách hàng trong danh sách
            Customer customer = getCustomerById(customerId);
            if (customer != null) {//tìm thấy khách hàng
                customer.displayTransactionInformation();
            } else {//không tìm thấy khách hàng
                System.out.printf("Không tìm thấy khách hàng %s, thao tác không thành công\n", customerId);
            }
        } else {//không có khách hàng trong danh sách
            System.out.println("Ngân hàng không có khách hàng nào, thao tác không thành công");
        }
    }
}
