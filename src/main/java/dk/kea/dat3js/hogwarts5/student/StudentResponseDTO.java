package dk.kea.dat3js.hogwarts5.student;

public record StudentResponseDTO(int id, String firstName, String middleName, String lastName, String fullName, String house, Integer schoolYear, Boolean isPrefect) {
}
