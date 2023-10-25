package exercise.service;

import exercise.dto.*;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.mapper.BookMapper;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private BookMapper mapper;

    @Autowired
    private BookRepository repository;

    public BookDTO create(BookCreateDTO bookCreateDTO){
        var dto = mapper.map(bookCreateDTO);
        repository.save(dto);
        var result = mapper.map(dto);
        return result;
    }


    public List<BookDTO> getAll(){
        var dto = repository.findAll();
        var result = dto.stream().map(mapper::map).toList();
        return result;
    }



    public BookDTO findById(long id){
        var dto = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found: " + id));
        var result = mapper.map(dto);
        return result;
    }

    public BookDTO updateBook(BookUpdateDTO bookData, Long id) {
        var book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not Found: " + id));

        mapper.update(bookData, book);
        repository.save(book);
        var bookDto = mapper.map(book);
        return bookDto;
    }


    public void delete( long id){
        repository.deleteById(id);
    }
    // END
}
