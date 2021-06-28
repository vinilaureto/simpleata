package br.edu.ifsp.aluno.domain.usecases.group;

import br.edu.ifsp.aluno.domain.entities.group.Group;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityNotFoundException;

public class DeleteGroupUseCase {
    private GroupDAO groupDAO;

    public DeleteGroupUseCase(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

    public boolean delete(String name) {
        if (name == null || groupDAO.findOne(name).isEmpty()) {
            throw new EntityNotFoundException("Group not found.");
        }
        return groupDAO.deleteByKey(name);
    }

    public boolean delete(Group group) {
        if (group == null || groupDAO.findOne(group.getName()).isEmpty()) {
            throw new EntityNotFoundException("Participant not found.");
        }
        return groupDAO.delete(group);
    }
}
