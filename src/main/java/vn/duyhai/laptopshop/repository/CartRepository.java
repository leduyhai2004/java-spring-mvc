package vn.duyhai.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.duyhai.laptopshop.domain.Cart;
import vn.duyhai.laptopshop.domain.User;

public interface  CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUser(User user);
}
