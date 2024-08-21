package com.botscrew.UniversityManagementApplication.service;

import com.botscrew.UniversityManagementApplication.entity.Degree;
import com.botscrew.UniversityManagementApplication.entity.Department;
import com.botscrew.UniversityManagementApplication.entity.Lector;
import com.botscrew.UniversityManagementApplication.repository.DepartmentRepository;
import com.botscrew.UniversityManagementApplication.repository.LectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        if(department == null) {
            System.out.println("Department not found");
            return;
        }

        System.out.println("Head of " + department.getName() + " department is " + department.getHeadOfTheDepartment().getName());
    }

    public void countDepartmentStatistics(String departmentName) {
        Department department = departmentRepository.findByName(departmentName);

        if(department == null) {
            System.out.println("Department not found");
            return;
        }

        long assistants = department.getLectors().stream().filter(l -> l.getDegree() == Degree.ASSISTANT).count();
        long associate_professors = department.getLectors().stream().filter(l -> l.getDegree() == Degree.ASSOCIATE_PROFESSOR).count();
        long professors = department.getLectors().stream().filter(l -> l.getDegree() == Degree.PROFESSOR).count();

        System.out.println("assistants - " + assistants + "\nassociate professors - " + associate_professors + "\nprofessors - " + professors);

    }

    public void getDepartmentAverageSalary(String departmentName) {
        Department department = departmentRepository.findByName(departmentName);

        if(department == null) {
            System.out.println("Department not found");
            return;
        }

        Double averageSalary = department.getLectors().stream()
                .mapToDouble(Lector::getSalary)
                .average()
                .orElse(0.0);

        if(averageSalary == 0.0) {
            System.out.println("Department average salary is empty");
            return;
        }

        System.out.println("The average salary of " + departmentName + " is " + averageSalary);
    }

    public void getDepartmentEmployeeCount(String departmentName) {
        Department department = departmentRepository.findByName(departmentName);

        if(department == null) {
            System.out.println("Department not found");
            return;
        }

        long employeeCount = department.getLectors().size();

        System.out.println(employeeCount);
    }

    public void globalSearch(String template) {
        List<String> matches = new ArrayList<>();

        matches.addAll(lectorRepository.findByKeyword(template));
        matches.addAll(departmentRepository.findByKeyword(template));

        System.out.println(String.join(", ", matches));
    }

}
