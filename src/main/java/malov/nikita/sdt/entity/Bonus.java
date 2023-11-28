package malov.nikita.sdt.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
public class Bonus extends Payment{
    private String reason;
    public Bonus(String reason,double amount){
        super(amount);
        this.reason = reason;
    }
}
