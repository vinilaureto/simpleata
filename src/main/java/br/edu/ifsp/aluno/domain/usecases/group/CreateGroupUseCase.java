package br.edu.ifsp.aluno.domain.usecases.group;

import br.edu.ifsp.aluno.domain.entities.group.Group;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityAlreadyExistsException;
import br.edu.ifsp.aluno.domain.usecases.utils.Notification;
import br.edu.ifsp.aluno.domain.usecases.utils.Validator;

public class CreateGroupUseCase {
    private GroupDAO groupDAO;

    public CreateGroupUseCase(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

    public Integer insert(Group group) {
        Validator<Group> validator = new GroupInputRequestValidator();
        Notification notification = validator.validate(group);

        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }

        String name = group.getName();
        if (groupDAO.findByName(name).isPresent()) {
            throw new EntityAlreadyExistsException("This group name is already in use");
        }
        return groupDAO.insert(group);
    }
}
