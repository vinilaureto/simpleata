package br.edu.ifsp.aluno.aplication.repository.sqlite.DAO;

import br.edu.ifsp.aluno.domain.entities.comment.Comment;
import br.edu.ifsp.aluno.domain.entities.schedule.Schedule;
import br.edu.ifsp.aluno.domain.usecases.comment.CommentDAO;

import java.util.List;
import java.util.Optional;

public class SqliteCommentDAO implements CommentDAO {
    @Override
    public List<Comment> findBySchedule(Schedule schedule) {
        return null;
    }

    @Override
    public Integer insert(Comment type) {
        return null;
    }

    @Override
    public Optional<Comment> findOne(Integer key) {
        return Optional.empty();
    }

    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public boolean update(Comment type) {
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        return false;
    }

    @Override
    public boolean delete(Comment type) {
        return false;
    }
}
