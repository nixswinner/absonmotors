package nixontergech.com.absonmotors.adapters;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yarolegovich.lovelydialog.LovelyCustomDialog;

import java.util.List;
import nixontergech.com.absonmotors.models.Products;


/**
 * Recycler viewer adapter to display Receiptss
 */
public class ProductssAdapter extends RecyclerView.Adapter<ProductssAdapter.ViewHolder> {

    private final List<Products> mValues;
    private Context context;
    private Dialog dialog;
  /*  private final List<Receipts> mValues;
    ReceiptsFragment.OnFragmentInteractionListener mListener;*/
   // private final OnListFragmentInteractionListener mListener;
    private TextView Products_description,sellers_name,Products_price,Products_name;
    private ImageView Products_image;

    public ProductssAdapter(List<Products> values, Context ctx) {
        this.mValues = values;
        this.context = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.products_item, parent, false);
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
                displayProductsDetails(mValues.get(position));

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
        public final TextView ProductsName,price;
        public Products mItem;
        ImageView image;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ProductsName = view.findViewById(R.id.Products_name);
            image = view.findViewById(R.id.image);
            price = view.findViewById(R.id.price);
        }
    }

    //method to show Productss details
    public void displayProductsDetails(final Products Products)
    {
        Log.e("Products", "Clicked displayProductsDetails: "+Products.getName() );
        dialog= new LovelyCustomDialog(context)
                .setView(R.layout.Products_details)
                .setTopColorRes(R.color.colorPrimaryDark)
                .setTitle(Products.getName()+" details")
                .setCancelable(true)
                .setListener(R.id.add_to_cart, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final ProgressDialog progressDialog = ProgressDialog.show(
                                context, "Adding to Basket"
                                , "Adding "+Products.getName()+" to your basket kindly wait...",
                                true);
                        progressDialog.show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                progressDialog.dismiss();
                                dialog.dismiss();
                                Toast toast = Toast.makeText(context,
                                        "You can add another Products to your basket!",
                                        Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                            }
                        }, 3000);

                    }
                })
                .show();
        //
        Products_description = dialog.findViewById(R.id.Products_description);
        sellers_name = dialog.findViewById(R.id.sellers_name);
        Products_price = dialog.findViewById(R.id.Products_price);
        Products_name=dialog.findViewById(R.id.Products_name);
        Products_image = dialog.findViewById(R.id.Products_image);

        //set each details
        Products_description.setText(Products.getDescription());
        sellers_name.setText(""+Products.getProfile());
        Products_price.setText(Products.getUnit_price());
        Products_name.setText(Products.getName());

        //load Products image
        Glide.with(context)
                .load(Products.getImage())
                .into(Products_image);

    }
}
