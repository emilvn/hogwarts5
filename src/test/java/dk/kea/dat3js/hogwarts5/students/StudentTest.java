package dk.kea.dat3js.hogwarts5.students;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void getFullNameWithMiddleName() {
        var student = new Student("Firstname", "Middlename", "Lastname", null, 0);

        var fullName = student.getFullName();
        var firstName = student.getFirstName();
        var middleName = student.getMiddleName();
        var lastName = student.getLastName();

        assertEquals(fullName, "Firstname Middlename Lastname");
        assertEquals(firstName, "Firstname");
        assertEquals(middleName, "Middlename");
        assertEquals(lastName, "Lastname");
    }

    @Test
    void getFullNameWithNoMiddleName(){
        var student = new Student("Firstname", "Lastname", null, 0);

        var fullName = student.getFullName();
        var firstName = student.getFirstName();
        var middleName = student.getMiddleName();
        var lastName = student.getLastName();

        assertEquals(fullName, "Firstname Lastname");
        assertEquals(firstName, "Firstname");
        assertNull(middleName);
        assertEquals(lastName, "Lastname");
    }

    @Test
    void setFullNameWithEmptyString(){
        var student = new Student();

        student.setFullName("");
        var firstName = student.getFirstName();
        var middleName = student.getMiddleName();
        var lastName = student.getLastName();

        assertEquals(firstName, "");
        assertNull(middleName);
        assertNull(lastName);
    }

    @Test
    void setFullNameWithNull(){
        var student = new Student("Firstname", "Lastname", null, 0);

        student.setFullName(null);
        var firstName = student.getFirstName();
        var middleName = student.getMiddleName();
        var lastName = student.getLastName();

        assertEquals(firstName, "Firstname");
        assertNull(middleName);
        assertEquals(lastName,"Lastname");
    }

    @Test
    void setFullNameWithOnlyFirstName(){
        var student = new Student();

        student.setFullName("Firstname");
        var firstName = student.getFirstName();
        var middleName = student.getMiddleName();
        var lastName = student.getLastName();

        assertEquals(firstName, "Firstname");
        assertNull(middleName);
        assertNull(lastName);
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
        var firstName = student.getFirstName();
        var middleName = student.getMiddleName();
        var lastName = student.getLastName();

        assertEquals(firstName, "Firstname");
        assertNull(middleName);
        assertEquals(lastName, "Lastname");

    }

    @Test
    void capitalizeNamePartsWithOneMiddleName(){
        var student = new Student("fiRsTnAmE", "MiDdLenAme", "lAstnAme", null, 0);

        var firstName = student.getFirstName();
        var middleName = student.getMiddleName();
        var lastName = student.getLastName();

        assertEquals(firstName, "Firstname");
        assertEquals(middleName, "Middlename");
        assertEquals(lastName, "Lastname");
    }

    @Test
    void capitalizeNamePartsWithMultipleMiddleNames(){
        var student = new Student("fiRsTnAmE", "MiDdLenAme1 mIddLeNamE2 mIddlEnAme3", "lAstnAme", null, 0);

        var firstName = student.getFirstName();
        var middleName = student.getMiddleName();
        var lastName = student.getLastName();

        assertEquals(firstName, "Firstname");
        assertEquals(middleName, "Middlename1 Middlename2 Middlename3");
        assertEquals(lastName, "Lastname");
    }
}