/**
 * project: Assignment 03
 * date created: 29/9/2022
 * author: Van Truong
 * version: 1.0.0
 */

package digitalbanking;

import digitalbanking.model.Customer;
import digitalbanking.model.DigitalBank;
import digitalbanking.model.DigitalCustomer;

import java.util.Scanner;

public class Entry {
    private static Scanner scanner;
    private static DigitalBank digitalBank;
    private static final String CUSTOMER_ID = "001215000001";
    private static final String CUSTOMER_NAME = "Huy";

    //hàm main
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        digitalBank = new DigitalBank();

        Customer customer = new DigitalCustomer(CUSTOMER_NAME, CUSTOMER_ID);
        digitalBank.getCustomersList().add(customer);

        displayGeneralInfo();//hiển thị thông tin chung
        boolean flag = true;

        do {
            String answer = inputMenu();//chọn chức năng giao diện
            switch (answer) {
                case "1"://thông tin khách hàng
                    digitalBank.showCustomerInformation(CUSTOMER_ID);
                    break;

                case "2"://thêm tài khoản ATM
                    digitalBank.addSavingsAccount(scanner, CUSTOMER_ID);
                    break;

                case "3"://thêm tài khoản tín dụng
                    digitalBank.addLoansAccount(scanner, CUSTOMER_ID);
                    break;

                case "4"://rút tiền
                    digitalBank.withdraw(scanner, CUSTOMER_ID);
                    break;

                case "5"://lịch sử giao dịch
                    digitalBank.displayTransactionInformation(CUSTOMER_ID);
                    break;

                case "0"://thoát
                    displayExitWarning();
                    flag = false;
                    break;

                default:
                    System.out.println("Vui lòng chọn chức năng từ 0 - 5");
                    break;
            }
        } while (flag);
    }

    //hàm chọn chức năng giao diện
    private static String inputMenu() {
        System.out.println("+------------------------------------------------------+");
        System.out.printf("| %-52s |\n", "1. Thông tin khách hàng");
        System.out.printf("| %-52s |\n", "2. Thêm tài khoản ATM");
        System.out.printf("| %-52s |\n", "3. Thêm tài khoản tín dụng");
        System.out.printf("| %-52s |\n", "4. Rút tiền");
        System.out.printf("| %-52s |\n", "5. Lịch sử giao dịch");
        System.out.printf("| %-52s |\n", "0. Thoát");
        System.out.println("+------------------------------------------------------+");
        System.out.print("Chọn chức năng: ");
        String answer = scanner.nextLine();
        System.out.println("+------------------------------------------------------+");
        return answer;
    }

    //hàm hiển thị thông tin chung
    private static void displayGeneralInfo() {
        System.out.println("+------------------------------------------------------+");
        System.out.printf("| %-52s |\n", "NGÂN HÀNG ĐIỆN TỬ | FX17848 - V1.0.0");
        System.out.println("+------------------------------------------------------+");
    }

    //hàm hiển thị thông báo thoát
    private static void displayExitWarning() {
        System.out.println("+------------------------------------------------------+");
        System.out.printf("| %-52s |\n", "Thoát thành công");
        System.out.println("+------------------------------------------------------+");
    }
}
