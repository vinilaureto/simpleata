package br.edu.ifsp.aluno.domain.entities.schedule;

import br.edu.ifsp.aluno.domain.entities.comment.Comment;
import br.edu.ifsp.aluno.domain.entities.voting.Voting;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private String topic;
    private String description; // entendemos que uma pauta deve conter um tópico e uma descrição do assunto
    private List<Comment> comments = new ArrayList<>();
    private Voting voting;

    public Schedule() {
    }

    public Schedule(String topic, String title) {
        this.topic = topic;
        this.description = title;
    }

    public Schedule(String topic, String title, List<Comment> comments) {
        this.topic = topic;
        this.description = title;
        this.comments = comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Voting getVoting() {
        return voting;
    }

    public void setVoting(Voting voting) {
        this.voting = voting;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "topic='" + topic + '\'' +
                ", description='" + description + '\'' +
                ", comments=" + comments +
                ", voting=" + voting +
                '}';
    }
}
