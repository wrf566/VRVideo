package com.hzgkyt.vr.fragment;

import android.os.Environment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hzgkyt.vr.adapter.VideoItemAdapter;
import com.hzgkyt.vr.decoration.VideoItemDecoration;
import com.hzgkyt.vr.model.VideoItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 更多页面
 * <p>
 * Created by wrf on 2016/12/2.
 */

public class MoreGroupFragment extends RecyclerViewFragment {

    private static final int ITEM_SPACE = 10;//item之间的距离


    @Override
    protected void initView(View view) {
        super.initView(view);
//        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mRecyclerView.getLayoutParams();
//        layoutParams.setMargins(30, 30, 30, 30);
//        mRecyclerView.setLayoutParams(layoutParams);
    }

    @Override
    protected RecyclerView.Adapter initRecyclerViewAdapter() {
        return new VideoItemAdapter(getActivity(),  getVideoItemList());
    }

    @Override
    protected RecyclerView.ItemDecoration initRecyclerViewItemDecoration() {
        return new VideoItemDecoration(ITEM_SPACE);
    }

    @Override
    protected RecyclerView.LayoutManager initRecyclerViewLayoutManager() {
        return new GridLayoutManager(getActivity(), 2);
    }


    private List<VideoItemModel> getVideoItemList() {
        List<VideoItemModel> mVideoItemList = new ArrayList<>();
        mVideoItemList.add(new VideoItemModel("楼观之路", Environment.getExternalStorageDirectory() + "/vrvideo/lgzl.mp4"));
        mVideoItemList.add(new VideoItemModel("楼观台广场", Environment.getExternalStorageDirectory() + "/vrvideo/lgtgc.mp4"));
        mVideoItemList.add(new VideoItemModel("财神殿", Environment.getExternalStorageDirectory() + "/vrvideo/csd.mp4"));
        mVideoItemList.add(new VideoItemModel("金鱼池", Environment.getExternalStorageDirectory() + "/vrvideo/jyc.mp4"));
        mVideoItemList.add(new VideoItemModel("太极台", Environment.getExternalStorageDirectory() + "/vrvideo/tjt.mp4"));
        mVideoItemList.add(new VideoItemModel("八卦池", Environment.getExternalStorageDirectory() + "/vrvideo/bgc.mp4"));

        return mVideoItemList;
    }
}
