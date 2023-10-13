package exercise.repository;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import exercise.model.Product;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{ }
// END
