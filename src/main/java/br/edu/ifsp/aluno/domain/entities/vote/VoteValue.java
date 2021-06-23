package br.edu.ifsp.aluno.domain.entities.vote;

import java.util.Arrays;

public enum VoteValue {
    POSITIVE ("A favor"),
    NEGATIVE ("Contra"),
    ABSTENTION ("Abstenção");

    private String label;
    VoteValue(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    public static VoteValue toEnun(String value) {
        return Arrays.stream(VoteValue.values())
                .filter(c -> value.equals(c.toString()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
