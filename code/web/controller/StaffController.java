package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.Staff;
import web.service.StaffService;

import java.util.List;

@RestController
public class StaffController {
    @Autowired
    StaffService staffService;
    @RequestMapping("/check")
    String method1(){
        return "It Work!!";
    }

    // CREATE staff detail
    @RequestMapping("/api/create-staff-detail/{number}/{firstName}/{lastName}/{salary}/{status}")
    public int insertStaffDetail(@PathVariable(name = "number")    int number,
                                 @PathVariable(name = "firstName") String firstName,
                                 @PathVariable(name = "lastName")  String lastName,
                                 @PathVariable(name = "salary")    double salary,
                                 @PathVariable(name = "status")    String status){
        return staffService.insertStaffDetail(number,firstName,lastName,salary,status);
    }

    // READ ALL
    @GetMapping("/api/staff-detail")
    Iterable<Staff> allDetail(){
        return staffService.allDetail();
    }

    // READ by ID @RequestParam
    @GetMapping("/api/staff-detail-by-id")
    public Staff getStaffById(@RequestParam(name = "number") int number){
        return staffService.getStaffById(number);
    }

    // Read by ID @PathVariable
    @GetMapping("/api/staff-detail-by-id/{number}")
    public Staff getStaffByIdPath(@PathVariable(name = "number") int number){
        return staffService.getStaffByIdPath(number);
    }

    // Custom Query : Read 'Active' Staff
    @RequestMapping("/api/staff-active")
    public List<Staff> getStaffActive(){
        return staffService.getStaffActive();
    }

    // Update Status
    @RequestMapping("/api/update-staff-status/number={number}&status={status}")
    public int updateStaffStatus(@PathVariable(name = "number") int number,
                                 @PathVariable(name = "status") String status){
        return staffService.updateStaffStatus(number,status);
    }

    // DELETE Staff by Id
    @RequestMapping("/api/delete-staff-by-Id/number={number}")
    public int deleteStaffById(@PathVariable(name = "number") int number){
        return staffService.deleteStaffById(number);
    }
}
