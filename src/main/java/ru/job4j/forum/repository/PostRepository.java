package ru.job4j.forum.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.Post;

@ThreadSafe
public interface PostRepository extends CrudRepository<Post, Integer> {
    @Query("select p from Post p")
    Iterable<Post> findAll(Sort sort);
}
