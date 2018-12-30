package org.vincent.springmvc.dto.incomming;

import java.util.Date;

public class OtherDemoDTO {

    private Date date;

    private String customerName;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
