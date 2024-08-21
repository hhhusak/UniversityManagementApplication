package com.botscrew.UniversityManagementApplication.repository;

import com.botscrew.UniversityManagementApplication.entity.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LectorRepository extends JpaRepository<Lector, Long> {
    @Query(value = "SELECT l.name FROM Lector l WHERE l.name LIKE %:keyword%")
    public List<String> findByKeyword(@Param("keyword") String keyword);
}
