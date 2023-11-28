package malov.nikita.sdt.repositorytests;

import malov.nikita.sdt.entity.*;
import malov.nikita.sdt.repository.DepartmentRepository;
import malov.nikita.sdt.repository.EmployeeRepository;
import malov.nikita.sdt.repository.ReportRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReportRepositoryTest {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    @Order(1)
    @DisplayName("findAllByFromDate returns List of reports based on correct date")
    public void findAllByFromDateAndToDateRetrievesCorrectDataWithCorrectDates(){
        Department department = new Department();
        department.setName("Back-End");
        departmentRepository.save(department);
        LocalDate localDate1 = LocalDate.now();

        LocalDate localDate2 = localDate1.plusMonths(1);

        Employee employee1 = new Employee("john","1234","employee",new Salary(30_000),department);
        Employee employee2 = new Employee("doe","123456789","headOfDepartment",new Salary(100_000),department);
        Employee employee3 = new Employee("pupa","1234ghhh","employee",new Salary(40_000),department);
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);
        department.setHeadOfDepartment(employee2);
        departmentRepository.save(department);

        Report report1 = new Report(localDate1,localDate2,employee1,30_000);
        report1.setBonus(new Bonus("",0));
        Report report2 = new Report(localDate1,localDate2,employee2,100_000);
        report2.setBonus(new Bonus("",0));
        LocalDate localeDate3 = localDate2.plusMonths(1);
        Report report3 = new Report(localDate1, localeDate3,employee3,40_000);
        report3.setBonus(new Bonus("",0));
        List<Report> expectedReports = List.of(report1,report2);
        reportRepository.save(report1);
        reportRepository.save(report2);
        reportRepository.save(report3);

        List<Report> actualReports = reportRepository.findAllByFromDateAndToDate(localDate1,localDate2);

        assertNotEquals(reportRepository.findAll(),actualReports);
        assertEquals(expectedReports,actualReports);
    }
}
