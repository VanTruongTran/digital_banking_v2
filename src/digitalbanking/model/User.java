/**
 * purpose: class quản lý thông tin người dùng của hệ thống
 * date created: 29/9/2022
 * author: Van Truong
 * version: 1.0.0
 */

package digitalbanking.model;

import digitalbanking.io.CustomerIdValidator;

public abstract class User {
    /*ATTRIBUTES*/
    protected String name;
    protected String customerId;

    /*GETTERS & SETTERS*/
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(String customerId) {
        if (CustomerIdValidator.validateCustomerId(customerId)) {
            this.customerId = customerId;
        }
    }

    /*CONSTRUCTORS*/
    public User() {

    }

    public User(String name, String customerId) {
        this.name = name;
        if (CustomerIdValidator.validateCustomerId(customerId)) {
            this.customerId = customerId;
        }
    }
}
