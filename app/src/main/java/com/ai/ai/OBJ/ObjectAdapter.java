package com.ai.ai.OBJ;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ai.ai.R;
import com.ai.ai.db.DBAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.ai.ai.group.GroupRecyclerAdapter;
import  com.ai.ai.lesson.clesson;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 事务适配器
 */

public class ObjectAdapter extends GroupRecyclerAdapter<String, Object> {





    private RequestManager mLoader;
    public ObjectAdapter(Context context) {
        super(context);
        mLoader = Glide.with(context.getApplicationContext());
        LinkedHashMap<String, List<Object>> map = new LinkedHashMap<>();
        List<String> titles = new ArrayList<>();
        map.put("今日事务", create(0,context));
        //map.put("今日课程", create(1,context));
        titles.add("今日事务");
        //titles.add("今日课程");
        resetGroups(map,titles);
    }


    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new ObjectViewHolder(mInflater.inflate(R.layout.item_list_article, parent, false));
    }

    @Override
    protected void onBindViewHolder(RecyclerView.ViewHolder holder, Object item, int position) {
        ObjectViewHolder h = (ObjectViewHolder) holder;
        h.mTextTitle.setText(item.getTitle());
        h.mTextContent.setText(item.getContent());
        h.CH.setChecked(item.getfinish());
    }

    private static class ObjectViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextTitle,
                mTextContent;
        private ImageView mImageView;
        private CheckBox CH;

        private ObjectViewHolder(View itemView) {
            super(itemView);
            mTextTitle = itemView.findViewById(R.id.tv_name);
            mTextContent = itemView.findViewById(R.id.tv_content);
            CH = itemView.findViewById(R.id.Finish);
        }
    }

    /**
     * 事务创建
     *
     */
    private static Object create(String title, String content, boolean finish) {
        Object object = new Object();
        object.setTitle(title);
        object.setContent(content);
        object.setFinish(finish);
        return object;
    }

    private static List<Object> create(int p,Context context) {
        List<Object> list = new ArrayList<>();
        DBAdapter db=new DBAdapter(context);
        db.open();
        clesson l=new clesson();
        l.setCname("hu");
        l.setTname("as");
        l.setCno(1);
        l.setLocat("dfs");
        l.setTimedata(34);
        db.insert(l);
        clesson lessons[]=db.queryAllData();


        if (p == 0) {
            /*list.add(create("新西兰克马德克群岛发生5.7级地震 震源深度10千米",
                    "#地震快讯#中国地震台网正式测定：12月04日08时08分在克马德克群岛（南纬32.82度，西经178.73度）发生5.7级地震，震源深度10千米。",
                    false));*/
            list.add(create("tyty","finsh",true));
            list.add(create("in","finsh",true));
            if(lessons.length!=0) {
                for (int i = 0; i < lessons.length; i++) {
                    list.add(create(lessons[i].getCname(), lessons[i].getLocat(), true));
                }
            }
        } else if (p == 1) {

        }

        db.close();
        return list;
    }
}
