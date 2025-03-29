package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Comment;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Optional<List<Comment>> findAllByAdPk(int ad);

    Optional<Comment> findByAdPkAndPk(int adPk, int pk);

    void deleteByAdPkAndPk(int adPk, int pk);

    void deleteAllByAdPk(int adId);
}
