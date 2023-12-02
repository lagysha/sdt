package malov.nikita.sdt.service.impl;

import lombok.RequiredArgsConstructor;
import malov.nikita.sdt.dto.ReportDateDtoRequest;
import malov.nikita.sdt.dto.ReportResponseDto;
import malov.nikita.sdt.repository.ReportRepository;
import malov.nikita.sdt.service.ReportService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;

    @Override
    public List<ReportResponseDto> getAllReportsByDate(ReportDateDtoRequest request, Pageable pageable) {
        return reportRepository.findAllByFromDateAndToDate(request.getFromDate(),
                request.getToDate(),pageable);
    }
}
