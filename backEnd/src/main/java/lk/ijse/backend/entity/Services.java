package lk.ijse.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


/*@AllArgsConstructor
@NoArgsConstructor
@Data*/

@Entity
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long serviceId;
    private String serviceName;
    private String serviceDescription;
    private double servicePrice;
    private String serviceDuration;
    private String serviceType;
    private String serviceStatus;
    private String location;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "services")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "services")
    private List<OrderedServiceDetails> serviceDetails = new ArrayList<>();

    public Services() {
    }

    public Services(long serviceId, String serviceName, String serviceDescription, double servicePrice, String serviceDuration, String serviceType, String serviceStatus, String location, List<Review> reviews, List<OrderedServiceDetails> serviceDetails) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.serviceDescription = serviceDescription;
        this.servicePrice = servicePrice;
        this.serviceDuration = serviceDuration;
        this.serviceType = serviceType;
        this.serviceStatus = serviceStatus;
        this.location = location;
        this.reviews = reviews;
        this.serviceDetails = serviceDetails;
    }

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<OrderedServiceDetails> getServiceDetails() {
        return serviceDetails;
    }

    public void setServiceDetails(List<OrderedServiceDetails> serviceDetails) {
        this.serviceDetails = serviceDetails;
    }
}
