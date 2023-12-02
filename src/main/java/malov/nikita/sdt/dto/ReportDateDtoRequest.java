package malov.nikita.sdt.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReportDateDtoRequest {
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate fromDate;

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate toDate;
}
