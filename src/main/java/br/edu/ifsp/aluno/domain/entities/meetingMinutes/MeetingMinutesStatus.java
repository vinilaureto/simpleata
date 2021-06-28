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
}
