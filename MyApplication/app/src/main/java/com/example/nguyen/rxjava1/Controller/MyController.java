package com.example.nguyen.rxjava1.Controller;

import android.app.Activity;
import android.app.ListActivity;

import com.example.nguyen.rxjava1.Model.Comment;
import com.example.nguyen.rxjava1.Model.Post;
import com.example.nguyen.rxjava1.Service.ForumService;
import com.example.nguyen.rxjava1.View.ListOfPostsActivity;
import com.example.nguyen.rxjava1.View.PostDetailActivity;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Nguyen on 10/6/2015.
 */
public class MyController {
    Activity activity;
    ForumService forumService;

    public MyController(Activity activity, ForumService forumService) {
        this.activity = activity;
        this.forumService = forumService;
    }

    public void loadPosts() {
        forumService.getApi()
                .getPosts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Post>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Post> posts) {
                        ((ListOfPostsActivity) activity).displayPosts(posts);
                    }
                });

    }

    public void loadPostAndComments(int postId) {
        forumService.getApi()
                .getPost(postId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Post>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Post post) {
                        ((PostDetailActivity) activity).displayPostDetail(post);
                    }
                });
        forumService.getApi()
                .getComments(postId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Comment>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Comment> comments) {
                        ((PostDetailActivity) activity).displayComments(comments);
                    }
                });
    }


}
