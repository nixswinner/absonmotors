package nixontergech.com.absonmotors;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import nixontergech.com.absonmotors.adapters.ProductsAdapter;
import nixontergech.com.absonmotors.models.Products;
import nixontergech.com.absonmotors.utils.InternetConnection;
import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.views.BannerSlider;


public class MainFragment extends Fragment {


    private OnFragmentInteractionListener mListener;
    RecyclerView recyclerView;
    Context context;

    public MainFragment() {
        // Required empty public constructor
    }
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_main,
                container, false);
        context = getActivity();
        BannerSlider bannerSlider = view.findViewById(R.id.banner_slider1);
        List<Banner> banners=new ArrayList<>();
        //add banner using image url

        banners.add(new RemoteBanner("http://rambosoft.co.ke/data/logo.png"));
        banners.add(new RemoteBanner("http://rambosoft.co.ke/data/motorbikes/EVO%20150.png"));
        banners.add(new RemoteBanner("http://rambosoft.co.ke/data/3wheelers/Haojin%203%20Models.jpg"));
        //add banner using resource drawable
        /*banners.add(new DrawableBanner(R.drawable.logo));
        banners.add(new DrawableBanner(R.drawable.logo));
        banners.add(new DrawableBanner(R.drawable.logo));*/
        bannerSlider.setBanners(banners);

        recyclerView =view.findViewById(R.id.recyclerview);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager( new GridLayoutManager(context, 2));
        checknet();

        return view;
    }

    private void checknet() {
        if (InternetConnection.checkConnection(context))
        {
            fetchProducts();
        }else
        {
            //no internet
            showNoInternetDialog();
        }
    }

    private void showNoInternetDialog() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_warning);
        dialog.setCancelable(false);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;


        ((AppCompatButton) dialog.findViewById(R.id.bt_retry)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                checknet();
            }
        });
        ((AppCompatButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                getActivity().finish();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    private void fetchProducts() {
        List<Products> productsList = new ArrayList<>();
        productsList.add(new Products(
                "Apex haojin",
                "http://rambosoft.co.ke/data/motorbikes/Apex%20hj%20125-23.jpg",
                ""));
        productsList.add(new Products(
                "EVO",
                "http://rambosoft.co.ke/data/motorbikes/FABIO%20125.png",
                ""));
        productsList.add(new Products(
                "Fabio",
                "http://rambosoft.co.ke/data/motorbikes/FABIO%20125.png",
                ""));
        productsList.add(new Products(
                "HJ 110-11 Lyra",
                "http://rambosoft.co.ke/data/motorbikes/HJ%20110-11%20LYRA.png",
                ""
        ));
        productsList.add(new Products(
                "HJ 125-9",
                "http://rambosoft.co.ke/data/motorbikes/HJ125-9(A).jpg",
                ""));
        productsList.add(new Products(
                "HJ 150",
                "http://rambosoft.co.ke/data/motorbikes/HJ%20150-11A%20SMALL%20TANK.png",
                ""));

        productsList.add(new Products(
                "Haojin 3 Models",
                "http://rambosoft.co.ke/data/3wheelers/Haojin%203%20Models.jpg",
                ""
        ));
        productsList.add(new Products(
                "Haojin Coke 2",
                "http://rambosoft.co.ke/data/3wheelers/Haojin%20Coke%202%20units%20%20.JPG",
                ""
        ));
        productsList.add(new Products(
                "Haojin Superman Bidco",
                "http://rambosoft.co.ke/data/3wheelers/Haojin%20Superman%20Bidco%20Golden%20Fry.jpg",
                ""
        ));
        productsList.add(new Products(
                "Haojin Superman Coke",
                "http://rambosoft.co.ke/data/3wheelers/Haojin%20Superman%20Coke%20.JPG",
                ""
        ));
        productsList.add(new Products(
                "Haojin Superman Geaa",
                "http://rambosoft.co.ke/data/3wheelers/Haojin%20Superman%20Gaea%20Nuru%20Bidco.jpg",
                ""
        ));
        productsList.add(new Products(
                "Haojin Superman Soap",
                "http://rambosoft.co.ke/data/3wheelers/Haojin%20Superman%20Soap%20Gaea.jpg",
                ""
        ));

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager( new GridLayoutManager(context,
                2));
        recyclerView.setAdapter(new ProductsAdapter(productsList,context));


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
