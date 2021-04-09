package entity;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Date;

public class Person_Equipment {
    private Integer eid;
    private Integer uid;
    private Date btime;
    private Date rtime;
    private Integer type;
    private String uname;
    private String ename;

    public Person_Equipment() {
    }

    public Person_Equipment(Integer eid, Integer uid, Date btime, Date rtime, Integer type, String ename, String uname) {
        this.eid = eid;
        this.uid = uid;
        this.ename = ename;
        this.uname = uname;
        this.btime = btime;
        this.rtime = rtime;
        this.type = type;
    }

    public Person_Equipment(String json) throws IOException {
        Person_Equipment personEquipment = new ObjectMapper().readValue(json,Person_Equipment.class);
        this.eid = personEquipment.getEid();
        this.uid = personEquipment.getUid();
        this.ename = personEquipment.getEname();
        this.uname = personEquipment.getUname();
        this.rtime = personEquipment.getRtime();
        this.btime = personEquipment.getBtime();
        this.type = personEquipment.getType();
    }

    @Override
    public String toString() {
        return "Person_Equipment{" +
                "eid='" + eid + '\'' +
                ",ename='" + ename + '\'' +
                ",uid='" + uid + '\'' +
                ",uname='" + uname + '\'' +
                ",btime='" + btime + '\'' +
                ",rtime='" + rtime + '\'' +
                ",type='" + type + '\'' +
                "}";
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getBtime() {
        return btime;
    }

    public void setBtime(Date btime) {
        this.btime = btime;
    }

    public Date getRtime() {
        return rtime;
    }

    public void setRtime(Date rtime) {
        this.rtime = rtime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }
}
