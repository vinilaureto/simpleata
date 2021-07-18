package br.edu.ifsp.aluno.domain.entities.meetingMinutes;

import java.util.Arrays;

public enum MeetingMinutesStatus {
    PREMEETING ("Pré-ata"),
    OPENED ("Aberto"),
    CLOSED ("Fechado");

    private String label;

    MeetingMinutesStatus(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    public static MeetingMinutesStatus toEnum(String value){
        return Arrays.stream(MeetingMinutesStatus.values())
                .filter(c -> value.equals(c.toString()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
