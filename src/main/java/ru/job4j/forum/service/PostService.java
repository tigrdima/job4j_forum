package ru.job4j.forum.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.data.domain.Sort;
import org.springframework.expression.spel.support.ReflectivePropertyAccessor;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.PostRepository;
import ru.job4j.forum.repository.UserRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@ThreadSafe
@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<Post> findAllPosts() {
        List<Post> posts = new ArrayList<>();
        postRepository.findAll(Sort.by("id")).forEach(posts::add);
        return posts;
    }

    public Post findPostById(int id) {
        return postRepository.findById(id).get();
    }

    public void savePost(Post post, Principal user) {
        User rsl = userRepository.findByEmail(user.getName()).get();
        post.setUser(rsl);
        postRepository.save(post);
    }

    public void addCommentToPost(Post post, Comment comment, Principal user) {
        User rsl = userRepository.findByEmail(user.getName()).get();
        comment.setUser(rsl);
        post.getComments().add(comment);
        postRepository.save(post);
    }

    public List<Post> findAllPostsByUserId(int id) {
        List<Post> postsUser = new ArrayList<>();
        postRepository.findPostsByUserId(id).forEach(postsUser::add);
        return postsUser;
    }

    public void deletePost(int id) {
        postRepository.deleteById(id);
    }
}
