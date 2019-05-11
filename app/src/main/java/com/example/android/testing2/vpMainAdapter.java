package com.example.android.testing2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class vpMainAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] images = {R.drawable.slide1, R.drawable.slide2, R.drawable.slide3};

    public vpMainAdapter(Context context, FragmentManager childFragmentManager) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.listCinemaLogo);
        imageView.setImageResource(images[position]);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (images[position] == 2131165343) {
                    Toast.makeText(context, "Saekano Movie", Toast.LENGTH_SHORT).show();
                } else if (images[position]== 2131165344) {
                    Toast.makeText(context, "Pikachu", Toast.LENGTH_SHORT).show();
                } else if (images[position] == 2131165345) {
                    Toast.makeText(context, "Kimi no sei", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(context, "current "+images[position]+"", Toast.LENGTH_SHORT).show();
                }
            }

        });
        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}
