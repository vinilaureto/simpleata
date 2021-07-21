package br.edu.ifsp.aluno.domain.entities.voting;

import java.util.Arrays;

public enum VoteResult {
    IN_PROGRESS ("Em andamento"),
    APPROVED ("Aprovado"),
    REJECTED ("Reprovado"),
    DRAW ("Empate");

    private String label;
    VoteResult(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    public static VoteResult toEnun(String value) {
        return Arrays.stream(VoteResult.values())
                .filter(c -> value.equals(c.toString()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
