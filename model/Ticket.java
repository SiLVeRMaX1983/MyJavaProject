package model;

import model.TicketStatus;
import java.util.List;

public class Ticket {
    private String question;
    private String answer;
    private TicketStatus status;
    private List<String> options;

    public Ticket(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.status = TicketStatus.REPEAT;
    }

    public Ticket(String question, String answer, List<String> options) {
        this.question = question;
        this.answer = answer;
        this.options = options;
        this.status = TicketStatus.REPEAT;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public List<String> getOptions() {
        return options;
    }
}
