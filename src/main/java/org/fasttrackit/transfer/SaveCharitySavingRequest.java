package org.fasttrackit.transfer;

import java.sql.Date;

public class SaveCharitySavingRequest {

    private String description;
    private boolean started;
    private boolean done;
    private Date deadLine;

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

    @Override
    public String toString() {
        return "SaveCharitySavingRequest{" +
                "description='" + description + '\'' +
                ", started=" + started +
                ", done=" + done +
                ", deadLine=" + deadLine +
                '}';
    }
}
