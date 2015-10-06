package com.example.nguyen.rxjava1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nguyen.rxjava1.Model.Post;
import com.example.nguyen.rxjava1.R;

import java.util.ArrayList;

/**
 * Created by Nguyen on 10/6/2015.
 */
public class PostsAdapter extends ArrayAdapter<Post> {
    private ArrayList<Post> data;
    private Context context;


    public PostsAdapter(Context context, ArrayList<Post> data) {
        super(context, 0, data);
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Post post = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_post_item, parent, false);

        TextView title = (TextView) convertView.findViewById(R.id.textViewItemTitle);
        title.setText(post.title);

        return convertView;
    }
}
