package vn.duyhai.laptopshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.duyhai.laptopshop.domain.User;

//crud: create, read, update, delete
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User eric);

    void deleteById(long id);

    List<User> findOneByEmail(String email); // cai nay tra ve List nen minh co the gioi han bang cach them one

    List<User> findAll();

    User findById(long id); // null

    boolean existsByEmail(String email);

    User findByEmail(String email);
}
