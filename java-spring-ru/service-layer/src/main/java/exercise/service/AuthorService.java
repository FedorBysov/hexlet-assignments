package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class AuthorService {
    // BEGIN
    @Autowired
    private AuthorMapper authorMapper;

    @Autowired
    private AuthorRepository authorRepository;

    public AuthorDTO create( AuthorCreateDTO authorCreateDTO){
        var dto = authorMapper.map(authorCreateDTO);
        authorRepository.save(dto);
        var result = authorMapper.map(dto);
        return result;

    }


    public List<AuthorDTO> getAll(){
        var dto = authorRepository.findAll();
        var result = dto.stream().map(authorMapper::map).toList();
        return result;
    }



    public AuthorDTO findById(long id){
        var dto = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author with id " + id + " not found"));
        var result = authorMapper.map(dto);
        return result;
    }

    public AuthorDTO update(long id, AuthorUpdateDTO authorUpdateDTO){
        var dto = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not Found: " + id));
        authorMapper.update(authorUpdateDTO, dto);
        authorRepository.save(dto);
        var result = authorMapper.map(dto);
        return result;


    }


    public void delete( long id){
        authorRepository.deleteById(id);
    }
    // END
}
