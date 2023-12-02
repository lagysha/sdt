package malov.nikita.sdt.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
public class Salary extends Payment{
    public Salary(double amount) {
        super(amount);
    }
}
