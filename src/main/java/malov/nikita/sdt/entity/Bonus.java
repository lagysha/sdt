package malov.nikita.sdt.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Bonus extends Payment{
    private String reason;
}
