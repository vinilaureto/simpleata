package br.edu.ifsp.aluno.domain.usecases.vote;

import br.edu.ifsp.aluno.domain.entities.vote.Vote;
import br.edu.ifsp.aluno.domain.usecases.utils.Notification;
import br.edu.ifsp.aluno.domain.usecases.utils.Validator;

public class CreateVoteUseCase {
    private VoteDAO voteDAO;

    public CreateVoteUseCase(VoteDAO voteDAO) {
        this.voteDAO = voteDAO;
    }

    public Integer insert(Vote vote) {
        Validator<Vote> validator = new VoteInputRequestValidator();
        Notification notification = validator.validate(vote);

        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }

        return voteDAO.insert(vote);
    }
}
