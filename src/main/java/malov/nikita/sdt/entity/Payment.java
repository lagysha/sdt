package malov.nikita.sdt.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.MappedSuperclass;
import lombok.NoArgsConstructor;

@Embeddable
@MappedSuperclass
@NoArgsConstructor
abstract public class Payment {
    public Payment(double amount) {
        this.amount = amount;
    }

    private double amount;
}
