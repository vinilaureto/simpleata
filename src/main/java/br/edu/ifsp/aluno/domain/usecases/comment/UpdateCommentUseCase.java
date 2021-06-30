package br.edu.ifsp.aluno.domain.usecases.comment;

import br.edu.ifsp.aluno.domain.entities.comment.Comment;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.aluno.domain.usecases.utils.Notification;
import br.edu.ifsp.aluno.domain.usecases.utils.Validator;

public class UpdateCommentUseCase {
    private CommentDAO commentDAO;

    public UpdateCommentUseCase(CommentDAO votingDAO) {
        this.commentDAO = votingDAO;
    }

    public boolean update(Comment comment) {
        Validator<Comment> validator = new CommentInputRequestValidator();
        Notification notification = validator.validate(comment);

        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }

        Integer id = comment.getId();
        if (commentDAO.findOne(id).isEmpty()) {
            throw new EntityNotFoundException("Comment not found.");
        }

        return commentDAO.update(comment);
    }
}
