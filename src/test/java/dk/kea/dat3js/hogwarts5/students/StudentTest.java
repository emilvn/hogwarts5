package dk.kea.dat3js.hogwarts5.students;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void getFullNameWithMiddleName() {
        var student = new Student("Firstname", "Middlename", "Lastname", null, 0);

        var fullName = student.getFullName();

        assertEquals(fullName, "Firstname Middlename Lastname");
    }

    @Test
    void getFullNameWithNoMiddleName(){
        var student = new Student("Firstname", "Lastname", null, 0);

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
        var student = new Student("Firstname", "Middlename", "Lastname", null, 0);

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
}