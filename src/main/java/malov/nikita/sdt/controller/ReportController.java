package malov.nikita.sdt.controller;

import lombok.RequiredArgsConstructor;
import malov.nikita.sdt.dto.ReportDateDtoRequest;
import malov.nikita.sdt.dto.ReportResponseDto;
import malov.nikita.sdt.entity.Report;
import malov.nikita.sdt.service.ReportService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping
    public List<ReportResponseDto> getAllReportsByDate(@RequestBody ReportDateDtoRequest request, Pageable pageable){
        System.out.println(request);
        return reportService.getAllReportsByDate(request,pageable);
    }
}
