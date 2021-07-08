package br.edu.ifsp.aluno.domain.usecases.group;

import br.edu.ifsp.aluno.domain.entities.group.Group;
import br.edu.ifsp.aluno.domain.usecases.utils.EntityNotFoundException;

public class DeleteGroupUseCase {
    private GroupDAO groupDAO;

    public DeleteGroupUseCase(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

    public boolean delete(Integer id) {
        if (id == 0 || groupDAO.findOne(id).isEmpty()) {
            throw new EntityNotFoundException("Group not found.");
        }
        return groupDAO.deleteByKey(id);
    }

    public boolean delete(Group group) {
        if (group == null || groupDAO.findOne(group.getId()).isEmpty()) {
            throw new EntityNotFoundException("Participant not found.");
        }
        return groupDAO.delete(group);
    }
}
