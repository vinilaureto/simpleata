package br.edu.ifsp.aluno.domain.entities.participant;

public class Participant {
    private Integer id;
    private String name;
    private String email;
    private String title;

    public Participant() {
    }

    public Participant(String name, String email, String title) {
        this.name = name;
        this.email = email;
        this.title = title;
    }

    public Participant(Integer id, String name, String email, String title) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
