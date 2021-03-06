package br.edu.ifsp.aluno.domain.usecases.comment;

import br.edu.ifsp.aluno.domain.entities.comment.Comment;
import br.edu.ifsp.aluno.domain.entities.schedule.Schedule;

import java.util.List;
import java.util.Optional;

public class FindCommentUseCase {
    private CommentDAO commentDAO;

    public FindCommentUseCase(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public Optional<Comment> findOne(Integer id) {
        if (id == 0) {
            throw new IllegalArgumentException("Comment ID can not be 0.");
        }
        return commentDAO.findOne(id);
    }

    public List<Comment> findBySchedule(Schedule schedule) {
        return commentDAO.findBySchedule(schedule);
    }

    public List<Comment> findAll() {
        return commentDAO.findAll();
    }
}
