package dk.kea.dat3js.hogwarts5.prefect;

import dk.kea.dat3js.hogwarts5.student.StudentResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/prefects")
public class PrefectController {
    PrefectService prefectService;

    public PrefectController(PrefectService prefectService){
        this.prefectService = prefectService;
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllPrefects(){
        return ResponseEntity.ok(prefectService.findAll());
    }

    //TODO: POST /prefects - modtager en student (eller et student-id) og udnævner vedkommende til prefect

    //TODO: GET /prefects/:id - returnerer en prefect (ud fra student-id) hvis den pågældende student er prefect

    //TODO: GET /prefects/house/{house} - returnerer en liste over alle prefects i det house

    //TODO: DELETE /prefects/:id - fratager den pågældende student rollen som prefect.
}