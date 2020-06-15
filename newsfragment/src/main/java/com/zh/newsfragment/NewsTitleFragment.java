package com.zh.newsfragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsTitleFragment extends Fragment {
    private static final String TAG = "NewsTitleFragment";
    private boolean isTwoPane;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_frag, container, false);
        RecyclerView newsTitleRecyclerView = (RecyclerView) view.findViewById(R.id.news_title_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        newsTitleRecyclerView.setLayoutManager(layoutManager);
        NewsAdapter newsAdapter = new NewsAdapter(getNews());
        newsTitleRecyclerView.setAdapter(newsAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity().findViewById(R.id.news_content_fragment) != null){
            isTwoPane = true;
        }else{
            isTwoPane = false;
        }
    }

    private List<News> getNews(){
        List<News> newsList = new ArrayList<>();
        for(int i = 0; i < 50; i++){
            News news = new News();
            news.setTitle("This is news title " + i);
            news.setContent(getRandomContent("This is Content " + i + " ."));
            newsList.add(news);
        }
        return newsList;
    }
    private String getRandomContent(String content){
        Random random = new Random();
        int len = random.nextInt(30) + 1;
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i  <len; i++){
            builder.append(content);
        }
        return builder.toString();
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
        private List<News> mNewsList;

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mews_item, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    News news = mNewsList.get(holder.getAbsoluteAdapterPosition());
                    if(isTwoPane){
                        NewsContentFragment newsContentFragment = (NewsContentFragment)getFragmentManager().findFragmentById(R.id.news_content_fragment);
                        newsContentFragment.refresh(news.getTitle(), news.getContent());
                    }else{
                        NewsContentActivity.actionStart(getActivity(), news.getTitle(), news.getContent());
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            News news = mNewsList.get(position);
            holder.newsTitleText.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            TextView newsTitleText;
            public ViewHolder(View view){
                super(view);
                newsTitleText = (TextView)view.findViewById(R.id.news_title_item);
            }
        }
        public NewsAdapter(List<News> newsList){
            this.mNewsList = newsList;
        }



    }
}
