package com.example.macbookair.myapplication2.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.macbookair.myapplication2.R;
import com.example.macbookair.myapplication2.activities.DetailsActivity;
import com.example.macbookair.myapplication2.constants.ExtraConstants;
import com.example.macbookair.myapplication2.constants.SocialNetworks;
import com.example.macbookair.myapplication2.models.Person;

import java.util.List;


/**
 * Created by macbookair on 1/20/18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    public static int INDEX = 0;
    private static List<Person> personList;
    private Context mContext;

    public RecyclerViewAdapter(Context mContext, List<Person> personList) {
        this.mContext = mContext;
        RecyclerViewAdapter.personList = personList;
    }

    public static List<Person> getPersonList() {
        return personList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.recycler_view, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Person person = personList.get(position);
        holder.txtName.setText(person.getName());
        holder.txtCategory.setText(Person.getCategory());
        Glide.with(mContext).load(person.getImageID()).into(holder.profileImage);
        if (!person.getSocialNet().equals("") && person.getSocialNet().equals(SocialNetworks.FACEBOOK))
            holder.networkImage.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.facebook));
        else if (!person.getSocialNet().equals("") && person.getSocialNet().equals(SocialNetworks.TWITTER))
            holder.networkImage.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.twitter));

        else {
        }

        Glide.with(mContext)
                .load(person.getImage())
                .apply(new RequestOptions().error(R.drawable.unknown).circleCrop())
                .into(holder.profileImage);

        holder.recyclerViewLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                INDEX = position;
                Intent i = new Intent(mContext, DetailsActivity.class);
                i.putExtra(ExtraConstants.FULL_NAME, person.getName());
                i.putExtra(ExtraConstants.COUNTRY, person.getCountry());
                i.putExtra(ExtraConstants.E_MAIL, person.getEmail());
                i.putExtra(ExtraConstants.PROFILE_IMAGE, person.getImageID());
                i.putExtra(ExtraConstants.NETWORK_IMAGE, person.getSocialNet());
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return personList.size() > 0 ? personList.size() : 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView profileImage;
        private TextView txtName;
        private TextView txtCategory;
        private ImageView networkImage;
        private RelativeLayout recyclerViewLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.profile_image);
            txtName = itemView.findViewById(R.id.txt_name);
            txtCategory = itemView.findViewById(R.id.txt_category);
            networkImage = itemView.findViewById(R.id.network_image);
            recyclerViewLayout = itemView.findViewById(R.id.recycler_view_layout);
        }

    }
}
