package org.fasttrackit.transfer;

import java.sql.Date;

public class SaveCharityRequest {

    private String description;
    private boolean started;
    private String location;
    private boolean done;
    private Date deadLine;
    private String note;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "SaveCharityRequest{" +
                "description='" + description + '\'' +
                ", started=" + started +
                ", location='" + location + '\'' +
                ", done=" + done +
                ", deadLine=" + deadLine +
                ", note='" + note + '\'' +
                '}';
    }
}
