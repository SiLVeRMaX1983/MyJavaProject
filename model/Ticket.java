package model;

public class Ticket {
   private String question;
   private String answer;
   private TicketStatus status;

   public Ticket(String question, String answer){
        this.question = question;
        this.answer = answer;
        this.status = TicketStatus.NOT_STUDIED;
   }

   public String getQuestion(){
      return question;
   }

   public String getAnswer(){
      return answer;
   }

   public TicketStatus getStatus(){
      return status;
   }

   public void setStatus(TicketStatus status){
      this.status = status;
   }

}

