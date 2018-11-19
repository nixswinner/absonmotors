package nixontergech.com.absonmotors.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import nixontergech.com.absonmotors.R;
import nixontergech.com.absonmotors.adapters.VideosAdapter;
import nixontergech.com.absonmotors.models.Videos;


public class CompanyInfo extends Fragment {

    private Context context;
    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;

    public CompanyInfo() {
        // Required empty public constructor
    }

    public static CompanyInfo newInstance() {
        CompanyInfo fragment = new CompanyInfo();
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
        View view= inflater.inflate(R.layout.fragment_company_info,
                container, false);
        context = getActivity();
        recyclerView =view.findViewById(R.id.recyclerview);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager( new GridLayoutManager(context, 2));


        getVideos();


        return view;
    }
    public void getVideos()
    {
        List<Videos> videosList = new ArrayList<>();
        videosList.add(new Videos("MOLO CUTWAYS 1","http://rambosoft.co.ke/data/shortvideos/MOLO%20CUTWAYS%201.mp4"));
        videosList.add(new Videos("MOLO CUTWAYS 2","http://rambosoft.co.ke/data/shortvideos/MOLO%20CUTWAYS%202.mp4"));
        videosList.add(new Videos("MOLO UPS 1","http://rambosoft.co.ke/data/shortvideos/MOLO%20UPS%201.mp4"));
        videosList.add(new Videos("MOLO UPS 2","http://rambosoft.co.ke/data/shortvideos/MOLO%20UPS%202.mp4"));
        videosList.add(new Videos("MOLO UPS 3","http://rambosoft.co.ke/data/shortvideos/MOLO%20UPS3.mp4"));
        videosList.add(new Videos("MOLO CUTWAYS 2","http://rambosoft.co.ke/data/shortvideos/MOLO%20CUTWAYS%203.mp4"));
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL, false);
      /*  recyclerView.setLayoutManager( new GridLayoutManager(context,
                2));*/
      recyclerView.setLayoutManager(horizontalLayoutManager);
        recyclerView.setAdapter(new VideosAdapter(videosList,context));
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
        void onFragmentInteraction(Uri uri);
    }
}
