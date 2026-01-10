package model;

import java.util.List;

public class Ticket {
    private String question;
    private String answer;
    private TicketStatus status;
    private List<String> options;

    public Ticket(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.status = TicketStatus.ПОВТОРИТЬ;
    }

    public Ticket(String question, String answer, List<String> options) {
        this.question = question;
        this.answer = answer;
        this.options = options;
        this.status = TicketStatus.ПОВТОРИТЬ;
    }

    public Ticket(String question, String answer, List<String> options, TicketStatus status) {
        this.question = question;
        this.answer = answer;
        this.options = options;
        this.status = status;
    }

    public String getQuestion() { return question; }
    public String getAnswer() { return answer; }
    public TicketStatus getStatus() { return status; }
    public void setStatus(TicketStatus status) { this.status = status; }
    public List<String> getOptions() { return options; }
}


