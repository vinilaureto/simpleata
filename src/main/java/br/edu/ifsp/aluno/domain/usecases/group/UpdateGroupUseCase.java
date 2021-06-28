package br.edu.ifsp.aluno.domain.usecases.group;

import br.edu.ifsp.aluno.domain.entities.group.Group;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.aluno.domain.usecases.utils.Notification;
import br.edu.ifsp.aluno.domain.usecases.utils.Validator;

public class UpdateGroupUseCase {
    private GroupDAO groupDAO;

    public UpdateGroupUseCase(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

    public boolean update(Group group) {
        Validator<Group> validator = new GroupInputRequestValidator();
        Notification notification = validator.validate(group);

        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }

        String name = group.getName();
        if (groupDAO.findOne(name).isEmpty()) {
            throw new EntityNotFoundException("Group not found.");
        }

        return groupDAO.update(group);
    }
}
