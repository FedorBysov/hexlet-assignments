package exercise;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN

    @GetMapping("/pages")
    public List<Post> index(@RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer limit) {

        return posts.stream().skip((page - 1) * limit).limit(limit).toList();
    }

    @GetMapping("/pages/{id}")
    public Optional<Post> show(@PathVariable String id) {
        var stream = posts.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
        return stream;

    }

    @PostMapping("/pages")
    public Post create(@RequestParam Post post) {
        posts.add(post);
        return post;
    }

    @DeleteMapping("/pages/{id}")
    public void destroy(@RequestParam String id) {
        posts.removeIf(r -> r.getId().equals(id));
    }

    @PutMapping("/posts/{id}")
    public Post update(@PathVariable String id, @RequestBody Post data) {
        var maybePost = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        if (maybePost.isPresent()) {
            var post = maybePost.get();
            post.setId(data.getId());
            post.setTitle(data.getTitle());
            post.setBody(data.getBody());
        }
        return data;
    }


    // END
}
