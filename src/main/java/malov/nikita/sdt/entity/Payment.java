package malov.nikita.sdt.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@MappedSuperclass
@NoArgsConstructor
abstract public class Payment {
    public Payment(double amount) {
        this.amount = amount;
    }

    private double amount;
}
