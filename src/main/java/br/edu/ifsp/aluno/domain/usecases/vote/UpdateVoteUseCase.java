package br.edu.ifsp.aluno.domain.usecases.vote;

import br.edu.ifsp.aluno.domain.entities.vote.Vote;
import br.edu.ifsp.aluno.domain.entities.voting.Voting;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.aluno.domain.usecases.utils.Notification;
import br.edu.ifsp.aluno.domain.usecases.utils.Validator;

public class UpdateVoteUseCase {
    private VoteDAO voteDAO;

    public UpdateVoteUseCase(VoteDAO voteDAO) {
        this.voteDAO = voteDAO;
    }

    public boolean update(Vote vote) {
        Validator<Vote> validator = new VoteInputRequestValidator();
        Notification notification = validator.validate(vote);

        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }

        Integer id = vote.getId();
        if (voteDAO.findOne(id).isEmpty()) {
            throw new EntityNotFoundException("Vote not found.");
        }

        return voteDAO.update(vote);
    }
}
