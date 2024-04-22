package dk.kea.dat3js.hogwarts5.student;

import dk.kea.dat3js.hogwarts5.house.House;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findAllByPrefectIsTrue();
    Optional<Student> findByPrefectTrueAndId(int id);
    List<Student> findByPrefectTrueAndHouse(House house);
}
