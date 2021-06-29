package br.edu.ifsp.aluno.domain.usecases.voting;

import br.edu.ifsp.aluno.domain.entities.schedule.Schedule;
import br.edu.ifsp.aluno.domain.entities.voting.Voting;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.aluno.domain.usecases.utils.Notification;
import br.edu.ifsp.aluno.domain.usecases.utils.Validator;

public class UpdateVotingUseCase {
    private VotingDAO votingDAO;

    public UpdateVotingUseCase(VotingDAO votingDAO) {
        this.votingDAO = votingDAO;
    }

    public boolean update(Voting voting) {
        Validator<Voting> validator = new VotingInputRequestValidator();
        Notification notification = validator.validate(voting);

        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }

        Integer id = voting.getId();
        if (votingDAO.findOne(id).isEmpty()) {
            throw new EntityNotFoundException("Voting not found.");
        }

        return votingDAO.update(voting);
    }
}
