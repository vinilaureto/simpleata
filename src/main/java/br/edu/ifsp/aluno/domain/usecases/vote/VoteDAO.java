package br.edu.ifsp.aluno.domain.usecases.vote;

import br.edu.ifsp.aluno.domain.entities.vote.Vote;
import br.edu.ifsp.aluno.domain.entities.voting.Voting;
import br.edu.ifsp.aluno.domain.usecases.utils.DAO;

import java.util.List;

public interface VoteDAO extends DAO<Vote, Integer> {
    List<Vote> findByVoting(Voting voting);
}
