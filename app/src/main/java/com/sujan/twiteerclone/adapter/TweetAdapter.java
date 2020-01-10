package com.sujan.twiteerclone.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.sujan.twiteerclone.R;
import com.sujan.twiteerclone.model.TweetM;

import java.util.List;

public class TweetAdapter  extends RecyclerView.Adapter<TweetAdapter.TweetList> {
    Context context;
    List<TweetM> dataSetList;
    public static final String base_url = "http://10.0.2.2:3000/";
    String imagePath = base_url + "uploads/" ;

    public TweetAdapter(Context context, List<TweetM> dataSetList) {
        this.context = context;
        this.dataSetList = dataSetList;
    }

    @NonNull
    @Override
    public TweetAdapter.TweetList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.homel_rv, parent, false );
        return new TweetAdapter.TweetList( view );

    }

    @Override
    public void onBindViewHolder(@NonNull TweetAdapter.TweetList holder, int position) {
        TweetM tm = dataSetList.get( position );
        holder.txt_head.setText( tm.getHeadingtext() );
        holder.txt_body.setText( tm.getMessagetext() );
        String url="http://10.0.2.2:3112/profile/";
        Picasso.get().load(url+tm.getMessageimage()).resize(150, 150).centerCrop().into(holder.m_img);
        Picasso.get().load(url+tm.getUserimage()).resize(150, 150).centerCrop().into(holder.p_img);

//        holder.m_img.setImageBitmap( BitmapFactory.decodeStream((InputStream) url.getContent()));
//        holder.p_img.setImageBitmap( BitmapFactory.decodeStream((InputStream) url.getContent()));


    }

    @Override
    public int getItemCount() {
        return dataSetList.size();
    }

    public class TweetList extends RecyclerView.ViewHolder {
        ImageView p_img,m_img;
        TextView txt_head,txt_body;
        public TweetList(@NonNull View itemView) {
            super( itemView );
            p_img=itemView.findViewById( R.id.P_img );
            m_img=itemView.findViewById( R.id.M_img );
            txt_body=itemView.findViewById( R.id.M_txt );
            txt_head=itemView.findViewById( R.id.H_txt );
        }
    }
}
