package malov.nikita.sdt.repositorytests;

import malov.nikita.sdt.dto.ReportResponseDto;
import malov.nikita.sdt.entity.*;
import malov.nikita.sdt.repository.DepartmentRepository;
import malov.nikita.sdt.repository.EmployeeRepository;
import malov.nikita.sdt.repository.ReportRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.springframework.data.domain.Pageable;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ReportRepositoryTest {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    @Order(1)
    @DisplayName("findAllByFromDateAndToDate returns List of reports based on correct date")
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
        List<ReportResponseDto> expectedReports = Stream.of(report2)
                                .map(report ->
                                        new ReportResponseDto(
                                                report.getFromDate(),
                                                report.getToDate(),
                                                report.getEmployee().getRole(),
                                                report.getEmployee().getSalary().getAmount(),
                                                report.getBonus().getReason(),
                                                report.getBonus().getAmount(),
                                                report.getWage(),
                                                report.getStatus()
                                        )).toList();

        reportRepository.save(report1);
        reportRepository.save(report2);
        reportRepository.save(report3);

        List<ReportResponseDto> actualReports = reportRepository
                .findAllByFromDateAndToDate(localDate1, localDate2, PageRequest.of(1,1));

        assertEquals(actualReports.size(), 1);
        assertEquals(expectedReports,actualReports);
    }
}
