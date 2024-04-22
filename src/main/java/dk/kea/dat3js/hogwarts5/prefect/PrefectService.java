package dk.kea.dat3js.hogwarts5.prefect;

import dk.kea.dat3js.hogwarts5.student.StudentResponseDTO;
import dk.kea.dat3js.hogwarts5.student.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrefectService {

    // Reglerne for prefects er:
    //
    //- Der kan max være to prefects pr. house
    //- Prefects i samme house skal have forskelligt køn (Hogwarts skelner normalt kun to køn, men du er velkommen til at tilføje flere)
    //- Alle prefects skal være 5. skoleår eller højere

    StudentService studentService;

    public PrefectService(StudentService studentService){
        this.studentService = studentService;
    }

    public List<StudentResponseDTO> findAll() {
        return studentService.findAllPrefects();
    }

    public Optional<StudentResponseDTO> findById(Integer id){
        if(id == null){
            return Optional.empty();
        }
        return studentService.findPrefectById(id);
    }

    public Optional<StudentResponseDTO> setPrefect(Integer id){
        return studentService.setPrefect(id);
    }

    //TODO: GET /prefects/:id - returnerer en prefect (ud fra student-id) hvis den pågældende student er prefect

    //TODO: GET /prefects/house/{house} - returnerer en liste over alle prefects i det house

    //TODO: DELETE /prefects/:id - fratager den pågældende student rollen som prefect.
}
