package br.edu.ifsp.aluno.domain.usecases.vote;

import br.edu.ifsp.aluno.domain.entities.vote.Vote;
import br.edu.ifsp.aluno.domain.usecases.utils.DAO;

public interface VoteDAO extends DAO<Vote, Integer> {
}
