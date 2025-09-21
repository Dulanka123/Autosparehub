package lk.ijse.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/*
@AllArgsConstructor
@NoArgsConstructor
@Data
*/

@Entity
@Table(name = "`order`")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    @OneToOne(mappedBy = "order")
    private Payment payment;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "order")
    private List<OrderedServiceDetails> serviceDetails = new ArrayList<>();

    public Order() {
    }

    public Order(long orderId, Date orderDate, Time orderTime, String orderStatus, String paymentType, double orderTotal, String customerName, String customerAddress, String apartment, String customerContact, String customerEmail, String postCode, String city, String country, String note, User user, Payment payment, List<OrderedServiceDetails> serviceDetails) {
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
        this.user = user;
        this.payment = payment;
        this.serviceDetails = serviceDetails;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public List<OrderedServiceDetails> getServiceDetails() {
        return serviceDetails;
    }

    public void setServiceDetails(List<OrderedServiceDetails> serviceDetails) {
        this.serviceDetails = serviceDetails;
    }

    @Override
    public String toString() {
        return "Order{" +
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
                ", user=" + user +
                ", payment=" + payment +
                ", serviceDetails=" + serviceDetails +
                '}';
    }
}
