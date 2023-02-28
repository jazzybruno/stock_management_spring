package rw.jazzybruno.istock.v1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.jazzybruno.istock.v1.models.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product , Long> {
    Optional<Product> findProductByProdName(String name);
}
