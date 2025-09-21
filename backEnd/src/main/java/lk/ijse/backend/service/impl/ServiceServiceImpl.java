package lk.ijse.backend.service.impl;

import lk.ijse.backend.DTO.ItemDTO;
import lk.ijse.backend.DTO.ServiceDTO;
import lk.ijse.backend.entity.Services;
import lk.ijse.backend.repo.ServiceRepository;
import lk.ijse.backend.service.ServiceService;
import lk.ijse.backend.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public int saveService(ServiceDTO serviceDTO) {
        if (serviceRepository.existsById(serviceDTO.getServiceId())) {
            return VarList.Not_Acceptable;
        } else {
            serviceRepository.save(modelMapper.map(serviceDTO, Services.class));
            return VarList.Created;
        }
    }

    @Override
    public ServiceDTO searchService(long serviceId) {
        if (serviceRepository.existsById(serviceId)) {
            Services services = serviceRepository.findById(serviceId).get();
            return modelMapper.map(services, ServiceDTO.class);
        }
        return null;
    }

    @Override
    public int deleteService(long serviceId) {
        if (serviceRepository.existsById(serviceId)) {
            serviceRepository.deleteById(serviceId);
            return VarList.OK;
        } else {
            return VarList.Not_Found;
        }
    }

    @Override
    public int updateService(ServiceDTO serviceDTO) {
        if (serviceRepository.existsById(serviceDTO.getServiceId())) {
            serviceRepository.save(modelMapper.map(serviceDTO, Services.class));
            return VarList.OK;
        } else {
            return VarList.Not_Found;
        }
    }

    @Override
    public List<ServiceDTO> getAllServices() {
        List<Services> all = serviceRepository.findAll();
        return modelMapper.map(all,new TypeToken<List<ServiceDTO>>(){}.getType());

    }
}
