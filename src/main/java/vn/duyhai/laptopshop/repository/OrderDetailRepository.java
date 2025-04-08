package vn.duyhai.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.duyhai.laptopshop.domain.OrderDetail;

@Repository
public interface  OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    
}
