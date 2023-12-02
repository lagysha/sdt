package malov.nikita.sdt.repository;

import malov.nikita.sdt.dto.ReportResponseDto;
import malov.nikita.sdt.entity.Report;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report,Long>{
    List<ReportResponseDto> findAllByFromDateAndToDate(LocalDate fromDate,
                                                       LocalDate toDate,
                                                       Pageable pageable);
}
