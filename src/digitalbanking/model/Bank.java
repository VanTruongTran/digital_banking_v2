/**
 * purpose: class quản lý thông tin ngân hàng
 * date created: 29/9/2022
 * author: Van Truong
 * version: 1.0.0
 */

package digitalbanking.model;

import digitalbanking.io.CustomerIdValidator;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Bank {
    /*ATTRIBUTES*/
    protected String bankId;
    protected ArrayList<Customer> customersList;
    protected ArrayList<Account> accountsList;

    /*GETTERS & SETTERS*/
    public String getBankId() {
        return this.bankId;
    }

    public ArrayList<Customer> getCustomersList() {
        return this.customersList;
    }

    public ArrayList<Account> getAccountsList() {
        return this.accountsList;
    }

    /*CONSTRUCTORS*/
    public Bank() {
        this.bankId = String.valueOf(UUID.randomUUID());
        this.customersList = new ArrayList<Customer>();
        this.accountsList = new ArrayList<Account>();
    }

    /*SERVICE METHODS*/
    //phương thức thêm khách hàng vào danh sách khách hàng
    public void addCustomer(Customer newCustomer) {
        if (!isCustomerExisted(newCustomer)) {//khách hàng chưa tồn tại trong danh sách
            this.customersList.add(newCustomer);
            System.out.printf("Đã thêm khách hàng %s vào danh sách khách hàng\n", newCustomer.getCustomerId());
        } else {//khách hàng đã tồn tại trong danh sách
            System.out.printf("Khách hàng %s đã tồn tại, thêm khách hàng không thành công\n", newCustomer.getCustomerId());
        }
    }

    public void addCustomer(Scanner scanner) {
        Customer customer = new Customer();
        customer.input(scanner);
        if (!isCustomerExisted(customer)) {//khách hàng chưa tồn tại trong danh sách
            this.customersList.add(customer);
            System.out.printf("Đã thêm khách hàng %s vào danh sách khách hàng\n", customer.getCustomerId());
        } else {//khách hàng đã tồn tại trong danh sách
            System.out.printf("Khách hàng %s đã tồn tại, thêm khách hàng không thành công\n", customer.getCustomerId());
        }
    }

    //phương thức thêm tài khoản cho khách hàng
    public void addAccount(String customerId, Account account) {
        if (!this.customersList.isEmpty()) {//có khách hàng trong danh sách
            Customer customer = getCustomerById(customerId);//tìm kiếm khách hàng theo id
            if (customer != null) {//tìm thấy khách hàng
                if (!isAccountExisted(account)) {//tài khoản chưa tồn tại trong danh sách
                    this.accountsList.add(account);
                    customer.addAccount(account);
                } else {//tài khoản đã tồn tại trong danh sách
                    System.out.printf("Tài khoản %s đã tồn tại, thêm tài khoản không thành công\n", account.getAccountNumber());
                }
            } else {//không tìm thấy khách hàng
                System.out.printf("Không tìm thấy khách hàng %s, thêm tài khoản không thành công\n", customerId);
            }
        } else {//không có khách hàng trong danh sách
            System.out.println("Ngân hàng không có khách hàng nào, thao tác không thành công");
        }
    }

    public void addAccount(Scanner scanner) {
        if (!this.customersList.isEmpty()) {//có khách hàng trong danh sách
            displayCustomersList();//hiển thị danh sách khách hàng
            Customer customer;
            do {
                System.out.print("Nhập số CCCD khách hàng: ");
                customer = getCustomerById(scanner.nextLine());//tìm kiếm khách hàng theo id
            } while (customer == null);

            Account account = new Account();
            account.input(scanner);
            if (!isAccountExisted(account)) {//tài khoản chưa tồn tại trong danh sách
                this.accountsList.add(account);
                customer.addAccount(account);
            } else {//tài khoản đã tồn tại trong danh sách
                System.out.printf("Tài khoản %s đã tồn tại, thêm tài khoản không thành công\n", account.getAccountNumber());
            }
        } else {//không có khách hàng trong danh sách
            System.out.println("Ngân hàng không có khách hàng nào, thao tác không thành công");
        }
    }

    //phương thức hiển thị danh sách khách hàng
    public void displayCustomersList() {
        if (!this.customersList.isEmpty()) {//có khách hàng trong danh sách
            for (Customer customer : this.customersList) {
                System.out.println(customer.toString());
            }
        } else {//không có khách hàng trong danh sách
            System.out.println("Ngân hàng không có khách hàng nào, thao tác không thành công");
        }
    }

    //phương thức hiển thị thông tin chi tiết danh sách khách hàng
    public void displayDetailCustomersList() {
        if (!this.customersList.isEmpty()) {//có khách hàng trong danh sách
            for (Customer customer : this.customersList) {
                customer.displayInformation();
            }
        } else {//không có khách hàng trong danh sách
            System.out.println("Ngân hàng không có khách hàng nào, thao tác không thành công");
        }
    }

    //phương thức tìm kiếm gần đúng khách hàng theo tên
    public void searchCustomerByName(Scanner scanner) {
        if (!this.customersList.isEmpty()) {//có khách hàng trong danh sách
            System.out.print("Nhập tên khách hàng: ");
            String customerName = scanner.nextLine().toUpperCase();
            boolean flag = false;

            //tìm kiếm khách hàng theo tên, xuất thông tin khách hàng nếu tìm thấy
            for (Customer customer : this.customersList) {
                if (customer.getName().toUpperCase().contains(customerName)) {
                    customer.displayInformation();
                    flag = true;
                }
            }

            if (!flag) {//không tìm thấy khách hàng
                System.out.printf("Không tìm thấy khách hàng \"%s\"\n", customerName);
            }
        } else {//không có khách hàng trong danh sách
            System.out.println("Ngân hàng không có khách hàng nào, thao tác không thành công");
        }
    }

    //phương thức tìm kiếm khách hàng theo id
    public void searchCustomerById(Scanner scanner) {
        if (!this.customersList.isEmpty()) {//có khách hàng trong danh sách
            String customerId;
            do {
                System.out.print("Nhập số CCCD khách hàng: ");
                customerId = scanner.nextLine();
            } while (!CustomerIdValidator.validateCustomerId(customerId));

            Customer customer = getCustomerById(customerId);//tìm kiếm khách hàng theo id
            if (customer != null) { //tìm thấy khách hàng
                customer.displayInformation();
            } else {//không tìm thấy khách hàng
                System.out.printf("Không tìm thấy khách hàng %s\n", customerId);
            }
        } else {//không có khách hàng trong danh sách
            System.out.println("Ngân hàng không có khách hàng nào, thao tác không thành công");
        }
    }

    //phương thức trả về khách hàng theo id
    public Customer getCustomerById(String customerId) {
        for (Customer customer : this.customersList) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;//tìm thấy khách hàng
            }
        }
        return null;
    }

    //phương thức kiểm tra khách hàng đã tồn tại trong danh sách khách hàng của ngân hàng hay chưa
    public boolean isCustomerExisted(Customer newCustomer) {
        for (Customer customer : this.customersList) {
            if (customer.getCustomerId().equals(newCustomer.getCustomerId())) {
                return true;//CCCD của khách hàng mới trùng với CCCD của một khách hàng trong danh sách khách hàng
            }
        }
        return false;
    }

    //phương thức kiểm tra tài khoản đã tồn tại trong danh sách tài khoản của ngân hàng hay chưa
    public boolean isAccountExisted(Account newAccount) {
        for (Account account : this.accountsList) {
            if (account.getAccountNumber().equals(newAccount.getAccountNumber())) {
                return true;//STK của tài khoản mới trùng với STK của một tài khoản trong danh sách tài khoản
            }
        }
        return false;
    }
}
