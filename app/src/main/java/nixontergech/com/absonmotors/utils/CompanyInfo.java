package nixontergech.com.absonmotors.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.bumptech.glide.Glide;

import nixontergech.com.absonmotors.R;


public class CompanyInfo extends Fragment {

    private Context context;
    private OnFragmentInteractionListener mListener;
    private VideoView videoView,videoView1;
    private ImageView image1,image,image2,image3,image4;

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
        videoView = view.findViewById(R.id.videoView);
        image = view.findViewById(R.id.image);
        image1 = view.findViewById(R.id.image1);
        image2 = view.findViewById(R.id.image2);
        image3 = view.findViewById(R.id.image3);
        image4 = view.findViewById(R.id.image4);
        //
        Glide.with(context)
                .load("http://rambosoft.co.ke/data/bronchures/HJ125-28.jpg")
                .into(image);
        Glide.with(context)
                .load("http://rambosoft.co.ke/data/bronchures/HJ200GY-2.jpg")
                .into(image1);
        Glide.with(context)
                .load("http://rambosoft.co.ke/data/bronchures/HJ150-11A.jpg")
                .into(image2);
        Glide.with(context)
                .load("http://rambosoft.co.ke/data/bronchures/Haojin%203%20Models.jpg")
                .into(image3);
        Glide.with(context)
                .load("http://rambosoft.co.ke/data/bronchures/IMGP4676.JPG")
                .into(image4);


        String LINK = "http://rambosoft.co.ke/data/shortvideos/MOLO%20UPS%202.mp4";
        MediaController mc = new MediaController(context);
        mc.setAnchorView(videoView);
        mc.setMediaPlayer(videoView);
        Uri video = Uri.parse(LINK);
        videoView.setMediaController(mc);
        videoView.setVideoURI(video);
        videoView.start();

        return view;
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
