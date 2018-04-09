package com.thrai.THRAI;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;
import com.veinhorn.scrollgalleryview.MediaInfo;
import com.veinhorn.scrollgalleryview.ScrollGalleryView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fahim on 4/8/18.
 */
public class BookDetailImageFragment extends Fragment implements ScrollGalleryView.OnImageClickListener {
    private ScrollGalleryView scrollGalleryView=null;

    private List<String> images;
    private FragmentManager fragmentManager;
    private List<MediaInfo> infos;

    //private View view=null;
    //private FragmentActivity myContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_book_detail_image, container, false);

        scrollGalleryView = view.findViewById(R.id.scroll_gallery_view);


        scrollGalleryView.setThumbnailSize(150);
        scrollGalleryView.setZoom(false);
        scrollGalleryView.setFragmentManager(getChildFragmentManager());
        scrollGalleryView.addMedia(infos);

        scrollGalleryView.addOnImageClickListener(this);


        return view;
    }

    public void setImages(List<String> images) {
        this.images = images;
        infos = new ArrayList<>(images.size());
        for (String url : images)
            infos.add(MediaInfo.mediaLoader(new PicassoImageLoader(url)));
    }

    @Override
    public void onClick() {
        showZoomImage(images.get(scrollGalleryView.getCurrentItem()));
    }

    private void showZoomImage(String url){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        LayoutInflater inflater = getLayoutInflater();
        View vi = inflater.inflate(R.layout.image_zoom_layout,null);
        PhotoView iv = (PhotoView) vi.findViewById(R.id.iv_dialog_imgView);

        Picasso.with(getContext())
                .load(url)
                .into(iv);

        builder.setView(vi);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
    }
}
