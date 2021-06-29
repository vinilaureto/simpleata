package br.edu.ifsp.aluno.domain.usecases.voting;

import br.edu.ifsp.aluno.domain.entities.schedule.Schedule;
import br.edu.ifsp.aluno.domain.entities.voting.Voting;
import br.edu.ifsp.aluno.domain.usecases.utils.Notification;
import br.edu.ifsp.aluno.domain.usecases.utils.Validator;

public class VotingInputRequestValidator extends Validator<Voting> {
    @Override
    public Notification validate(Voting voting) {
        Notification notification = new Notification();

        if (voting == null) {
            notification.addError("Voting is null.");
            return notification;
        }
        if (voting.getId() == 0) {
            notification.addError("Voting ID can't be 0.");
        }
        if (voting.getResult() == null) {
            notification.addError("Voting result can't be null");
        }

        return notification;
    }
}
