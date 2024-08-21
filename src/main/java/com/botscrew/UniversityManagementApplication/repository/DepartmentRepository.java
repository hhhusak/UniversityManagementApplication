package com.botscrew.UniversityManagementApplication.repository;

import com.botscrew.UniversityManagementApplication.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    public Department findByName(final String name);

    @Query("SELECT d.name FROM Department d WHERE d.name LIKE %:keyword%")
    List<String> findByKeyword(@Param("keyword") String keyword);

}
