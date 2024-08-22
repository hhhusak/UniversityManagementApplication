package com.botscrew.UniversityManagementApplication.repository;

import com.botscrew.UniversityManagementApplication.entity.Degree;
import com.botscrew.UniversityManagementApplication.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    public Department findByName(final String name);

    @Query("SELECT AVG(l.salary) FROM Lector l JOIN l.department d WHERE d.name = :departmentName")
    BigDecimal findDepartmentAverageSalary(@Param("departmentName") String departmentName);

    @Query("SELECT COUNT(l) FROM Lector l JOIN l.department d WHERE d.name = :departmentName AND l.degree = :degree")
    long countDepartmentLectorsByDegree(String departmentName, Degree degree);

    @Query("SELECT d.name FROM Department d WHERE d.name LIKE %:template%")
    List<String> findByTemplate(@Param("template") String template);

    @Query("SELECT COUNT(l) FROM Lector l JOIN l.department d WHERE d.name = :departmentName")
    long countDepartmentLectors(String departmentName);

}
