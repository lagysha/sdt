package malov.nikita.sdt.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "departments")
@Setter
@Getter
@NoArgsConstructor
public class Department {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "head_of_department__id")
    private Employee headOfDepartment;

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    public void addEmployee(Employee employee){
        employee.setDepartment(this);
        employees.add(employee);
    }

    public void deleteEmployee(Employee employee){
        employee.setDepartment(null);
        employees.remove(employee);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
