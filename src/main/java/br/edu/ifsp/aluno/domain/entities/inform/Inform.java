package br.edu.ifsp.aluno.domain.entities.inform;

public class Inform {
    private String title;
    private String description;

    public Inform() {
    }

    public Inform(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Inform{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
