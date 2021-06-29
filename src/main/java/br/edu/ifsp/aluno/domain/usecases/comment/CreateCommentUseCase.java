package br.edu.ifsp.aluno.domain.usecases.comment;

import br.edu.ifsp.aluno.domain.entities.comment.Comment;
import br.edu.ifsp.aluno.domain.usecases.utils.Notification;
import br.edu.ifsp.aluno.domain.usecases.utils.Validator;

public class CreateCommentUseCase {
    private CommentDAO commentDAO;

    public CreateCommentUseCase(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public Integer insert(Comment comment) {
        Validator<Comment> validator = new CommentInputRequestValidator();
        Notification notification = validator.validate(comment);

        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }

        return commentDAO.insert(comment);
    }
}
