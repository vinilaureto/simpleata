package br.edu.ifsp.aluno.domain.usecases.comment;

import br.edu.ifsp.aluno.domain.entities.comment.Comment;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityNotFoundException;

public class DeleteCommentUseCase {
    private CommentDAO commentDAO;

    public DeleteCommentUseCase(CommentDAO votingDAO) {
        this.commentDAO = votingDAO;
    }

    public boolean delete(Integer id) {
        if (id == null || commentDAO.findOne(id).isEmpty()) {
            throw new EntityNotFoundException("Comment not found.");
        }
        return commentDAO.deleteByKey(id);
    }

    public boolean delete(Comment comment) {
        if (comment == null || commentDAO.findOne(comment.getId()).isEmpty()) {
            throw new EntityNotFoundException("Schedule not found.");
        }
        return commentDAO.delete(comment);
    }
}
