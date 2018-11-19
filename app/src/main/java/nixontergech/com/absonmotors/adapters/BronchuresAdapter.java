package nixontergech.com.absonmotors.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yarolegovich.lovelydialog.LovelyCustomDialog;

import java.util.List;

import nixontergech.com.absonmotors.BronchureDisplay;
import nixontergech.com.absonmotors.R;
import nixontergech.com.absonmotors.models.Bronchures;


/**
 *
 */
public class BronchuresAdapter extends RecyclerView.Adapter<BronchuresAdapter.ViewHolder> {

    private final List<Bronchures> mValues;
    private Context context;
    private Dialog dialog;
    private ImageView bronchure_image;

    public BronchuresAdapter(List<Bronchures> values, Context ctx) {
        this.mValues = values;
        this.context = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.ProductsName.setText(mValues.get(position).getName());
       // holder.price.setText("Ksh "+mValues.get(position).getUnit_price());
        //click
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //view details
                //displayBronchure(mValues.get(position));
                Intent intent = new Intent(context, BronchureDisplay.class);
                intent.putExtra("broncurename", mValues.get(position).getName());
                intent.putExtra("url", mValues.get(position).getUrl());
                context.startActivity(intent);

            }
        });
        Glide.with(context)
                .load(mValues.get(position).getUrl())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView ProductsName;
        public Bronchures mItem;
        ImageView image;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ProductsName = view.findViewById(R.id.product_name);
            image = view.findViewById(R.id.imageView);

        }
    }

    //method to show Productss details
    public void displayBronchure(final Bronchures bronchures)
    {
        Log.e("Products", "Clicked displayVideo: "+bronchures.getName() );
        dialog= new LovelyCustomDialog(context)
                .setView(R.layout.bronchure_details)
                .setTopColorRes(R.color.colorPrimaryDark)
                .setTitle(bronchures.getName())
                .setCancelable(true)
                .show();
                bronchure_image = dialog.findViewById(R.id.imageView);
                //load Products image
                Glide.with(context)
                        .load(bronchures.getUrl())
                        .into(bronchure_image);

    }
}
