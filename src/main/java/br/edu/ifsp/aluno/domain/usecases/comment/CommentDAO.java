package br.edu.ifsp.aluno.domain.usecases.comment;

import br.edu.ifsp.aluno.domain.entities.comment.Comment;
import br.edu.ifsp.aluno.domain.usecases.utils.DAO;

public interface CommentDAO extends DAO<Comment, Integer> {
}
