/**
 * purpose: class quản lý tài khoản tiết kiệm của khách hàng
 * date created: 29/9/2022
 * author: Van Truong
 * version: 1.0.0
 */

package digitalbanking.model;

import digitalbanking.io.DateTimeCreator;

public class SavingsAccount extends Account implements IReport, IWithdraw {
    /*CONSTANTS*/
    private static final int SAVINGS_ACCOUNT_MAX_WITHDRAW = 5_000_000;
    private static final int SAVINGS_ACCOUNT_MIN_WITHDRAW = 50_000;

    /*CONSTRUCTORS*/
    public SavingsAccount() {
        super();
    }

    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    /*SERVICE METHODS*/
    //phương thức trả về chuỗi thông tin tài khoản
    @Override
    public String toString() {
        return String.format("%-6s | %-7s | %,30.2fđ", this.accountNumber, "SAVINGS", this.balance);
    }

    //phương thức in biên lai theo từng loại tài khoản mỗi khi rút tiền
    @Override
    public void log(double amount) {
        System.out.println("+------------------------------------------------------+");
        System.out.println("|              BIÊN LAI GIAO DỊCH SAVINGS              |");
        System.out.printf("| %-20s %31s |\n", "NGÀY GD:", DateTimeCreator.getDateTime());
        System.out.printf("| %-20s %31s |\n", "ATM ID:", "DIGITAL-BANK-ATM 2022");
        System.out.printf("| %-20s %31s |\n", "SỐ TK:", this.accountNumber);
        System.out.printf("| %-20s %31s |\n", "SỐ TIỀN RÚT:", String.format("%,.2fđ", amount));
        System.out.printf("| %-20s %31s |\n", "SỐ DƯ TK:", String.format("%,.2fđ", this.balance));
        System.out.printf("| %-20s %31s |\n", "PHÍ + VAT:", "0đ");
        System.out.println("+------------------------------------------------------+");
    }

    //phương thức xử lý nghiệp vụ rút tiền
    @Override
    public boolean withdraw(double amount) {
        if (isAccepted(amount)) {//số tiền rút hợp lệ
            if ((this.balance - amount) >= 50_000) {//số tiền trong tài khoản đủ để rút
                this.balance -= amount;
                createTransaction(this.accountNumber, -amount, DateTimeCreator.getDateTime(), true);//tạo giao dịch
                System.out.println("Rút tiền thành công, biên lai giao dịch:");
                log(amount);//hiển thị biên lai giao dịch
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
        if (isAccountPremium()) {//đối với tài khoản premium
            return (amount >= SAVINGS_ACCOUNT_MIN_WITHDRAW) && (amount % 10_000 == 0);
        } else {//đối với tài khoản thường
            return (amount >= SAVINGS_ACCOUNT_MIN_WITHDRAW && amount <= SAVINGS_ACCOUNT_MAX_WITHDRAW) && (amount % 10_000 == 0);
        }
    }
}
