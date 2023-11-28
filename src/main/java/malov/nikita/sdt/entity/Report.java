package malov.nikita.sdt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "reports")
@Setter
@Getter
@NoArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "from_date")
    private Timestamp fromDate;

    @Column(nullable = false, name = "to_date")
    private Timestamp toDate;

    @OneToOne
    @JoinColumn(name = "person_id", foreignKey = @ForeignKey(name = "reports_employees_fk"))
    private Employee employee;

    @Column
    private Integer wage;

    @Column
    private Boolean status;

    @Embedded
    private Bonus bonus;
}
