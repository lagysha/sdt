package malov.nikita.sdt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "employees")
@Setter
@Getter
@NoArgsConstructor
public class Employee {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false,unique = true)
    private String loginName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @Embedded
    private Salary salary;

    @ManyToOne
    @JoinColumn(name = "department_id",foreignKey = @ForeignKey(name = "employees_departments_fk"))
    private Department department;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(loginName, employee.loginName) && Objects.equals(role, employee.role) && Objects.equals(department, employee.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loginName, role, department);
    }
}
