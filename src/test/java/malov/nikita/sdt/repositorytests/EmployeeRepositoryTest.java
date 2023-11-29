package malov.nikita.sdt.repositorytests;

import malov.nikita.sdt.entity.Department;
import malov.nikita.sdt.entity.Employee;
import malov.nikita.sdt.entity.Salary;
import malov.nikita.sdt.repository.DepartmentRepository;
import malov.nikita.sdt.repository.EmployeeRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class EmployeeRepositoryTest {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @BeforeEach
    public void init(){
        Department department = new Department();
        department.setName("Back-End");
        departmentRepository.save(department);

        Employee employee1 = new Employee("john","1234","employee",new Salary(30_000),department);
        Employee employee2 = new Employee("doe","123456789","headOfDepartment",new Salary(100_000),department);
        Employee employee3 = new Employee("pupa","1234ghhh","employee",new Salary(40_000),department);
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);
        departmentRepository.save(department);
    }

    @Test
    @Order(1)
    @DisplayName("findByLoginName returns Employee based on correct loginName")
    public void findByLoginNameRetrievesEmployeeWithNotExistingLoginName(){
        var expectedResult = employeeRepository.findById(1L);
        var actualResult = employeeRepository.findByLoginName("john");

       assertEquals(expectedResult,actualResult);
    }

    @Test
    @Order(2)
    @DisplayName("findByLoginName returns nothing based on not correct loginName")
    public void findByLoginNameRetrievesNothingWithNotExistingLoginName(){
        var actualResult = employeeRepository.findByLoginName("john1");

        assertFalse(actualResult.isPresent());
    }
}
