package lk.ijse.backend.repo;

import lk.ijse.backend.entity.OrderedServiceDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedServiceDetailRepository extends JpaRepository<OrderedServiceDetails, Long> {

}
