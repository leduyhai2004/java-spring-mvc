package vn.duyhai.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.duyhai.laptopshop.domain.Cart;
import vn.duyhai.laptopshop.domain.CartDetail;
import vn.duyhai.laptopshop.domain.Product;

public interface  CartDetailRepository extends JpaRepository<CartDetail, Long> {
    boolean existsByCartAndProduct(Cart cart, Product product);
    CartDetail findByCartAndProduct(Cart cart, Product product);
}
