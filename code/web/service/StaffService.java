package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.Staff;
import web.dao.StaffDao;

import java.util.List;

@RestController
public class StaffService{
    @Autowired
    private StaffDao staffDao;

    public int insertStaffDetail(@PathVariable(name = "number")    int number,
                                 @PathVariable(name = "firstName") String firstName,
                                 @PathVariable(name = "lastName")  String lastName,
                                 @PathVariable(name = "salary")    double salary,
                                 @PathVariable(name = "status")    String status){
        return staffDao.insertStaff(number, firstName, lastName, salary, status);
    }

    public Iterable<Staff> allDetail(){
        return staffDao.findAll();
    }


    public Staff getStaffById(@RequestParam int number) {
        return staffDao.findById(number).orElse(null);
    }

    public Staff getStaffByIdPath(@PathVariable int number){
        return staffDao.findById(number).orElse(null);
    }

    public List<Staff> getStaffActive(){
        return staffDao.findCustom();
    }

    public int updateStaffStatus(@PathVariable(name="number") int number,
                                 @PathVariable(name="status") String status) {
        return staffDao.updateStatus(number, status);
    }

    public int deleteStaffById(@PathVariable(name="number")int number){
        return staffDao.deleteStaff(number);
    }

}


