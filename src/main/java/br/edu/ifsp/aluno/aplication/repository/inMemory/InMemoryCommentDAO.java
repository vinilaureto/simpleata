package br.edu.ifsp.aluno.aplication.repository.inMemory;

import br.edu.ifsp.aluno.domain.entities.comment.Comment;
import br.edu.ifsp.aluno.domain.entities.schedule.Schedule;
import br.edu.ifsp.aluno.domain.usecases.comment.CommentDAO;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryCommentDAO implements CommentDAO {
    private static final Map<Integer, Comment> db = new LinkedHashMap<>();
    private static int commentIdCounter;

    @Override
    public Integer insert(Comment comment) {
        commentIdCounter++;
        comment.setId(commentIdCounter);
        db.put(commentIdCounter, comment);
        return commentIdCounter;
    }

    @Override
    public Optional<Comment> findOne(Integer id) {
        if (db.containsKey(id)) {
            return Optional.of(db.get(id));
        }
        return Optional.empty();
    }

    @Override
    public List<Comment> findBySchedule(Schedule schedule) {
        return null;
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
