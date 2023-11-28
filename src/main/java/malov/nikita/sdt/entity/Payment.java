package malov.nikita.sdt.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.MappedSuperclass;

@Embeddable
@MappedSuperclass
abstract public class Payment {
    private double amount;
}
