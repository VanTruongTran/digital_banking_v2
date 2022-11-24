/**
 * purpose: class quản lý tài khoản vay của khách hàng
 * date created: 29/9/2022
 * author: Van Truong
 * version: 1.0.0
 */

package digitalbanking.model;

import digitalbanking.io.DateTimeCreator;

import java.util.Scanner;

public class LoansAccount extends Account implements IReport, IWithdraw {
    /*CONSTANTS*/
    private static final float LOAN_ACCOUNT_WITHDRAW_FEE = 0.05f;
    private static final float LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE = 0.01f;
    private static final int LOAN_ACCOUNT_MAX_BALANCE = 100_000_000;

    /*CONSTRUCTORS*/
    public LoansAccount() {
        super();
    }

    public LoansAccount(String accountNumber) {
        super(accountNumber, 10_000_000);
    }

    /*SERVICE METHODS*/
    //phương thức nhập dữ liệu
    @Override
    public void input(Scanner scanner) {
        do {
            System.out.print("Nhập số tài khoản gồm 6 chữ số: ");
            this.accountNumber = scanner.nextLine();
        } while (!validateAccount(this.accountNumber));
        this.balance = 10_000_000;

        createTransaction(this.accountNumber, this.balance, DateTimeCreator.getDateTime(), true);//tạo giao dịch đầu tiên
    }

    //phương thức trả về chuỗi thông tin tài khoản
    @Override
    public String toString() {
        return String.format("%-6s | %-7s | %,30.2fđ", this.accountNumber, "LOAN", this.balance);
    }

    //phương thức in biên lai theo từng loại tài khoản mỗi khi rút tiền
    @Override
    public void log(double amount) {

    }

    public void log(double amount, double fee) {
        System.out.println("+------------------------------------------------------+");
        System.out.println("|               BIÊN LAI GIAO DỊCH LOAN                |");
        System.out.printf("| %-20s %31s |\n", "NGÀY GD:", DateTimeCreator.getDateTime());
        System.out.printf("| %-20s %31s |\n", "ATM ID:", "DIGITAL-BANK-ATM 2022");
        System.out.printf("| %-20s %31s |\n", "SỐ TK:", this.accountNumber);
        System.out.printf("| %-20s %31s |\n", "SỐ TIỀN RÚT:", String.format("%,.2fđ", amount));
        System.out.printf("| %-20s %31s |\n", "SỐ DƯ TK:", String.format("%,.2fđ", this.balance));
        System.out.printf("| %-20s %31s |\n", "PHÍ + VAT:", String.format("%,.2fđ", fee));
        System.out.println("+------------------------------------------------------+");
    }

    //phương thức xử lý nghiệp vụ rút tiền
    @Override
    public boolean withdraw(double amount) {
        if (isAccepted(amount)) {//số tiền rút hợp lệ
            double fee = getFee(amount);
            if (this.balance - (amount + fee) >= 50_000) {//số tiền trong tài khoản đủ để rút
                this.balance -= (amount + fee);
                createTransaction(this.accountNumber, -amount, DateTimeCreator.getDateTime(), true);//tạo giao dịch
                System.out.println("Rút tiền thành công, biên lai giao dịch:");
                log(amount, fee);//hiển thị biên lai giao dịch
                return true;
            } else {//số tiền trong tài khoản quá ít không thể rút
                System.out.println("Số tiền trong tài khoản không đủ, rút tiền không thành công");
                return false;
            }
        } else {//số tiền rút không hợp lệ
            System.out.println("Số tiền rút không hợp lệ, rút tiền không thành công");
            return false;
        }
    }

    //phương thức xác định số tiền có thỏa điều kiện rút tiền hay không
    @Override
    public boolean isAccepted(double amount) {
        return (amount > 0 && amount <= LOAN_ACCOUNT_MAX_BALANCE) && (amount % 10_000 == 0);
    }

    //phương thức tính phí rút tiền
    public double getFee(double amount) {
        return (isAccountPremium() ? LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE : LOAN_ACCOUNT_WITHDRAW_FEE) * amount;
    }
}
