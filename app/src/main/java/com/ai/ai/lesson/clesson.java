package com.ai.ai.lesson;

public class clesson {
    private   int cno;
    private  String cname;
    private  String tname;
    private  int timedata;
    private  String locat;

    @Override
    public String toString(){
        String result ="";
        result+=this.cname+"\n";
        result+=this.locat+"\n";
        result+=this.tname+"\n";
        return result;
    }
    public int getCno(){return cno;}
    public int getTimedata(){return timedata;}
    public String getCname(){return cname;}
    public String getTname(){return tname;}
    public String getLocat(){return locat;}

    public void setCno(int cno){
        this.cno=cno;
    }
    public void setCname(String cname){
        this.cname=cname;
    }
    public void setTname(String tname){
        this.tname=tname;
    }
    public void setTimedata(int timedata){
        this.timedata=timedata;
    }
    public void setLocat(String locat){
        this.locat=locat;
    }
}