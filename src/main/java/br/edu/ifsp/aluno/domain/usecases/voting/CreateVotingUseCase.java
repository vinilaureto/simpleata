package br.edu.ifsp.aluno.domain.usecases.voting;

import br.edu.ifsp.aluno.domain.entities.voting.Voting;
import br.edu.ifsp.aluno.domain.usecases.utils.Notification;
import br.edu.ifsp.aluno.domain.usecases.utils.Validator;

public class CreateVotingUseCase {
    private VotingDAO votingDAO;

    public CreateVotingUseCase(VotingDAO votingDAO) {
        this.votingDAO = votingDAO;
    }

    public Integer insert(Voting voting) {
        Validator<Voting> validator = new VotingInputRequestValidator();
        Notification notification = validator.validate(voting);

        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }

        return votingDAO.insert(voting);
    }
}
