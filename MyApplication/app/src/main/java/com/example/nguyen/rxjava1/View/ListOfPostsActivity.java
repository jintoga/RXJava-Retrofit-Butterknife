package com.example.nguyen.rxjava1.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.nguyen.rxjava1.Adapter.PostsAdapter;
import com.example.nguyen.rxjava1.Model.Post;
import com.example.nguyen.rxjava1.Controller.MyController;
import com.example.nguyen.rxjava1.R;
import com.example.nguyen.rxjava1.Service.ForumService;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;
import butterknife.OnItemSelected;

public class ListOfPostsActivity extends AppCompatActivity {

    @InjectView(R.id.listViewPosts)
    ListView listViewPosts;

    PostsAdapter postsAdapter;
    ForumService forumService;
    MyController myController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_posts);
        ButterKnife.inject(this);
        ArrayList<Post> dummyPosts = new ArrayList<Post>();
        postsAdapter = new PostsAdapter(this, dummyPosts);
        listViewPosts.setAdapter(postsAdapter);

        forumService = new ForumService();
        myController = new MyController(this, forumService);
        myController.loadPosts();
    }

    @OnItemClick(R.id.listViewPosts)
    public void onPostSelect(int position) {
        Intent intent = new Intent(this, PostDetailActivity.class);
        intent.putExtra("data", postsAdapter.getItem(position).getId());
        startActivity(intent);
    }


    public void displayPosts(List<Post> posts) {

        postsAdapter.clear();
        postsAdapter.addAll(posts);
        postsAdapter.notifyDataSetInvalidated();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_of_posts, menu);
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
