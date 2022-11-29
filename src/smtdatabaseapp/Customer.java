
package smtdatabaseapp;


public class Customer {
    private int customerId;
    private String customerName;
    private String emailId;
    private String custPhnNo;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getCustPhnNo() {
        return custPhnNo;
    }

    public void setCustPhnNo(String custPhnNo) {
        this.custPhnNo = custPhnNo;
    }
}
