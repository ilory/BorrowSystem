package entity;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Equipment {
    private Integer eid;
    private String ename;
    private Integer borrow;

    public Equipment() {
    }

    public Equipment(Integer eid, String ename, Integer borrow) {
        this.eid = eid;
        this.ename = ename;
        this.borrow = borrow;
    }

    public Equipment(String json) throws IOException {
        Equipment equipment = new ObjectMapper().readValue(json,Equipment.class);
        this.eid = equipment.getEid();
        this.ename = equipment.getEname();
        this.borrow = equipment.getBorrow();
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Integer getBorrow() {
        return borrow;
    }

    public void setBorrow(Integer borrow) {
        this.borrow = borrow;
    }

    @Override
    public String toString() {
        return "Person_Equipment{" +
                "eid='" + eid + '\'' +
                "ename='" + ename + '\'' +
                ",borrow='" + borrow + '\'' +
                "}";
    }
}
