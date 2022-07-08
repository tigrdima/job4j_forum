package ru.job4j.forum.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
@Repository
public class MemRepository {
    private final Map<Integer, Post> posts = new HashMap<>();
    private final Map<String, User> users = new HashMap<>();
    private final AtomicInteger postIds = new AtomicInteger(3);
    private final AtomicInteger commentIds = new AtomicInteger(3);
    private final AtomicInteger userIds = new AtomicInteger(3);

    public MemRepository() {
        User user1 = User.of(1, "Vova", "vova@vova.ru", "v");
        User user2 = User.of(2, "Dima", "dima@dima.ru", "d");
        User user3 = User.of(3, "Olya", "olya@olya.ru", "o");

        users.put(user1.geteMail(), user1);
        users.put(user2.geteMail(), user2);
        users.put(user3.geteMail(), user3);

        Post post1 = Post.of(1, "Post1", "Продаю машину ладу 01.", user1);
        Post post2 = Post.of(2, "Post2", "Продаю машину ладу 02.", user2);
        Post post3 = Post.of(3, "Post3", "Продаю машину ладу 03.", user3);
        posts.put(1, post1);
        posts.put(2, post2);
        posts.put(3, post3);

        post1.getComments().add(Comment.of(1, "Comment1", "nhfnhfnf", user1));
        post1.getComments().add(Comment.of(2, "Comment2", "anananan", user3));

    }

    public Collection<Post> getAllPosts() {
        return posts.values();
    }

    public void addPost(Post post, User user) {
        int id = postIds.incrementAndGet();
        post.setId(id);
        post.setUser(user);
        posts.putIfAbsent(id, post);
    }

    public Post findPostById(int id) {
        return posts.get(id);
    }

    public void addCommentToPost(Post post, Comment comment, User user) {
        int id = commentIds.incrementAndGet();
        comment.setId(id);
        comment.setUser(user);
        post.getComments().add(comment);
    }

    public Collection<Comment> findAllCommentsByPost(Post post) {
        return post.getComments();
    }

    public void updatePost(Post post) {
        Post rsl = posts.get(post.getId());
        rsl.setDescription(post.getDescription());
        posts.replace(post.getId(), rsl);
    }

    public void addUser(User user) {
        int id = userIds.incrementAndGet();
        user.setId(id);
        users.put(user.geteMail(), user);
    }

    public boolean findContainsUserInMem(User user) {
       User rsl = users.get(user.geteMail());
       return rsl.getPassword().equals(user.getPassword());
    }

    public Optional<User> findUserByEmail(String eMail) {
        return Optional.ofNullable(users.get(eMail));
    }
}
