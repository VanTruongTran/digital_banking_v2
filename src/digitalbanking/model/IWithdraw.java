/**
 * purpose: interface xử lý nghiệp vụ rút tiền
 * date created: 29/9/2022
 * author: Van Truong
 * version: 1.0.0
 */

package digitalbanking.model;

public interface IWithdraw {
    public abstract boolean withdraw(double amount);

    public abstract boolean isAccepted(double amount);
}
