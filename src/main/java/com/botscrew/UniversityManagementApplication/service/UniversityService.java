package com.botscrew.UniversityManagementApplication.service;

import com.botscrew.UniversityManagementApplication.entity.Degree;
import com.botscrew.UniversityManagementApplication.entity.Department;
import com.botscrew.UniversityManagementApplication.repository.DepartmentRepository;
import com.botscrew.UniversityManagementApplication.repository.LectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class UniversityService {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private LectorRepository lectorRepository;

    public void findHeadOfDepartment(String departmentName) {
        Department department = departmentRepository.findByName(departmentName);

        if (department == null) {
            System.out.println("Department not found");
            return;
        }

        System.out.format("Head of %s department is %s\n", department.getName(), department.getHeadOfTheDepartment().getName());
    }

    public void countDepartmentStatistics(String departmentName) {
        Department department = departmentRepository.findByName(departmentName);

        if (department == null) {
            System.out.println("Department not found");
            return;
        }

        long assistants = departmentRepository.countDepartmentLectorsByDegree(departmentName, Degree.ASSISTANT);
        long associateProfessors = departmentRepository.countDepartmentLectorsByDegree(departmentName, Degree.ASSOCIATE_PROFESSOR);
        long professors = departmentRepository.countDepartmentLectorsByDegree(departmentName, Degree.PROFESSOR);

        System.out.format("assistants - %d\nassociate professors - %d\nprofessors - %d\n",
                          assistants, associateProfessors, professors);

    }

    public void getDepartmentAverageSalary(String departmentName) {
        BigDecimal averageSalary = departmentRepository.findDepartmentAverageSalary(departmentName);

        if (averageSalary == null) {
            System.out.println("Department not found or no lectors in the department");
            return;
        }

        System.out.println("The average salary of " + departmentName + " is " + averageSalary);
    }

    public void getDepartmentEmployeeCount(String departmentName) {
        Department department = departmentRepository.findByName(departmentName);

        if (department == null) {
            System.out.println("Department not found");
            return;
        }

        long employeeCount = department.getLectors().size();

        System.out.println(employeeCount);
    }

    public void globalSearch(String template) {
        List<String> matches = new ArrayList<>();

        matches.addAll(lectorRepository.findByTemplate(template));
        matches.addAll(departmentRepository.findByTemplate(template));

        System.out.println(String.join(", ", matches));
    }

}
