package dk.kea.dat3js.hogwarts5.prefect;

import dk.kea.dat3js.hogwarts5.house.HouseService;
import dk.kea.dat3js.hogwarts5.student.StudentResponseDTO;
import dk.kea.dat3js.hogwarts5.student.StudentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<StudentResponseDTO> findAllByHouseName(String houseName){
        return studentService.findAllPrefectsByHouseName(houseName);
    }

    public Optional<StudentResponseDTO> setPrefect(Integer id){
        return studentService.setPrefect(id);
    }

    public Optional<StudentResponseDTO> removePrefectStatus(Integer id){
        return studentService.removePrefectStatus(id);
    }

    //TODO: DELETE /prefects/:id - fratager den pågældende student rollen som prefect.
}
