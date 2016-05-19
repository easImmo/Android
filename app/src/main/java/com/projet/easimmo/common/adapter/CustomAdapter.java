package com.projet.easimmo.common.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.projet.easimmo.R;
import com.projet.easimmo.dto.ImageDTO;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 12/05/2016.
 */
public class CustomAdapter extends PagerAdapter {

    Context context;
    List<ImageDTO> imageDTOList;
    //int[] imageId = {R.drawable.image1, R.drawable.image2v, R.drawable.image3v};

    public CustomAdapter(Context context, List<ImageDTO> imageDTOs){
        this.context = context;
        imageDTOList = new ArrayList<>();
        imageDTOList.addAll(imageDTOs);
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // TODO Auto-generated method stub

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();

        ImageDTO imageDTO = imageDTOList.get(position);

        String imageUri = "http://easimmoapi.nicolasdu.com/images/"+imageDTO.getmId();

        View viewItem = inflater.inflate(R.layout.image_item, container, false);
        //ImageView imageView = (ImageView) viewItem.findViewById(R.id.imageView);
        //imageView.setImageResource(imageId[position]);

        ImageView ivBasicImage = (ImageView) viewItem.findViewById(R.id.imageView);
        Picasso.with(context).load(imageUri).into(ivBasicImage);
        ((ViewPager)container).addView(viewItem);

        return viewItem;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return imageDTOList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        // TODO Auto-generated method stub

        return view == ((View)object);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        ((ViewPager) container).removeView((View) object);
    }

}