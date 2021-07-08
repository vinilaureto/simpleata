package br.edu.ifsp.aluno.domain.entities.schedule;

import br.edu.ifsp.aluno.domain.entities.comment.Comment;
import br.edu.ifsp.aluno.domain.entities.voting.Voting;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private Integer id;
    private String topic;
    private List<Comment> comments = new ArrayList<>();
    private Voting voting;

    public Schedule() {
    }

    public Schedule(String topic, List<Comment> comments, Voting voting) {
        this.topic = topic;
        this.comments = comments;
        this.voting = voting;
    }

    public Schedule(Integer id, String topic, List<Comment> comments, Voting voting) {
        this.id = id;
        this.topic = topic;
        this.comments = comments;
        this.voting = voting;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void removeComment(Comment comment){
        comments.remove(comment);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
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
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", comments=" + comments +
                ", voting=" + voting +
                '}';
    }
}
