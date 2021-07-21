package br.edu.ifsp.aluno.domain.usecases.vote;

import br.edu.ifsp.aluno.domain.entities.vote.Vote;
import br.edu.ifsp.aluno.domain.entities.voting.Voting;
import br.edu.ifsp.aluno.domain.usecases.utils.Notification;
import br.edu.ifsp.aluno.domain.usecases.utils.Validator;

public class VoteInputRequestValidator extends Validator<Vote> {
    @Override
    public Notification validate(Vote vote) {
        Notification notification = new Notification();

        if (vote == null) {
            notification.addError("Vote is null.");
            return notification;
        }
//        if (vote.getId() == null) {
//            notification.addError("Vote ID can't be 0.");
//        }
        if (vote.getValue() == null) {
            notification.addError("Voting value can't be null");
        }

        return notification;
    }
}
