package Model;

import javax.persistence.*;
import java.sql.*;

@Entity
@Table(name = "hours")
public class Hours {
    private String day;
    private Time startTime;
    private Time closeTime;

    public Hours() {

    }

    public Hours(String day, Time startTime, Time closeTime) {
        this.day = day;
        this.startTime = startTime;
        this.closeTime = closeTime;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Time closeTime) {
        this.closeTime = closeTime;
    }
}
