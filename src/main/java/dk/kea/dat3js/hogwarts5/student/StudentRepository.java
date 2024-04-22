package dk.kea.dat3js.hogwarts5.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findAllByPrefectIsTrue();
    Optional<Student> findByPrefectTrueAndId(int id);
}
