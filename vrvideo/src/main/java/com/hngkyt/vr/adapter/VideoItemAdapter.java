package com.hngkyt.vr.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hngkyt.vr.R;
import com.hngkyt.vr.activity.VRVideoActivity;
import com.hngkyt.vr.model.Video;

import java.util.List;

/**
 * 单个视频适配器
 * Created by wrf on 2016/12/1.
 */

public class VideoItemAdapter extends RecyclerView.Adapter<VideoItemAdapter.VideoItemViewHolder> {


    private Context mContext;
    private List<Video> mListBeanList;

    public VideoItemAdapter(Context context, List<Video> listBeanList) {
        mContext = context;
        mListBeanList = listBeanList;
    }

    public List<Video> getListBeanList() {
        return mListBeanList;
    }

    public void setListBeanList(List<Video> listBeanList) {
        mListBeanList = listBeanList;
        notifyDataSetChanged();
    }

    @Override
    public VideoItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_video, parent, false);

        return new VideoItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VideoItemViewHolder holder, int position) {
        final Video video = mListBeanList.get(position);

        holder.mTextViewName.setText(video.getVedioName());
        holder.mRelativeLayoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, VRVideoActivity.class);
                intent.putExtra(Video.class.getCanonicalName(), video);
                mContext.startActivity(intent);

            }
        });

        Glide.with(mContext)
                .load(video.getVedioImgUrl())
                .asBitmap()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.mImageViewCover);

    }

    @Override
    public int getItemCount() {
        return mListBeanList.size();
    }


    static class VideoItemViewHolder extends RecyclerView.ViewHolder {

        TextView mTextViewName;
        ImageView mImageViewCover;
        RelativeLayout mRelativeLayoutItem;

         VideoItemViewHolder(View itemView) {
            super(itemView);

            mTextViewName = (TextView) itemView.findViewById(R.id.textview_item_video_name);
            mImageViewCover = (ImageView) itemView.findViewById(R.id.imageview_item_video_cover);
            mRelativeLayoutItem = (RelativeLayout) itemView.findViewById(R.id.relativelayout_item_video);
        }
    }


}
