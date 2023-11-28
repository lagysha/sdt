package malov.nikita.sdt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "reports")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "from_date")
    private LocalDate fromDate;

    @Column(nullable = false, name = "to_date")
    private LocalDate toDate;

    @OneToOne
    @JoinColumn(name = "person_id", foreignKey = @ForeignKey(name = "reports_employees_fk"))
    private Employee employee;

    @Column
    private Integer wage;

    @Column
    private Boolean status;

    @Embedded
    private Bonus bonus;

    public Report(LocalDate fromDate, LocalDate toDate, Employee employee, Integer wage) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.employee = employee;
        this.wage = wage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(fromDate, report.fromDate) && Objects.equals(toDate, report.toDate) && Objects.equals(employee, report.employee) && Objects.equals(wage, report.wage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromDate, toDate, employee, wage);
    }
}
