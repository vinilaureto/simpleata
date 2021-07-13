package br.edu.ifsp.aluno.aplication.repository.inMemory;

import br.edu.ifsp.aluno.domain.entities.group.Group;
import br.edu.ifsp.aluno.domain.usecases.group.GroupDAO;

import java.util.*;

public class InMemoryGroupDAO implements GroupDAO {
    private static final Map<Integer, Group> db = new LinkedHashMap<>();
    private static int groupIdCounter;

    @Override
    public Integer insert(Group group) {
        groupIdCounter++;
        group.setId(groupIdCounter);
        db.put(groupIdCounter, group);
        return groupIdCounter;
    }

    @Override
    public Optional<Group> findOne(Integer id) {
        if (db.containsKey(id)) {
            return Optional.of(db.get(id));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Group> findByName(String name) {
        // TODO: implementar método
        return Optional.empty();
    }

    @Override
    public List<Group> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Group group) {
        Integer id = group.getId();
        if (db.containsKey(id)) {
            db.replace(id, group);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer id) {
        if (db.containsKey(id)) {
            db.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Group group) {
        return deleteByKey(group.getId());
    }
}
