package lk.ijse.backend.DTO;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

public class OrderDTO {
    private long orderId;
    private Date orderDate;
    private Time orderTime;
    private String orderStatus;
    private String paymentType;
    private double orderTotal;
    private String customerName;
    private String customerAddress;
    private String apartment;
    private String customerContact;
    private String customerEmail;
    private String postCode;
    private String city;
    private String country;
    private String note;
    private UUID userId;

    public OrderDTO() {
    }

    public OrderDTO(long orderId, Date orderDate, Time orderTime, String orderStatus, String paymentType, double orderTotal, String customerName, String customerAddress, String apartment, String customerContact, String customerEmail, String postCode, String city, String country, String note, UUID userId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.orderStatus = orderStatus;
        this.paymentType = paymentType;
        this.orderTotal = orderTotal;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.apartment = apartment;
        this.customerContact = customerContact;
        this.customerEmail = customerEmail;
        this.postCode = postCode;
        this.city = city;
        this.country = country;
        this.note = note;
        this.userId = userId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Time getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Time orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", orderTime=" + orderTime +
                ", orderStatus='" + orderStatus + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", orderTotal=" + orderTotal +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", apartment='" + apartment + '\'' +
                ", customerContact='" + customerContact + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", postCode='" + postCode + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", note='" + note + '\'' +
                ", userId=" + userId +
                '}';
    }
}
