package br.edu.ifsp.aluno.domain.entities.meetingMinutes;

import java.util.Arrays;

public enum MeetingMinuteStatus {
    PREMEETING ("Pré-ata"),
    OPENED ("Aberto"),
    CLOSED ("Fechado");

    private String label;
    MeetingMinuteStatus(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    public static MeetingMinuteStatus toEnun(String value) {
        return Arrays.stream(MeetingMinuteStatus.values())
                .filter(c -> value.equals(c.toString()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
