package com.mynews.benomari.apitest.model.Model.MostPopularModel;

    import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
    import com.mynews.benomari.apitest.model.Model.MostPopularModel.MediaMetadatum;

public class Medium {

        @SerializedName("media-metadata")
        @Expose
        private List<MediaMetadatum> mediaMetadata = null;

        public List<MediaMetadatum> getMediaMetadata() {
            return mediaMetadata;
        }
    }

