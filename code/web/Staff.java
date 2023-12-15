package web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class StaffService{
    @Autowired
    StaffDetail sd;

    // READ ALL
    @RequestMapping("/api/staff-detail")
    Iterable<Staff> allDetail(){
        return sd.findAll();
    }
    // READ BY ID
    @RequestMapping("/api/staff-detail-by-id")
    public Staff getStaffById(@RequestParam(name = "number") int number) {
        return sd.findById(number).orElse(null);
    }

    // DELETE
    @RequestMapping("/api/staff-delete-by-id")
    public void deleteStaffDetail(@RequestParam(name = "number") int number){
        sd.deleteById(number);
    }
}

@Service
interface StaffDetail extends CrudRepository<Staff,Integer>{

}

@Table("staff")
public class Staff {
    @Id public int number;
    public String first_name;
    String last_name;
    double salary;
    public String status;
}
