package exercise.controller;

import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<PostDTO> index(){
        var post = postRepository.findAll();
        var result = post.stream()
                .map(this::toPostDTO)
                .toList();
        return result;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDTO show(@PathVariable long id){
        var post = postRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Post with id " + id + " not found"));

        return toPostDTO( post);
    }


    private PostDTO toPostDTO(Post post){
        var dto = new PostDTO();
        dto.setId(post.getId());
        dto.setBody(post.getBody());
        dto.setTitle(post.getTitle());

        var comment = commentRepository.findByPostId(post.getId());

        var commentsDto = comment.stream()
                .map((comment1 ->{
                    var commentDto = new CommentDTO();
                    commentDto.setId(comment1.getId());
                    commentDto.setBody(comment1.getBody());
                    return commentDto;
                })).toList();

        dto.setComments(commentsDto);
        return dto;
    }
}
// END
