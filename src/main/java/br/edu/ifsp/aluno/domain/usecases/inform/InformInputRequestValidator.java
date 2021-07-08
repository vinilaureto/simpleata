package br.edu.ifsp.aluno.domain.usecases.inform;

import br.edu.ifsp.aluno.domain.entities.inform.Inform;
import br.edu.ifsp.aluno.domain.usecases.utils.Notification;
import br.edu.ifsp.aluno.domain.usecases.utils.Validator;

public class InformInputRequestValidator extends Validator<Inform> {
    @Override
    public Notification validate(Inform inform) {
        Notification notification = new Notification();

        if (inform == null) {
            notification.addError("Inform is null.");
            return notification;
        }
        if (isNullOrEmpty(inform.getDescription())) {
            notification.addError("Inform's description is null or empty.");
        }

        return notification;
    }
}
