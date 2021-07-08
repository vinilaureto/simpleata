package br.edu.ifsp.aluno.domain.usecases.group;

import br.edu.ifsp.aluno.domain.entities.group.Group;
import br.edu.ifsp.aluno.domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class FindGroupUseCase {
    private GroupDAO groupDAO;

    public FindGroupUseCase(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

    public Optional<Group> findOne(Integer id) {
        if (id == 0) {
            throw new IllegalArgumentException("Id can't be null or empty");
        }
        return groupDAO.findOne(id);
    }

    public Optional<Group> findByName(String name) {
        if (Validator.isNullOrEmpty(name)) {
            throw new IllegalArgumentException("Name can't be null or empty");
        }
        return groupDAO.findByName(name);
    }

    public List<Group> findAll() {
        return groupDAO.findAll();
    }
}
