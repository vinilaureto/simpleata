package br.edu.ifsp.aluno.domain.usecases.voting;

import br.edu.ifsp.aluno.domain.entities.schedule.Schedule;
import br.edu.ifsp.aluno.domain.entities.voting.Voting;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityNotFoundException;

public class DeleteVotingUseCase {
    private VotingDAO votingDAO;

    public DeleteVotingUseCase(VotingDAO votingDAO) {
        this.votingDAO = votingDAO;
    }

    public boolean delete(Integer id) {
        if (id == null || votingDAO.findOne(id).isEmpty()) {
            throw new EntityNotFoundException("Voting not found.");
        }
        return votingDAO.deleteByKey(id);
    }

    public boolean delete(Voting voting) {
        if (voting == null || votingDAO.findOne(voting.getId()).isEmpty()) {
            throw new EntityNotFoundException("Schedule not found.");
        }
        return votingDAO.delete(voting);
    }
}
