package com.ai.ai.pager;

import androidx.recyclerview.widget.LinearLayoutManager;


import com.ai.ai.R;
import com.ai.ai.base.fragment.BaseFragment;
import com.ai.ai.group.Exsuper;
import com.ai.ai.group.GroupItemDecoration;
import com.ai.ai.group.GroupRecyclerView;
import com.ai.ai.group.SuperAdapter;

public class PagerFragment extends BaseFragment {

    private GroupRecyclerView mRecyclerView;


    static PagerFragment newInstance() {
        return new PagerFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pager;
    }

    @Override
    protected void initView() {
        mRecyclerView = mRootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.addItemDecoration(new GroupItemDecoration<String, Object>());
       /* mRecyclerView.setAdapter(new SuperAdapter(mContext));
        mRecyclerView.notifyDataSetChanged();*/
    }

    @Override
    protected void initData() {

    }

    boolean isScrollTop() {
        return mRecyclerView != null && mRecyclerView.computeVerticalScrollOffset() == 0;
    }
}
