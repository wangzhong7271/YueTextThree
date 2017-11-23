package model;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.test.yuetextthree.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;

import java.util.ArrayList;

import bean.GlideImaGlideImageLoader;
import bean.Myuser;

/**
 * Created by 笔片 on 2017/11/23.
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Myuser mDatas;
    Context context;
    ArrayList mlist;

    public HomeAdapter(Myuser mDatas, Context context) {
        this.mDatas = mDatas;
        this.context = context;
    }

    //枚举类型
    private enum Item_Type {

        Typeone, Typetwo

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == Item_Type.Typeone.ordinal()) {
            View mView = LayoutInflater.from(context).inflate(R.layout.recycle_banner_item, null);
            ViewHolderA viewHolder = new ViewHolderA(mView);
            return viewHolder;

        } else if (viewType == Item_Type.Typetwo.ordinal()) {

            View mView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
            ViewHolderB viewHolder = new ViewHolderB(mView);
            return viewHolder;
        }
        return null;
    }

    /**
     * 绑定数据：可以直接拿到已经绑定控件的Viewholder对象
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderA) {
            mlist = new ArrayList();
            for (int i = 0; i < mDatas.getData().size(); i++) {
                mlist.add(mDatas.getData().get(i).getImage_url());
            }
            //设置图片加载器
            ((ViewHolderA) holder).mbanner.setImageLoader(new GlideImaGlideImageLoader());
            ((ViewHolderA) holder).mbanner.setImages(mlist);
            ((ViewHolderA) holder).mbanner.start();

        } else if (holder instanceof ViewHolderB) {
            ((ViewHolderB) holder).tv.setText(mDatas.getData().get(position).getTitle());
            String imgURL = mDatas.getData().get(position).getImage_url();
            Uri uri = Uri.parse(imgURL);
            ((ViewHolderB) holder).img.setImageURI(uri);
            // 如果设置了回调，则设置点击事件
            if (mOnItemClickLitener != null) {
                ((ViewHolderB) holder).itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = ((ViewHolderB) holder).getLayoutPosition();
                        mOnItemClickLitener.onItemClick(((ViewHolderB) holder).itemView, pos);
                    }
                });

                ((ViewHolderB) holder).itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int pos = ((ViewHolderB) holder).getLayoutPosition();
                        mOnItemClickLitener.onItemLongClick(((ViewHolderB) holder).itemView, pos);
                        return false;
                    }
                });
            }

        }

    }

    @Override
    public int getItemCount() {
        return mDatas.getData().size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    class ViewHolderA extends RecyclerView.ViewHolder {
        public Banner mbanner;

        public ViewHolderA(View itemView) {
            super(itemView);

            mbanner = (Banner) itemView.findViewById(R.id.banner);
        }
    }


    class ViewHolderB extends RecyclerView.ViewHolder {

        public SimpleDraweeView img;
        public TextView tv;

        public ViewHolderB(View itemView) {
            super(itemView);
            img = (SimpleDraweeView) itemView.findViewById(R.id.img);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }

    // 设置点击事件
    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

}
