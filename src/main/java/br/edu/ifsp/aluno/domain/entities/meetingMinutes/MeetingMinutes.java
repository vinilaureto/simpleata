package br.edu.ifsp.aluno.domain.entities.meetingMinutes;

import br.edu.ifsp.aluno.domain.entities.group.Group;
import br.edu.ifsp.aluno.domain.entities.inform.Inform;
import br.edu.ifsp.aluno.domain.entities.schedule.Schedule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MeetingMinutes {
    private Integer id;
    private String logo;
    private String identifier;
    private String local;
    private String title;
    private LocalDate creationDate;
    private LocalDate closingDate;
    private Group group;
    private List<Inform> informs = new ArrayList<>();
    private List<Schedule> schedules = new ArrayList<>();
    private MeetingMinutesStatus status;

    public MeetingMinutes() {
        status = MeetingMinutesStatus.PREMEETING;
    }

    public MeetingMinutes(String logo, String identifier, String local, String title, LocalDate creationDate, LocalDate closingDate, Group group, List<Inform> informs, List<Schedule> schedules, MeetingMinutesStatus status) {
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

    public MeetingMinutes(Integer id, String logo, String identifier, String local, String title, LocalDate creationDate, LocalDate closingDate, Group group, List<Inform> informs, List<Schedule> schedules, MeetingMinutesStatus status) {
        this.id = id;
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
        status = MeetingMinutesStatus.CLOSED;
    }

    public void startMeetingMinute() {
        status = MeetingMinutesStatus.OPENED;
    }

    public void addInform(Inform inform) {
        informs.add(inform);
    }

    public void addSchedule(Schedule schedule) {
        schedules.add(schedule);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setGroup(Group group) { this.group = group; }

    public Group getGroup() {
        return group;
    }

    public List<Inform> getInforms() {
        return informs;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public MeetingMinutesStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "MeetingMinutes{" +
                "id=" + id +
                ", logo='" + logo + '\'' +
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
