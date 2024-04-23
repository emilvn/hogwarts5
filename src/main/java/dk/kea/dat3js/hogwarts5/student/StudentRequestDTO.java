package dk.kea.dat3js.hogwarts5.student;

public record StudentRequestDTO(Integer id, String firstName, String middleName, String lastName, String name, Boolean isMale, String house, Integer schoolYear, Boolean isPrefect) {
}
