package com.grazielleanaia.spring_security_jwt.infrastructure.repository;

import com.grazielleanaia.customer_registration.infrastructure.entity.Residence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidenceRepository extends JpaRepository<Residence, Long> {
}
