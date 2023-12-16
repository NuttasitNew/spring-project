package web.dao;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import web.Staff;

import java.util.List;

@Repository
public interface StaffDao extends CrudRepository<Staff,Integer> {

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
