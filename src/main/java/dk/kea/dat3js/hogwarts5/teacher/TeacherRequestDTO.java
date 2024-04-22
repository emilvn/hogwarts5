package dk.kea.dat3js.hogwarts5.teacher;

import java.time.LocalDate;

public record TeacherRequestDTO(int id, String firstName, String middleName, String lastName, String name, String house, String mainSubject, LocalDate employmentDate) {
}

