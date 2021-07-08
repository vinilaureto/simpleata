package br.edu.ifsp.aluno.domain.entities.inform;

public class Inform {
    private Integer id;
    private String description;

    public Inform() {
    }

    public Inform(String description) {
        this.description = description;
    }

    public Inform(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Inform{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
