package com.botscrew.UniversityManagementApplication.repository;

import com.botscrew.UniversityManagementApplication.entity.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LectorRepository extends JpaRepository<Lector, Long> {
    @Query("SELECT l.name FROM Lector l WHERE l.name LIKE %:template%")
    public List<String> findByTemplate(@Param("template") String template);
}
