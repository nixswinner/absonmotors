package nixontergech.com.absonmotors.adapters;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.yarolegovich.lovelydialog.LovelyCustomDialog;
import com.yarolegovich.lovelydialog.LovelyTextInputDialog;

import java.util.List;

import nixontergech.com.absonmotors.R;
import nixontergech.com.absonmotors.models.Videos;


/**
 * Recycler viewer adapter to display Receiptss
 */
public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.ViewHolder> {

    private final List<Videos> mValues;
    private Context context;
    private Dialog dialog;
    private VideoView videoView;

    public VideosAdapter(List<Videos> values, Context ctx) {
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
        holder.name.setText(mValues.get(position).getName());
        //click
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //view details
                displayVideo(mValues.get(position));

            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView name;
        public Videos mItem;
        ImageView image;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            name = view.findViewById(R.id.name);
        }
    }

    //method to show Productss details
    public void displayVideo(final Videos videos)
    {
        Log.e("Products", "Clicked displayVideo: "+videos.getName() );
        dialog= new LovelyCustomDialog(context)
                .setView(R.layout.video_details)
                .setTopColorRes(R.color.colorPrimaryDark)
                .setTitle(videos.getName())
                .setCancelable(true)
                .show();
        videoView=dialog.findViewById(R.id.videoView);

        //load video and start
        MediaController mc = new MediaController(context);
        mc.setAnchorView(videoView);
        mc.setMediaPlayer(videoView);
        Uri video = Uri.parse(videos.getUrl());
        videoView.setMediaController(mc);
        videoView.setVideoURI(video);
        videoView.start();
    }

    public void alertwithinput(String msg)
    {
        new LovelyTextInputDialog(context)
                .setTopColorRes(R.color.colorPrimaryDark)
                .setTitle("Provide")
                .setMessage(msg)
                .setInputFilter("Error.Invalid Input",
                        new LovelyTextInputDialog.TextFilter() {
                    @Override
                    public boolean check(String text) {
                        return text.matches("\\w+");
                    }
                })
                .setConfirmButton(android.R.string.ok, new LovelyTextInputDialog.OnTextInputConfirmListener() {
                    @Override
                    public void onTextInputConfirmed(String text) {
                    }
                })
                .show();
    }
}
