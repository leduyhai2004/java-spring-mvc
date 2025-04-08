package vn.duyhai.laptopshop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.duyhai.laptopshop.domain.Order;
import vn.duyhai.laptopshop.domain.User;


@Repository
public interface  OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
    Page<Order> findAll(Pageable page);
}
