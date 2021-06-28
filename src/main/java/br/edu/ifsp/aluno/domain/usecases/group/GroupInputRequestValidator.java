package br.edu.ifsp.aluno.domain.usecases.group;

import br.edu.ifsp.aluno.domain.entities.group.Group;
import br.edu.ifsp.aluno.domain.usecases.utils.Notification;
import br.edu.ifsp.aluno.domain.usecases.utils.Validator;

public class GroupInputRequestValidator extends Validator<Group> {
    @Override
    public Notification validate(Group group) {
        Notification notification = new Notification();

        if (group == null) {
            notification.addError("Group is null.");
            return notification;
        }
        if (isNullOrEmpty(group.getName())) {
            notification.addError("Name group is null or empty.");
        }

        return notification;
    }
}
