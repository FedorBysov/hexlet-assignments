package exercise.controller;

import exercise.dto.AuthorDTO;
import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.mapper.AuthorMapper;
import exercise.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

    @Autowired
    private AuthorService authorService;

    // BEGIN

    @Autowired
    private AuthorMapper authorMapper;

    @Autowired
    private AuthorService service;



//
//    @GetMapping("")
//    @ResponseStatus(HttpStatus.OK)
//    public List<AuthorDTO> index() {
//        return service.getAll();
//    }
//
//
//    @GetMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public AuthorDTO show(@PathVariable long id) {
//        return service.findById(id);
//    }
//
//
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void delete(@PathVariable long id) {
//        service.delete(id);
//    }
//
//
//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public AuthorDTO update(@Valid @PathVariable long id, @RequestParam AuthorUpdateDTO authorUpdateDTO) {
//        return service.update(id, authorUpdateDTO);
//    }
//
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public AuthorDTO create(@Valid @RequestParam AuthorCreateDTO authorCreateDTO) {
//        return service.create(authorCreateDTO);
//    }

    @GetMapping(path = "")
    public List<AuthorDTO> index() {
        return authorService.getAll();
    }

    @GetMapping(path = "/{id}")
    public AuthorDTO show(@PathVariable long id) {
        return authorService.findById(id);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDTO create(@Valid @RequestBody AuthorCreateDTO authorData) {
        return authorService.create(authorData);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    AuthorDTO update(@RequestBody @Valid AuthorUpdateDTO authorData, @PathVariable Long id) {
        return authorService.update(id,authorData);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void destroy(@PathVariable Long id) {
        authorService.delete(id);
    }
    // END
}
