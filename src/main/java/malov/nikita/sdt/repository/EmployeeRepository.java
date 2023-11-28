package malov.nikita.sdt.repository;

import malov.nikita.sdt.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
