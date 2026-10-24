package ru.dmc3105.webapp.model;

public enum TaskPriority {
    LOW("LOW"),
    MEDIUM("MEDIUM"),
    HIGH("HIGH"),
    ;

    public String getValue() {
        return value;
    }

    private final String value;

    TaskPriority(String value) {
        this.value = value;
    }
}
