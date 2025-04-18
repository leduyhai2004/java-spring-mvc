package vn.duyhai.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.duyhai.laptopshop.domain.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findById(long id);
}
