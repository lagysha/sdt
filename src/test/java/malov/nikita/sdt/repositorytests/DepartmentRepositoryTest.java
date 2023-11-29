package malov.nikita.sdt.repositorytests;

import malov.nikita.sdt.entity.Department;
import malov.nikita.sdt.repository.DepartmentRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DepartmentRepositoryTest {

    @Autowired
    DepartmentRepository departmentRepository;

    @BeforeEach
    public void init(){
        Department expectedResult = new Department();
        expectedResult.setName("Back-End");
        departmentRepository.save(expectedResult);
    }

    @Test
    @Order(1)
    @DisplayName("findByName returns Department based on correct name")
    public void findByNameRetrievesDepartmentWithExistingName(){
        var expectedResult = departmentRepository.findById(1L);
        var actualResult = departmentRepository.findByName("Back-End");
        assertEquals(actualResult,expectedResult);
    }

    @Test
    @Order(2)
    @DisplayName("findByName returns nothing based on not correct name")
    public void findByNameRetrievesNothingWithNotExistingName(){

        var actualResult = departmentRepository.findByName("Back-End11");
        assertFalse(actualResult.isPresent());
    }
}
