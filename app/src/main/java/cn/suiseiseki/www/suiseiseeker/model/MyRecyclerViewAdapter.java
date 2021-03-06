package cn.suiseiseki.www.suiseiseeker.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;


import java.util.ArrayList;

import cn.suiseiseki.www.suiseiseeker.R;
import cn.suiseiseki.www.suiseiseeker.tools.FontHelper;

/**
 * Created by Suiseiseki/shuikeyi on 2016/3/17.
 * Adapter for RecyclerView
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Post> mPosts;
    private Context mContext;

    /**
     * Define an interface to handle post click event
     */
    private onItemClickCallback mCallback;
    public interface onItemClickCallback
    {
        void onItemClick(Post post);
    }
    /**
     * Use construct method to initialize Posts and Callback
     */
    public MyRecyclerViewAdapter(ArrayList<Post> posts,onItemClickCallback callback)
    {
        mPosts = posts;
        mCallback = callback;
    }
    /**
     * define an inner class to define own ViewHolder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView mImageView;
        TextView mTitleTextView,mAuthorTextView,mExcerptTextView,mDateTextView;
        public ViewHolder(View view)
        {
            super(view);
            mImageView = (ImageView) view.findViewById(R.id.thumbnail_imageview_cardview);
            mTitleTextView = (TextView) view.findViewById(R.id.textview_cardview_post);
            mAuthorTextView = (TextView) view.findViewById(R.id.textview_cardview_author);
            mExcerptTextView = (TextView)view.findViewById(R.id.textview_excerpt);
            mDateTextView = (TextView)view.findViewById(R.id.textview_date_post);
        }
    }
    /**
     * Override onCreateViewHolder to Initialize the view
     */
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent,final int i)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item,parent,false);
        mContext = parent.getContext();
        FontHelper.applyFont(mContext,v,"fonts/myfont.ttf");
        return new ViewHolder(v);
    }
    @Override
    public int getItemCount()
    {
        return mPosts.size();
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder,final int i)
    {
        Glide.with(mContext).load(mPosts.get(i).getThumbnailUrl())
                .centerCrop().into(viewHolder.mImageView);
        viewHolder.mTitleTextView.setText(mPosts.get(i).getTitle());
        viewHolder.mExcerptTextView.setText(mPosts.get(i).getExcerpt());
        viewHolder.mDateTextView.setText(mPosts.get(i).getDate());
        if(mPosts.get(i).getAuthor() == null)
            viewHolder.mAuthorTextView.setText("Anonymous");
        else
            viewHolder.mAuthorTextView.setText(mPosts.get(i).getAuthor());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onItemClick(mPosts.get(i));
            }
        });
    }


}
