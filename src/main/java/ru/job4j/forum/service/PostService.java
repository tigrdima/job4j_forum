package ru.job4j.forum.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

@ThreadSafe
@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAllPosts() {
        List<Post> posts = new ArrayList<>();
        postRepository.findAll(Sort.by("id")).forEach(posts::add);
        return posts;
    }

    public Post findPostById(int id) {
        return postRepository.findById(id).get();
    }

    public void savePost(Post post, User user) {
        post.setUser(user);
        postRepository.save(post);
    }

    public void addCommentToPost(Post post, Comment comment, User user) {
        comment.setUser(user);
        post.getComments().add(comment);
        postRepository.save(post);
    }
}
