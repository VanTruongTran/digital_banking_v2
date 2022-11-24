/**
 * purpose: interface xử lý nghiệp vụ báo cáo
 * date created: 29/9/2022
 * author: Van Truong
 * version: 1.0.0
 */

package digitalbanking.model;

public interface IReport {
    public abstract void log(double amount);
}
