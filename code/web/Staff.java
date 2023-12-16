package web;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("staff")
public class Staff {
    @Id
    public int number;
    public String first_name;
    String last_name;
    double salary;
    public String status;
}
