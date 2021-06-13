package com.ai.ai.lesson;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ai.ai.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.ai.ai.group.GroupRecyclerAdapter;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 事务适配器
 */

public class lessonAdapter extends GroupRecyclerAdapter<String, clesson> {


    private RequestManager mLoader;

    public lessonAdapter(Context context) {
        super(context);
        mLoader = Glide.with(context.getApplicationContext());
        LinkedHashMap<String, List<clesson>> map = new LinkedHashMap<>();
        List<String> titles = new ArrayList<>();
        //map.put("今日事务", create(0));
        map.put("今日课程", create(1));
        //titles.add("今日事务");
        titles.add("今日课程");
        resetGroups(map,titles);
    }


    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new CViewHolder(mInflater.inflate(R.layout.item_list_article, parent, false));
    }

    @Override
    protected void onBindViewHolder(RecyclerView.ViewHolder holder, clesson item, int position) {
        CViewHolder h = (CViewHolder) holder;
        h.mTextTitle.setText(item.getCname());
        h.mTextContent.setText(item.getLocat());
//        h.mTextT.setText(item.getTname());
        h.CH.setChecked(item.getFinish());
    }

    private static class CViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextTitle;
        private TextView mTextContent;
        private TextView mTextT;

        private CheckBox CH;

        private CViewHolder(View itemView) {
            super(itemView);
            mTextTitle = itemView.findViewById(R.id.tv_name);
            mTextContent = itemView.findViewById(R.id.tv_content);
          //  mTextT = ;
            CH = itemView.findViewById(R.id.Finish);
        }
    }

    /**
     * 事务创建
     *
     */

private static clesson create(String cname, String location, String tname, boolean finish) {
        clesson lesson = new clesson();
        lesson.setCname(cname);
        lesson.setLocat(location);
        lesson.setTname(tname);
        lesson.setFinish(finish);
        return lesson;
    }

    private static List<clesson> create(int p) {
        List<clesson> list = new ArrayList<>();
        if (p == 0) {


        } else if (p == 1) {
                list.add(create("Math","1111","LIhua",false));
        }


        return list;
    }
}
