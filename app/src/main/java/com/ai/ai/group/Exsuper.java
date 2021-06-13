package com.ai.ai.group;

import java.io.Serializable;
import java.util.Calendar;

import com.ai.ai.lesson.clesson;
import com.ai.ai.OBJ.Object;

public class Exsuper  {

    int type;
    private int Sid;          //课程号/事件号
    private String Sname;     //课程名/事件名
    private String Scontext;  //教师名/内容
    private int Sstar;        //课程开始/事件开始
    private int Sfinish;      //课程结束/事件结束
    private String Sloty;     //课程地点/事件类型
    private int timedata;     //发生时间
    private boolean complite = false; //完成

    public int getid(){return Sid;}
    public String getSname(){return Sname;}
    public String getScontext(){return Scontext;}
    public int getSstar(){return Sstar;}
    public int getSfinish(){return Sfinish; }
    public String getSloty(){return Sloty;}
    public int getTimedata(){return timedata;}
    public boolean getcomplite(){
        if(type == 1) {
            Calendar crr = Calendar.getInstance();
            int h = crr.get(Calendar.HOUR_OF_DAY);
            int mi = crr.get(Calendar.MINUTE);
            int Tb = getBo(h, mi);
            if (Sfinish < Tb)
                complite = true;
        }
        return complite;
    }

    public void setSid(int id){this.Sid = id; }
    public void setSname(String name){this.Sname = name; }
    public void setScontext(String scontext){this.Scontext = scontext; }
    public void setSstar(int star){this.Sstar = star; }
    public void setSfinish(int finish){this.Sfinish = finish;}
    public void setSloty(String sloty){this.Sloty = sloty; }
    public void setTimedata(int timedata){this.timedata = timedata; }
    public void setcomplite(boolean f){this.complite = f; }


    public int getBo(int h,int mi)
    {
        int I = 0;
        if(h >= 8  && h < 10 ) I = 1;
        if(h >= 10 && h < 13 ) I = 2;
        if(h >= 13 && h < 15 ) I = 3;
        if(h >= 15 && h < 17 ) I = 4;
        if(h >= 18 && h <= 20) I = 5;

        return I;
    }



}