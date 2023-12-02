package malov.nikita.sdt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record ReportResponseDto(LocalDate fromDate,
                                LocalDate toDate,
                                String employeeRole,
                                Double employeeSalaryAmount,
                                String bonusReason,
                                @JsonProperty("bonus money")
                                Double bonusAmount,
                                Integer wage,
                                Boolean status) {
}
