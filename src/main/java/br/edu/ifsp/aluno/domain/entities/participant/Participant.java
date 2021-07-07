package br.edu.ifsp.aluno.domain.entities.participant;

public class Participant {
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
        return "\n\t Nome: " + name
                +"\n\t\t E-mail: " + email
                +"\n\t\t Título: " + title;
    }
}
