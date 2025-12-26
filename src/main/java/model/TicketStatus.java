package model;

public enum TicketStatus {
    НЕ_ИЗУЧЕНО("Не изучено"),
    ПОВТОРИТЬ("Повторить"),
    ВЫУЧЕНО("Выучено");

    private final String label;

    TicketStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

