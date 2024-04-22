package dk.kea.dat3js.hogwarts5.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    public List<Student> findAllByPrefectIsTrue();
}
