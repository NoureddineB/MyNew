package com.mynews.benomari.apitest.activity;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.mynews.benomari.apitest.R;
import com.mynews.benomari.apitest.model.Model.Article;
import com.mynews.benomari.apitest.model.Model.Result;
import com.mynews.benomari.apitest.model.Model.SearchModel.Docs;
import com.mynews.benomari.apitest.model.Model.SearchModel.Response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ApiViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.title)
    TextView textView;
    @BindView(R.id.country)
    TextView textViewCountry;
    @BindView(R.id.date)
    TextView textViewDate;
    @BindView(R.id.fragment_image)
    ImageView imageView;
    private final static String BASE_URL = "https://nytimes.com/";


    public ApiViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }


    public <T> void updateWithArticle(T articles, RequestManager glide) {
        if (articles instanceof Result) {
            Result article = ((Result) articles);
            //Titles
            this.textView.setText(article.getTitles());

            //Formated Date
            this.textViewDate.setText(formatDate(article.getPublishedDate()));

            // Subsections and Sections
            if ((article.getSubsection() != null) && (!article.getSubsection().isEmpty())) {
                this.textViewCountry.setText(article.getSection() + " > " + article.getSubsection());
            } else {
                this.textViewCountry.setText(article.getSection());
            }

            //Article image
            if ((article.getMultimedia() != null) && (!article.getMultimedia().isEmpty())) {
                glide.load(article.getMultimedia().get(1).getUrl()).apply(RequestOptions.centerCropTransform()).into(imageView);
            }

            //Most Popular Media
            else if (article.getMedia() != null) {
                glide.load(article.getMedia().get(0).getMediaMetadata().get(0).getUrl()).apply(RequestOptions.centerCropTransform()).into(imageView);
            }

            //Search result
        } else if (articles instanceof Docs) {
            Docs article = ((Docs) articles);
            this.textViewCountry.setText(article.getSectionName());
            this.textViewDate.setText(formatDate(article.getPubDate()));
            this.textView.setText(article.getHeadline().getMain());

            if (!article.getMultimedia().isEmpty()) {
                String mUrl = BASE_URL + article.getMultimedia().get(0).getUrl();
                glide.load(mUrl).apply(RequestOptions.centerCropTransform()).into(imageView);
                Log.e("TAGULRSEARCH", mUrl);
            }
        }


    }


    //Format the Date
    @SuppressLint("SimpleDateFormat")
    private String formatDate(String dateString) {
        List<String> formatStrings = Arrays.asList("yyyy-MM-dd", "yyyy-MM-dd'T'HH:mm:ssZ");

        for (String formatString : formatStrings) {
            try {
                SimpleDateFormat sd;
                Date d = new SimpleDateFormat(formatString).parse(dateString);
                sd = new SimpleDateFormat("dd/MM/yy");
                return sd.format(d);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return "";
    }
}




