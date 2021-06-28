package br.edu.ifsp.aluno.domain.entities.meetingMinutes;

import br.edu.ifsp.aluno.domain.entities.group.Group;
import br.edu.ifsp.aluno.domain.entities.inform.Inform;
import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.entities.schedule.Schedule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MeetingMinutes {
    private String logo;
    private String identifier;
    private String local;
    private String title;
    private LocalDate creationDate;
    private LocalDate closingDate;
    private Group group;
    private List<Inform> informs = new ArrayList<>();
    private List<Schedule> schedules = new ArrayList<>();
    private MeetingMinuteStatus status;

    public MeetingMinutes() {
        status = MeetingMinuteStatus.PREMEETING;
    }

    public MeetingMinutes(String logo, String identifier, String local, String title, LocalDate creationDate, LocalDate closingDate, Group group, List<Inform> informs, List<Schedule> schedules, MeetingMinuteStatus status) {
        this.logo = logo;
        this.identifier = identifier;
        this.local = local;
        this.title = title;
        this.creationDate = creationDate;
        this.closingDate = closingDate;
        this.group = group;
        this.informs = informs;
        this.schedules = schedules;
        this.status = status;
    }

    public void closeMeetingMinute() {
        status = MeetingMinuteStatus.CLOSED;
    }

    public void startMeetingMinute() {
        status = MeetingMinuteStatus.OPENED;
    }

    public void addInform(Inform inform) {
        informs.add(inform);
    }

    public void addSchedule(Schedule schedule) {
        schedules.add(schedule);
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDate closingDate) {
        this.closingDate = closingDate;
    }

    public Group getGroup() {
        return group;
    }

    public List<Inform> getInforms() {
        return informs;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public MeetingMinuteStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "MeetingMinutes{" +
                "logo='" + logo + '\'' +
                ", identifier='" + identifier + '\'' +
                ", local='" + local + '\'' +
                ", title='" + title + '\'' +
                ", creationDate=" + creationDate +
                ", closingDate=" + closingDate +
                ", group=" + group +
                ", informs=" + informs +
                ", schedules=" + schedules +
                ", status=" + status +
                '}';
    }
}