package br.edu.ifsp.aluno.aplication.repository;

import br.edu.ifsp.aluno.domain.entities.comment.Comment;
import br.edu.ifsp.aluno.domain.usecases.comment.CommentDAO;

import java.util.*;

public class InMemoryCommentDAO implements CommentDAO {
    private static final Map<Integer, Comment> db = new LinkedHashMap<>();

    @Override
    public Integer insert(Comment comment) {
        Integer id = comment.getId();
        db.put(id, comment);
        return id;
    }

    @Override
    public Optional<Comment> findOne(Integer id) {
        if (db.containsKey(id)) {
            return Optional.of(db.get(id));
        }
        return Optional.empty();
    }

    @Override
    public List<Comment> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Comment comment) {
        Integer id = comment.getId();
        if (db.containsKey(id)) {
            db.replace(id, comment);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer id) {
        if (db.containsKey(id)) {
            db.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Comment comment) {
        return deleteByKey(comment.getId());
    }
}
