package dk.kea.dat3js.hogwarts5.house;

import dk.kea.dat3js.hogwarts5.student.Student;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HouseTest {

    @Test
    void addValidPrefectNoExistingPrefects(){
        var house = new House("Gryffindor", "Godric Gryffindor", new String[]{"Red", "Gold"});
        var prefect = new Student("Firstname", "Middlename", "Lastname", true, house, 5);

        var isAdded = house.addPrefect(prefect);

        assertTrue(isAdded);
    }

    @Test
    void addValidPrefectExistingPrefectDifferentGender(){
        var house = new House("Gryffindor", "Godric Gryffindor", new String[]{"Red", "Gold"});
        var prefectMale = new Student("Firstname", "Middlename", "Lastname", true, house, 5);
        var prefectFemale = new Student("Firstname", "Middlename", "Lastname", false, house, 5);
        house.setPrefects(new ArrayList<>(List.of(prefectFemale)));

        var isAdded = house.addPrefect(prefectMale);

        assertTrue(isAdded);
    }

    @Test
    void addValidPrefectExistingPrefectSameGender(){
        var house = new House("Gryffindor", "Godric Gryffindor", new String[]{"Red", "Gold"});
        var prefect1 = new Student("Firstname", "Middlename", "Lastname", true, house, 5);
        var prefect2 = new Student("Firstname", "Middlename", "Lastname", true, house, 5);
        house.setPrefects(new ArrayList<>(List.of(prefect1)));

        var isAdded = house.addPrefect(prefect2);

        assertFalse(isAdded);
    }

    @Test
    void addValidPrefectTwoExistingPrefects(){
        var house = new House("Gryffindor", "Godric Gryffindor", new String[]{"Red", "Gold"});
        var prefect1 = new Student("Firstname", "Middlename", "Lastname", true, house, 5);
        var prefect2 = new Student("Firstname", "Middlename", "Lastname", true, house, 5);
        var prefect3 = new Student("Firstname", "Middlename", "Lastname", true, house, 5);
        house.setPrefects(new ArrayList<>(List.of(prefect1, prefect2)));

        var isAdded = house.addPrefect(prefect3);

        assertFalse(isAdded);
    }

    @Test
    void addPrefectDifferentHouse(){
        var house = new House("Gryffindor", "Godric Gryffindor", new String[]{"Red", "Gold"});
        var differentHouse = new House("Slytherin", "Salazar Slytherin", new String[]{"Green", "Silver"});
        var prefect = new Student("Firstname", "Middlename", "Lastname", true, differentHouse, 5);

        var isAdded = house.addPrefect(prefect);

        assertFalse(isAdded);
    }

    @Test
    void addPrefectTooLowSchoolYear(){
        var house = new House("Gryffindor", "Godric Gryffindor", new String[]{"Red", "Gold"});
        var prefect = new Student("Firstname", "Middlename", "Lastname", true, house, 4);

        var isAdded = house.addPrefect(prefect);

        assertFalse(isAdded);
    }


}
