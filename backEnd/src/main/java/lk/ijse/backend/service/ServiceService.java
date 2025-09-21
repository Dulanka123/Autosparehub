package lk.ijse.backend.service;

import lk.ijse.backend.DTO.ServiceDTO;

import java.util.List;

public interface ServiceService {
    int saveService(ServiceDTO serviceDTO);
    ServiceDTO searchService(long serviceId);
    int deleteService(long serviceId);
    int updateService(ServiceDTO serviceDTO);
    List<ServiceDTO> getAllServices();
}
