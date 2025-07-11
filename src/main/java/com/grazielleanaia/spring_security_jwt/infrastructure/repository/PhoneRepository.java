package com.grazielleanaia.spring_security_jwt.infrastructure.repository;


import com.grazielleanaia.spring_security_jwt.infrastructure.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
