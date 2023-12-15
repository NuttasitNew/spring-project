package web;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class StaffService{
    @Autowired
    private StaffDetail sd;

    // CREATE staff detail
    @RequestMapping("/api/create-staff-detail/{number}/{firstName}/{lastName}/{salary}/{status}")
    public int insertStaffDetail(@PathVariable(name = "number")    int number,
                                    @PathVariable(name = "firstName") String firstName,
                                    @PathVariable(name = "lastName")  String lastName,
                                    @PathVariable(name = "salary")    double salary,
                                    @PathVariable(name = "status")    String status){
        return sd.insertStaff(number, firstName, lastName, salary, status);
    }

    // READ ALL
    @GetMapping("/api/staff-detail")
    Iterable<Staff> allDetail(){
        return sd.findAll();
    }

    // READ by ID @RequestParam
    @GetMapping("/api/staff-detail-by-id")
    public Staff getStaffById(@RequestParam(name = "number") int number) {
        return sd.findById(number).orElse(null);
    }
    // Read by ID @PathVariable
    @GetMapping("/api/staff-detail-by-id/{number}")
    public Staff getStaffByIdPath(@PathVariable(name = "number") int number){
        return sd.findById(number).orElse(null);
    }

    // Custom Query : Read 'Active' Staff
    @RequestMapping("/api/staff-active")
    public List<Staff> getStaffActive(){
        return sd.findCustom();
    }

    // Update Status
    @RequestMapping("/api/update-staff-status/number={number}&status={status}")
    public int updateStaffStatus(@PathVariable(name="number") int number,
                                 @PathVariable(name="status") String status) {
        return sd.updateStatus(number, status);
    }

    // DELETE Staff by Id
    @RequestMapping("/api/delete-staff-by-Id/number={number}")
    public int deleteStaffById(@PathVariable(name="number")int number){
        return sd.deleteStaff(number);
    }


}

@Repository
interface StaffDetail extends CrudRepository<Staff,Integer>{

    @Query("SELECT * FROM staff WHERE status = 'Active'")
    List<Staff> findCustom() throws IncorrectResultSizeDataAccessException;
    @Modifying
    @Query("INSERT INTO staff (number, first_name, last_name, salary, status) " +
            "VALUES (:number, :firstName, :lastName, :salary, :status)")
    int insertStaff(@Param("number") int number,
                    @Param("firstName") String firstName,
                    @Param("lastName") String lastName,
                    @Param("salary") double salary,
                    @Param("status") String status);

    @Modifying
    @Query("UPDATE staff set status = :status WHERE number = :number")
    int updateStatus(@Param("number") int number,@Param("status") String status);

    @Modifying
    @Query("DELETE FROM staff WHERE number = :number")
    int deleteStaff(@Param("number") int number);
}

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
