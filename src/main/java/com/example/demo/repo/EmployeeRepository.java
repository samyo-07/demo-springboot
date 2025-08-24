package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.dto.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	List<Employee> findByNameContainingIgnoreCase(String name);

	List<Employee> findByIdAndNameContainingIgnoreCase(Integer id, String name);

	@Query(value = "SELECT * FROM employee where id=:id", nativeQuery = true)
	List<Employee> findByNativeQurey(@Param("id") Integer id);

}
