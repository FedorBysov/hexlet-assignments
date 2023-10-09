package exercise;

import java.util.List;
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
    public List<Post> index(@RequestParam(defaultValue = "10") Integer limit ){
        return posts.stream().toList();
    }

    @GetMapping("/pages/{id}")
    public Stream<Post> show(@PathVariable Integer id){
        Stream<Post> stream = posts.stream()
                .filter(a -> a.getId().equals(id))
                .limit(1);
        return stream;

    }

    @PostMapping("/pages")
    public Post create(@RequestParam Post post){
        posts.add(post);
        return post;
    }

    @DeleteMapping("/pages/{id}")
    public void destroy(@RequestParam String id){
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