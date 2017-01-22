package in.aqel.jamunamailer;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by Ahammad on 22/01/17.
 */

@Table(name = "Student")
public class Student  extends Model {


    @Column(name = "mobileNumber")
    public String mobileNumber;

    @Column(name = "name")
    public String name;

    @Column(name = "rollNumber")
    public String rollNumber;

    @Column(name = "roomNumber")
    public int roomNumber;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public Student() {
    }

    public Student(int roomNumber, String mobileNumber, String name, String rollNumber) {
        this.roomNumber = roomNumber;
        this.mobileNumber = mobileNumber;
        this.name = name;
        this.rollNumber = rollNumber;
    }

    public static Student loadStudent(String name) {
        return new Select()
                .from(Student.class)
                .where("rollNumber LIKE ?", "%" + name + "%")
                .executeSingle();
    }

    public static Student loadStudent(int room) {
        return new Select()
                .from(Student.class)
                .where("roomNumber = ?", room)
                .executeSingle();
    }


    public static List<Student> loadAll(String name) {
        // This is how you execute a query
        return new Select()
                .from(Student.class)
                .where("name LIKE ?", "%" + name + "%")
                .orderBy("name ASC")
                .execute();
    }
}
