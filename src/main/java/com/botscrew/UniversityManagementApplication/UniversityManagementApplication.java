package com.botscrew.UniversityManagementApplication;

import com.botscrew.UniversityManagementApplication.service.UniversityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class UniversityManagementApplication implements CommandLineRunner {

	private final UniversityService universityService;

	public UniversityManagementApplication(UniversityService universityService) {
        this.universityService = universityService;
    }

	public static void main(String[] args) {
		SpringApplication.run(UniversityManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("Enter a command: ");
			String command = scanner.nextLine();
			handleCommand(command);
		}
	}

	private void handleCommand(String command) {
		if (command.startsWith("Who is head of department ")) {
			String departmentName = command.split("department ")[1];
			universityService.findHeadOfDepartment(departmentName);
		} else if (command.startsWith("Show") && command.contains("statistics")) {
			Pattern pattern = Pattern.compile("Show\\s+(.+?)\\s+statistics");
			Matcher matcher = pattern.matcher(command);
			String departmentName = matcher.group(1);
			universityService.countDepartmentStatistics(departmentName);
		} else if (command.startsWith("Show the average salary for the department")) {
			String departmentName = command.split("department ")[1];
			universityService.getDepartmentAverageSalary(departmentName);
		} else if (command.startsWith("Show count of employee for ")) {
			String departmentName = command.split("for ")[1];
			universityService.getDepartmentEmployeeCount(departmentName);
		} else if (command.startsWith("Global search by")) {
			String template = command.split("by ")[1];
			universityService.globalSearch(template);
		} else {
			System.out.println("Invalid command");
		}
	}
}
