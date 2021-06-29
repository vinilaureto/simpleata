package br.edu.ifsp.aluno.domain.usecases.comment;

import br.edu.ifsp.aluno.domain.entities.comment.Comment;
import br.edu.ifsp.aluno.domain.usecases.utils.Notification;
import br.edu.ifsp.aluno.domain.usecases.utils.Validator;

public class CommentInputRequestValidator extends Validator<Comment> {
    @Override
    public Notification validate(Comment comment) {
        Notification notification = new Notification();

        if (comment == null) {
            notification.addError("Comment is null.");
            return notification;
        }
        if (isNullOrEmpty(comment.getMessage())) {
            notification.addError("Comment message is null or empty.");
        }

        return notification;
    }
}
