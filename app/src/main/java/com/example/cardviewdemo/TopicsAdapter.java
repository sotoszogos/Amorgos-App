package com.example.cardviewdemo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.TopicViewHolder> {

    //helper
    //travaei data apo ton klasi Topic
    private Context mContext;
    // Member variable to handle item clicks
    final private ItemClickListener mItemClickListener;
    private List<Topic> topicList;

    //getter
    public List<Topic> getTopics(){
        return topicList;
    }

    public TopicsAdapter(Context context, ItemClickListener listener) {
        mContext = context;
        mItemClickListener = listener;
    }

    @Override
    public TopicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.topic_card, parent, false);
        return new TopicViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final TopicViewHolder holder, final int position) {
        Topic topic = topicList.get(position);
        holder.title.setText(topic.getName());
        holder.count.setText("");
        // construct string of viewed POIs
        String viewedPOIs = topic.getNumOfPOIs() + " " + mContext.getResources().getString(R.string.item_highlight_txt);
        holder.POIsViewed.setText(viewedPOIs);
        // loading topic topics_cover using Glide library
        /* // to round up the corners
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));
        Glide.with(mContext).load(topic.getThumbnailResourceID()).apply(requestOptions).into(holder.thumbnail);
        */
        Log.d("thumbnail id", String.valueOf(topic.getThumbnailResourceID()));
        //Glide.with(mContext).load(topic.getThumbnailResourceID()).into(holder.thumbnail);
        holder.thumbnail.setImageResource(topic.getThumbnailResourceID());

        //For accessibility
        holder.thumbnail.setContentDescription(topic.getImageContentDescription());
    }

    @Override
    public int getItemCount() {
        if (topicList == null)
            return 0;
        return topicList.size();
    }

    public void setTopics(List<Topic> topics) {
        topicList = topics;
        for (Topic topic : topics)
            Log.d("Adapter: ", topic.getName());
    }

    public String getTopicName(int index){
        Topic topic = topicList.get(index);
        return topic.getName();
    }

    public interface ItemClickListener {
        void onItemClickListener(int itemId);
    }

    public class TopicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title, count, POIsViewed;
        private ImageView thumbnail, overflow;

        public TopicViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            count = view.findViewById(R.id.numPOIs);
            thumbnail = view.findViewById(R.id.thumbnailResourceID);
            overflow = view.findViewById(R.id.viewed_icon);
            POIsViewed = view.findViewById(R.id.POIsViewed);
            // must set onclick listener here
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //this should pass the adapter position to item click
            mItemClickListener.onItemClickListener(getAdapterPosition());
        }
    }
}
