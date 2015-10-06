package com.example.nguyen.rxjava1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nguyen.rxjava1.Model.Comment;
import com.example.nguyen.rxjava1.Model.Post;
import com.example.nguyen.rxjava1.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Nguyen on 10/6/2015.
 */
public class CommentsAdapter extends ArrayAdapter<Comment> {
    private ArrayList<Comment> data;
    private Context context;

    public CommentsAdapter(Context context, ArrayList<Comment> data) {
        super(context, 0, data);
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Comment comment = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_comment_item, parent, false);

        ViewHolder view = new ViewHolder();
        ButterKnife.inject(view, convertView);
        view.load(comment);

        return convertView;
    }

    class ViewHolder {
        @InjectView(R.id.textViewCommentName)
        protected TextView textViewName;
        @InjectView(R.id.textViewCommentEmail)
        protected TextView textViewEmail;
        @InjectView(R.id.textViewCommentBody)
        protected TextView textViewBody;

        public void load(Comment comment) {
            textViewName.setText(comment.name);
            textViewEmail.setText(comment.email);
            textViewBody.setText(comment.body);
        }
    }


}
