package malov.nikita.sdt.service;

import malov.nikita.sdt.dto.ReportDateDtoRequest;
import malov.nikita.sdt.dto.ReportResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReportService {
    List<ReportResponseDto> getAllReportsByDate(ReportDateDtoRequest request, Pageable pageable);
}
