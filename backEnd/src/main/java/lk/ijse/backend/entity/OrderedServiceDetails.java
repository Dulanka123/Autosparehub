package lk.ijse.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


/*@AllArgsConstructor
@NoArgsConstructor
@Data*/

@Entity
public class OrderedServiceDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long serviceDetailId;
    private String serviceName;
    private Date serviceDate;
    private double servicePrice;
    private String serviceDuration;
    private String serviceType;
    private String serviceStatus;



    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "orderId")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "serviceId", referencedColumnName = "serviceId")
    private Services services;

    public OrderedServiceDetails() {
    }

    public OrderedServiceDetails(long serviceDetailId, String serviceName, Date serviceDate, double servicePrice, String serviceDuration, String serviceType, String serviceStatus, Order order, Services services) {
        this.serviceDetailId = serviceDetailId;
        this.serviceName = serviceName;
        this.serviceDate = serviceDate;
        this.servicePrice = servicePrice;
        this.serviceDuration = serviceDuration;
        this.serviceType = serviceType;
        this.serviceStatus = serviceStatus;
        this.order = order;
        this.services = services;
    }

    public long getServiceDetailId() {
        return serviceDetailId;
    }

    public void setServiceDetailId(long serviceDetailId) {
        this.serviceDetailId = serviceDetailId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getServiceDuration() {
        return serviceDuration;
    }

    public void setServiceDuration(String serviceDuration) {
        this.serviceDuration = serviceDuration;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }
}
