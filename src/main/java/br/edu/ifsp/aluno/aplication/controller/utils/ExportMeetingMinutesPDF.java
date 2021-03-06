package br.edu.ifsp.aluno.aplication.controller.utils;

import br.edu.ifsp.aluno.domain.entities.comment.Comment;
import br.edu.ifsp.aluno.domain.entities.group.Group;
import br.edu.ifsp.aluno.domain.entities.inform.Inform;
import br.edu.ifsp.aluno.domain.entities.participant.Participant;
import br.edu.ifsp.aluno.domain.entities.schedule.Schedule;
import br.edu.ifsp.aluno.domain.entities.vote.Vote;
import br.edu.ifsp.aluno.domain.entities.voting.Voting;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import br.edu.ifsp.aluno.domain.entities.meetingMinutes.MeetingMinutes;
import static br.edu.ifsp.aluno.aplication.main.Main.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.Normalizer;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ExportMeetingMinutesPDF {
    private MeetingMinutes meetingMinutes;
    private Group group;

    public void exportPDF(MeetingMinutes meetingMinutes) throws FileNotFoundException, DocumentException {
        group = findGroupUseCase.findOne(meetingMinutes.getGroup().getId()).get();

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(validateMeetingMinutesFileName(meetingMinutes.getTitle())+ ".pdf"));
        document.open();

        // Font
        Font titleFont = new Font(Font.getFamily("TIMES_ROMAN"), 18, Font.BOLD);
        Font subTitleFont = new Font(Font.getFamily("TIMES_ROMAN"), 14, Font.BOLD);
        Font caption = new Font(Font.getFamily("TIMES_ROMAN"), 11);
        Font normal = new Font(Font.getFamily("TIMES_ROMAN"), 12);

        // Meeting minutes title
        Paragraph meetingMinutesTitle = new Paragraph(meetingMinutes.getTitle(), titleFont);
        meetingMinutesTitle.setAlignment(Element.ALIGN_CENTER);
        document.add(meetingMinutesTitle);

        // Group name
        Paragraph groupName = new Paragraph(group.getName(), caption);
        groupName.setAlignment(Element.ALIGN_CENTER);
        document.add(groupName);
        Paragraph emptyParagraph = new Paragraph("\n", normal);
        document.add(emptyParagraph);

        // Meeting minutes opening
        Locale locale = new Locale("pt", "br");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy", locale);
        StringBuilder openingText = new StringBuilder("Ata da reuni??o realizada no dia ");
        openingText.append(meetingMinutes.getClosingDate().format(formatter) + ". ");
        openingText.append("Membros integrantes do grupo: ");
        for (int i = 0; i < group.getParticipants().size(); i++) {
            if (i == group.getParticipants().size() - 2) {
                openingText.append(group.getParticipants().get(i).getTitle() + " " + group.getParticipants().get(i).getName() + " e ");
            } else if (i == group.getParticipants().size() - 1) {
                openingText.append(group.getParticipants().get(i).getTitle() + " " + group.getParticipants().get(i).getName() + ".");
            } else {
                openingText.append(group.getParticipants().get(i).getTitle() + " " + group.getParticipants().get(i).getName() + ", ");
            }
        }
        Paragraph openingParagraph = new Paragraph(openingText.toString(), normal);
        document.add(openingParagraph);
        document.add(emptyParagraph);

        // Informs
        List<Inform> informs = new ArrayList<>(findInformUseCase.findByMeetingMinutes(meetingMinutes));
        if (informs.size() > 0) {
            Paragraph informsTitle = new Paragraph("Informes:", subTitleFont);
            document.add(informsTitle);
            for (Inform inform : informs) {
                String informText = inform.getDescription();
                Paragraph informParagraf = new Paragraph(informText, normal);
                document.add(informParagraf);
            }
            document.add(emptyParagraph);
        }

        // Schedules
//        List<Schedule> schedules = new ArrayList<>(findScheduleUseCase.findByMeetingMinutes(meetingMinutes));
//        if (schedules.size() > 0) {
//            Paragraph scheduleTitle = new Paragraph("Pautas:", subTitleFont);
//            document.add(scheduleTitle);
//            for (Schedule schedule : schedules) {
//                StringBuilder scheduleText = new StringBuilder("Foi discutida a pauta: ");
//                scheduleText.append(schedule.getTopic() + ". ");
//                List<Comment> comments = new ArrayList<>(findCommentUseCase.findBySchedule(schedule));
//                for (Comment comment : comments) {
//                    scheduleText.append(comment.getParticipant().getName() + " comentou: ");
//                    scheduleText.append(comment.getMessage() + " ");
//                }
//                Paragraph scheduleParagraph = new Paragraph(scheduleText.toString(), normal);
//                document.add(scheduleParagraph);
//                document.add(emptyParagraph);
//            }
//        }

        // Schedule with voting
        List<Schedule> schedules = new ArrayList<>(findScheduleUseCase.findByMeetingMinutes(meetingMinutes));
        if (schedules.size() > 0) {
            Paragraph scheduleTitle = new Paragraph("Pautas:", subTitleFont);
            document.add(scheduleTitle);
            for (Schedule schedule : schedules) {
                StringBuilder scheduleText = new StringBuilder("Foi discutida a pauta: ");
                scheduleText.append(schedule.getTopic() + ". ");
                List<Comment> comments = new ArrayList<>(findCommentUseCase.findBySchedule(schedule));
                for (Comment comment : comments) {
                    scheduleText.append(comment.getParticipant().getName() + " comentou: ");
                    scheduleText.append(comment.getMessage() + " ");
                }

                Voting voting = null;
                if (findVotingUseCase.findBySchedule(schedule).isPresent()) {
                    voting = findVotingUseCase.findBySchedule(schedule).get();
                }

                if (voting != null) {
                    String votingType;
                    List<Vote>votes = findVoteUseCase.findByVoting(voting);
                    if (votes.size() > 0 && votes.get(0).getParticipant() != null) {
                        votingType = "nominal";
                    } else {
                        votingType = "an??nima";
                    }
                    scheduleText.append("Houve uma vota????o " + votingType + ". ");
                    scheduleText.append("O resultado foi " + voting.getResult().toString() + ". ");
                    if (votingType == "nominal" && votes.size() > 0) {
                        scheduleText.append("Os votos foram: ");
                        for (int i = 0; i < votes.size(); i++) {
                            if (i == votes.size() - 2) {
                                scheduleText.append(votes.get(i).getParticipant().getName() + ": " + votes.get(i).getValue().toString() + " e ");
                            } else if (i == votes.size() - 1) {
                                scheduleText.append(votes.get(i).getParticipant().getName() + ": " + votes.get(i).getValue().toString() + ".");
                            } else {
                                scheduleText.append(votes.get(i).getParticipant().getName() + ": " + votes.get(i).getValue().toString() + ", ");
                            }
                        }
                    }
                } else {
                    scheduleText.append("A pauta n??o possuiu vota????o.");
                }
                Paragraph scheduleParagraph = new Paragraph(scheduleText.toString(), normal);
                document.add(scheduleParagraph);
                document.add(emptyParagraph);
            }
        }

        document.close();
    }

    private String validateMeetingMinutesFileName(String name) {
        return Normalizer.normalize(name, Normalizer.Form.NFD).replaceAll("[\\\\/:*?\\\"<>|]", "_");
    }
}
