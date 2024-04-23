package dk.kea.dat3js.hogwarts5.student;

import dk.kea.dat3js.hogwarts5.house.House;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void getFullNameWithMiddleName() {
        var student = new Student("Firstname", "Middlename", "Lastname", true, null, 0);

        var fullName = student.getFullName();

        assertEquals(fullName, "Firstname Middlename Lastname");
    }

    @Test
    void getFullNameWithNoMiddleName(){
        var student = new Student("Firstname", "Lastname", true, null, 0);

        var fullName = student.getFullName();

        assertEquals(fullName, "Firstname Lastname");
    }

    @Test
    void setFullNameWithEmptyString(){
        var student = new Student();

        student.setFullName("");

        assertEquals(student.getFirstName(), "");
        assertNull(student.getMiddleName());
        assertNull(student.getLastName());
    }

    @Test
    void setFullNameWithNull(){
        var student = new Student("Firstname", "Middlename", "Lastname", true, null, 0);

        student.setFullName(null);

        assertEquals(student.getFirstName(), "Firstname");
        assertEquals(student.getMiddleName(), "Middlename");
        assertEquals(student.getLastName(),"Lastname");
    }

    @Test
    void setFullNameWithOnlyFirstName(){
        var student = new Student();

        student.setFullName("Firstname");

        assertEquals(student.getFirstName(), "Firstname");
        assertNull(student.getMiddleName());
        assertNull(student.getLastName());
    }

    @Test
    void setFullNameWithMiddleName() {
        var student = new Student();

        student.setFullName("Firstname Middlename Lastname");

        assertEquals(student.getFirstName(), "Firstname");
        assertEquals(student.getMiddleName(), "Middlename");
        assertEquals(student.getLastName(), "Lastname");
    }

    @Test
    void setFullNameWithMultipleMiddleNames(){
        var student = new Student();

        student.setFullName("Firstname Middlename1 Middlename2 Middlename3 Lastname");

        assertEquals(student.getFirstName(), "Firstname");
        assertEquals(student.getMiddleName(), "Middlename1 Middlename2 Middlename3");
        assertEquals(student.getLastName(), "Lastname");
    }

    @Test
    void setFullNameWithoutMiddleName(){
        var student = new Student();

        student.setFullName("Firstname Lastname");

        assertEquals(student.getFirstName(), "Firstname");
        assertNull(student.getMiddleName());
        assertEquals(student.getLastName(), "Lastname");

    }

    @Test
    void capitalizeSingleWord(){
        var student = new Student();

        var capitalized = student.capitalize("cApiTalIze");

        assertEquals(capitalized, "Capitalize");
    }

    @Test
    void capitalizeMultipleWords(){
        var student = new Student();

        var capitalized = student.capitalize("cApiTalIze tHiS StrInG");

        assertEquals(capitalized, "Capitalize This String");
    }

    @Test
    void setPrefectTooLowSchoolyear(){
        var house = new House("House", "Founder", new String[] {"white", "black"});
        var student = new Student("first", "last", true, house, 4);

        student.setPrefect(true);

        assertFalse(student.isPrefect());
    }

    @Test
    void setPrefectFifthSchoolyear(){
        var house = new House("House", "Founder", new String[] {"white", "black"});
        var student = new Student("first", "last", true, house, 5);

        student.setPrefect(true);

        assertTrue(student.isPrefect());
    }

    @Test
    void setPrefectSameGender(){
        var house = new House("House", "Founder", new String[] {"white", "black"});
        var maleStudent1 = new Student("first", "last", true, house, 5);
        var maleStudent2 = new Student("first", "last", true, house, 5);
        house.setPrefects(List.of(maleStudent2));

        maleStudent1.setPrefect(true);

        assertFalse(maleStudent1.isPrefect());
    }

    @Test
    void setPrefectAlreadyTwoPrefects(){
        var house = new House("House", "Founder", new String[] {"white", "black"});
        var student1 = new Student("first", "last", true, house, 5);
        var student2 = new Student("first", "last", true, house, 5);
        var student3 = new Student("first", "last", false, house, 5);
        house.setPrefects(List.of(student2, student3));

        student1.setPrefect(true);

        assertFalse(student1.isPrefect());
    }

    @Test
    void setPrefectDifferentGender(){
        var house = new House("House", "Founder", new String[] {"white", "black"});
        var maleStudent = new Student("first", "last", true, house, 5);
        var femaleStudent = new Student("first", "last", false, house, 5);
        house.setPrefects(new ArrayList<>(List.of(femaleStudent)));

        maleStudent.setPrefect(true);

        assertTrue(maleStudent.isPrefect());
    }

    @Test
    void removePrefectStatus(){
        var house = new House("House", "Founder", new String[] {"white", "black"});
        var student1 = new Student("first", "last", true, house, 5);
        var student2 = new Student("first", "last", false, house, 5);
        student1.setId(1);
        student2.setId(2);
        house.setPrefects(List.of(student1, student2));

        student1.setPrefect(false);

        assertFalse(student1.isPrefect());
        assertEquals(house.getPrefects().size(), 1);
    }
}