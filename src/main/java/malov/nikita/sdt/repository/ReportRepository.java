package malov.nikita.sdt.repository;

import malov.nikita.sdt.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report,Long>{
    List<Report> findAllByFromDateAndToDate(LocalDate fromDate, LocalDate toDate);
}
