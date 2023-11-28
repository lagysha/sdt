package malov.nikita.sdt.repository;

import malov.nikita.sdt.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report,Long> {
}
