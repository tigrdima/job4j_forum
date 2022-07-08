package ru.job4j.forum.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.MemRepository;

import java.util.Collection;
import java.util.Optional;

@ThreadSafe
@Service
public class MemService {
    private final MemRepository memRepositoryPosts;

    public MemService(MemRepository posts) {
        this.memRepositoryPosts = posts;
    }

    public Collection<Post> getAllPosts() {
        return memRepositoryPosts.getAllPosts();
    }

    public void addPost(Post post, User user) {
        memRepositoryPosts.addPost(post, user);
    }

    public Post findPostById(int id) {
        return memRepositoryPosts.findPostById(id);
    }

    public void addCommentToPost(Post post, Comment comment, User user) {
        memRepositoryPosts.addCommentToPost(post, comment, user);
    }

    public Collection<Comment> findAllCommentsByPost(Post post) {
        return memRepositoryPosts.findAllCommentsByPost(post);
    }

    public void updatePost(Post post) {
        memRepositoryPosts.updatePost(post);
    }

    public void addUser(User user) {
       memRepositoryPosts.addUser(user);
    }

    public boolean findContainsUserInMem(User user) {
        return memRepositoryPosts.findContainsUserInMem(user);
    }

    public Optional<User> findUserByEmail(String eMail) {
        return memRepositoryPosts.findUserByEmail(eMail);
    }
}
