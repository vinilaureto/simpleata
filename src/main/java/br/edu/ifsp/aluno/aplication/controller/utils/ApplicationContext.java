package br.edu.ifsp.aluno.aplication.controller.utils;

import br.edu.ifsp.aluno.domain.entities.group.Group;

public class ApplicationContext {
    private static ApplicationContext applicationContext;

    private Group currentGroup;

    private ApplicationContext() {
    }

    public static ApplicationContext getInstance() {
        if (applicationContext == null){
            applicationContext = new ApplicationContext();
        }
        return applicationContext;
    }

    public Group getCurrentGroup() {
        return currentGroup;
    }

    public void setCurrentGroup(Group currentGroup) {
        this.currentGroup = currentGroup;
    }
}
