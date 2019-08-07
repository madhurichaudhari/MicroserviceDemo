package com.microservice.MicroServiceApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductValueRepository extends JpaRepository<Product, Long> {

}