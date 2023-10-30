package exercise.controller;

import exercise.dto.CommentDto;
import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    // BEGIN
    @GetMapping("/{postId}/comments")
    public Iterable<Comment> getAllComments(@PathVariable long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @GetMapping("/{postId}/comments/{commentId}")
    public Comment getComment(@PathVariable long postId, @PathVariable long commentId) {
        return commentRepository.findByIdAndPostId(postId, commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
    }

    @PostMapping("/{postId}/comments")
    public Comment createComment(@PathVariable long postId, @RequestBody CommentDto dto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        Comment comment = new Comment();
        comment.setContent(dto.content());

        comment.setPost(post);
        return commentRepository.save(comment);
    }

    @PatchMapping(path = "/{postId}/comments/{commentId}")
    public Comment updateComment(@PathVariable long postId, @PathVariable long commentId, @RequestBody CommentDto dto) {

//        Comment comment = commentRepository.findByIdAndPostId(postId, commentId)
//                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
//
//
//        comment.setContent(dto.content());
//        return commentRepository.save(comment);

        Comment comment = commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

        comment.setContent(dto.content());
        return commentRepository.save(comment);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public void destroyComment(@PathVariable long postId, @PathVariable long commentId) {
        Comment comment = commentRepository.findByIdAndPostId(postId, commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        commentRepository.delete(comment);
    }
    // END
}
