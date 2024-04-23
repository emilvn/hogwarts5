package dk.kea.dat3js.hogwarts5.prefect;

import dk.kea.dat3js.hogwarts5.student.StudentRequestDTO;
import dk.kea.dat3js.hogwarts5.student.StudentResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getPrefectById(@PathVariable Integer id){
        return ResponseEntity.of(prefectService.findById(id));
    }

    @GetMapping("/house/{houseName}")
    public ResponseEntity<List<StudentResponseDTO>> getPrefectsByHouse(@PathVariable String houseName){
        return ResponseEntity.ok(prefectService.findAllByHouseName(houseName));
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> setPrefect(@RequestBody StudentRequestDTO studentDto){
        return ResponseEntity.of(prefectService.setPrefect(studentDto.id()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> removePrefectStatus(@PathVariable Integer id){
        var student = prefectService.removePrefectStatus(id).orElse(null);
        if(student == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
