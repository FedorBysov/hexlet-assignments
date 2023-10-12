package exercise.controller.users;
import exercise.Data;
import exercise.model.Post;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

 */

// BEGIN

@RestController
@RequestMapping("/api")
class PostsController{

    List<Post> data = Data.getPosts();

    @GetMapping("/users/{id}/posts")
    public List<Post> show(@PathVariable int id){
        var stream = data.stream().filter(a ->a.getUserId() == id).toList();
        return stream;
    }

    @GetMapping("/users/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@PathVariable int id, @RequestBody Post posts){
        posts.setUserId(id);
        data.add(posts);
        return posts;
    }

}

// END
