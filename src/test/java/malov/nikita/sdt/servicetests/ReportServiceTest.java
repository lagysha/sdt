package malov.nikita.sdt.servicetests;

import malov.nikita.sdt.dto.ReportDateDtoRequest;
import malov.nikita.sdt.dto.ReportResponseDto;
import malov.nikita.sdt.repository.ReportRepository;
import malov.nikita.sdt.service.ReportService;
import malov.nikita.sdt.service.impl.ReportServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReportServiceTest {

    @Mock
    private ReportRepository reportRepository;

    @InjectMocks
    private ReportServiceImpl reportService;

    private List<ReportResponseDto> generatedData;

    public ReportServiceTest() {
    }

    @BeforeEach
    public void init(){
        generatedData = createDtoList();
    }

    @Test
    @Order(1)
    @DisplayName("getAllReportsByDate returns Reports based on existing fromDate and toDate")
    public void getAllReportsByDateRetrievesListOfDataByWrightDates(){

        when(reportRepository.findAllByFromDateAndToDate(
                eq(LocalDate.of(2023, 1, 1)),
                eq(LocalDate.of(2023, 12, 31)),
                any()))
                .thenReturn(generatedData);

        LocalDate fromDate = LocalDate.of(2023, 1, 1);
        LocalDate toDate = LocalDate.of(2023, 12, 31);
        List<ReportResponseDto> actualResults =
                reportService.getAllReportsByDate(
                        new ReportDateDtoRequest(fromDate,toDate), Pageable.ofSize(5));

        assertEquals(5,actualResults.size());
        assertEquals(actualResults,generatedData);
    }

    @Test
    @Order(2)
    @DisplayName("getAllReportsByDate returns empty list based on not existing fromDate and toDate")
    public void getAllReportsByDateRetrievesEmptyListWithNotExistingDates(){
        when(reportRepository.findAllByFromDateAndToDate(any(),
                any(),
                any()))
                .thenReturn(Collections.emptyList());

        LocalDate fromDate = LocalDate.of(2023, 2, 1);
        LocalDate toDate = LocalDate.of(2023, 3, 31);
        List<ReportResponseDto> actualResults =
                reportService.getAllReportsByDate(
                        new ReportDateDtoRequest(fromDate,toDate), Pageable.unpaged());

        assertEquals(0,actualResults.size());
        assertNotEquals(actualResults,generatedData);
    }

    private List<ReportResponseDto> createDtoList() {
        List<ReportResponseDto> dtoList = new ArrayList<>();

        LocalDate fromDate = LocalDate.of(2023, 1, 1);
        LocalDate toDate = LocalDate.of(2023, 12, 31);
        String employeeRole = "Employee";
        Double employeeSalaryAmount = 50000.0;
        String bonusReason = "Exceptional performance";
        Double bonusAmount = 2000.0;
        Integer wage = 25;
        Boolean status = true;

        for (int i = 0; i < 5; i++) {
            ReportResponseDto dto = new ReportResponseDto(fromDate, toDate, employeeRole,
                    employeeSalaryAmount*i, bonusReason, bonusAmount, wage, status);
            dtoList.add(dto);
        }

        return dtoList;
    }
}
