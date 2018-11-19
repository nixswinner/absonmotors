package nixontergech.com.absonmotors;

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

import java.util.ArrayList;
import java.util.List;

import nixontergech.com.absonmotors.adapters.BronchuresAdapter;
import nixontergech.com.absonmotors.adapters.VideosAdapter;
import nixontergech.com.absonmotors.models.Bronchures;


public class BroncuresFragment extends Fragment {

    private Context context;
    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;

    public BroncuresFragment() {
        // Required empty public constructor
    }
    public static BroncuresFragment newInstance() {
        BroncuresFragment fragment = new BroncuresFragment();
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
        View view= inflater.inflate(R.layout.fragment_broncures, container,
                false);
        context = getActivity();
        recyclerView =view.findViewById(R.id.recyclerview);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager( new GridLayoutManager(context, 2));


        getBronchures();
        return view;
    }

    private void getBronchures() {
        List<Bronchures> bronchuresList = new ArrayList<>();
        bronchuresList.add(new Bronchures("Fabio Spec","http://rambosoft.co.ke/data/bronchures/FABIO%20SPEC.jpg"));
        bronchuresList.add(new Bronchures("HJ125-28","http://rambosoft.co.ke/data/bronchures/HJ125-28.jpg"));
        bronchuresList.add(new Bronchures("HJ125-50","http://rambosoft.co.ke/data/bronchures/HJ125-50.jpg"));
        bronchuresList.add(new Bronchures("Haojin 3 Models","http://rambosoft.co.ke/data/bronchures/Haojin%203%20Models.jpg"));
        bronchuresList.add(new Bronchures("HJ200ZH","http://rambosoft.co.ke/data/bronchures/HJ200ZH-6C.jpg"));
        bronchuresList.add(new Bronchures("HJ200GY-2","http://rambosoft.co.ke/data/bronchures/Haojin%20HJ200GY-2%20Brochures%20and%20Specifications.jpg"));

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager( new GridLayoutManager(context,
                2));
        recyclerView.setAdapter(new BronchuresAdapter(bronchuresList,context));
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
