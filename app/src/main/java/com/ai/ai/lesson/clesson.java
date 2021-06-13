package com.ai.ai.lesson;

import java.util.Calendar;

public class clesson {
    private   int cno;
    private  String cname;
    private  String tname;
    private  int timeblock[] = new int[2];
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
    public boolean getFinish(){
        Calendar  crr = Calendar.getInstance();
        int h = crr.get(Calendar.HOUR_OF_DAY);
        int mi = crr.get(Calendar.MINUTE);
        //int Tb = getBo(h,mi);
        if(timeblock[1] < 1) return true;
        else return false;
    }
    public  void setFinish(boolean f)
    {
        if(f==false) timeblock[0] = 0;
    }
    public int getBo(int h,int mi)
    {
        int I;
        switch (h)
        {
            case 8:  I = 1; break;
            case 10: I = 2; break;
            case 13: I = 3; break;
            case 15: I = 4; break;
            case 18: I = 5; break;
            case 20: I = 6; break;
            default:
                throw new IllegalStateException("Unexpected value: " + h + mi);
        }
        return I;
    }
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