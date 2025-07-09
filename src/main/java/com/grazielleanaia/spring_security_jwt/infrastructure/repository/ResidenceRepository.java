package com.grazielleanaia.spring_security_jwt.infrastructure.repository;


import com.grazielleanaia.spring_security_jwt.infrastructure.entity.Residence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidenceRepository extends JpaRepository<Residence, Long> {
}
