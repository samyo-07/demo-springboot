package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.dto.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	@Query(value = "SELECT * FROM address WHERE employee_id = :employeeId", nativeQuery = true)
	Address findAddressByEmployeeId(@Param("employeeId") Integer employeeId);
}
