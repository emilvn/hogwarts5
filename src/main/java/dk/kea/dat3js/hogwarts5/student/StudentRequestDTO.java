package dk.kea.dat3js.hogwarts5.student;

public record StudentRequestDTO(int id, String firstName, String middleName, String lastName, String name, Boolean isMale, String house, Integer schoolYear) {
}
