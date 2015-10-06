package com.example.nguyen.rxjava1.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nguyen.rxjava1.Adapter.CommentsAdapter;
import com.example.nguyen.rxjava1.Adapter.PostsAdapter;
import com.example.nguyen.rxjava1.Controller.MyController;
import com.example.nguyen.rxjava1.Model.Comment;
import com.example.nguyen.rxjava1.Model.Post;
import com.example.nguyen.rxjava1.R;
import com.example.nguyen.rxjava1.Service.ForumService;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PostDetailActivity extends AppCompatActivity {
    @InjectView(R.id.textViewTitleDetail)
    protected TextView textViewTitleDetail;
    @InjectView(R.id.textViewBodyDetail)
    protected TextView textViewBodyDetail;
    @InjectView(R.id.listViewComments)
    protected ListView listViewComments;

    CommentsAdapter commentsAdapter;
    ForumService forumService;
    MyController myController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int position = getIntent().getIntExtra("data", 0);
        setContentView(R.layout.activity_post_detail);
        ButterKnife.inject(this);
        ArrayList<Comment> dummyComments = new ArrayList<Comment>();
        commentsAdapter = new CommentsAdapter(this, dummyComments);
        listViewComments.setAdapter(commentsAdapter);

        forumService = new ForumService();
        myController = new MyController(this, forumService);
        myController.loadPostAndComments(position);
    }

    public void displayComments(List<Comment> comments) {

        commentsAdapter.clear();
        commentsAdapter.addAll(comments);
        commentsAdapter.notifyDataSetInvalidated();
    }

    public void displayPostDetail(Post post) {
        textViewTitleDetail.setText(post.getTitle());
        textViewBodyDetail.setText(post.getBody());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_post_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
