package br.edu.ifsp.aluno.domain.usecases.comment;

import br.edu.ifsp.aluno.domain.entities.comment.Comment;
import br.edu.ifsp.aluno.domain.entities.schedule.Schedule;
import br.edu.ifsp.aluno.domain.usecases.utils.DAO;

import java.util.List;

public interface CommentDAO extends DAO<Comment, Integer> {
    List<Comment> findBySchedule(Schedule schedule);
}
