package vn.duyhai.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.duyhai.laptopshop.domain.Product;
import vn.duyhai.laptopshop.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product pr) {
        return this.productRepository.save(pr);
    }

    public List<Product> fetchProducts() {
        return this.productRepository.findAll();
    }
    public Product getProductById(long id) {
        return this.productRepository.findById(id);
    }
    public void deleteProduct(long id) {
        this.productRepository.deleteById(id);
    }

}
