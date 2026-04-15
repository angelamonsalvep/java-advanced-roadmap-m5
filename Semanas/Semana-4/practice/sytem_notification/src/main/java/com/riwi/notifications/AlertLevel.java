package com.riwi.notifications;

public enum AlertLevel {
    INFO(1, "blue"),
    WARNING(2, "yellow"),
    CRITICAL(3, "red");

    private final int idLevel;
    private final String colorLevel;

    AlertLevel(int idLevel, String colorLevel) {
        this.idLevel = idLevel;
        this.colorLevel = colorLevel;
    }

    public int getIdLevel() {
        return idLevel;
    }

    public String getColorLevel() {
        return colorLevel;
    }

    @Override
    public String toString() {
        return "AlertLevel{" +
                "idLevel=" + idLevel +
                ", colorLevel='" + colorLevel + '\'' +
                '}';
    }
}
