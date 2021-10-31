package com.moringaschool.cocktaillush.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.cocktaillush.R;
import com.moringaschool.cocktaillush.models.Drink;
import com.moringaschool.cocktaillush.ui.CocktailDetailActivity;
//import com.moringaschool.cocktaillush.util.ItemTouchHelperAdapter;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CocktailListadapter  extends RecyclerView.Adapter<CocktailListadapter.CocktailViewHolder>{
    private List<Drink> mCocktails;
    private Context mContext;
    //private ItemTouchHelper mTouchHelper;

    public CocktailListadapter(Context context, List<Drink> cocktails) {
        mContext = context;
        mCocktails = cocktails;
    }

    @NonNull
    @Override
    public CocktailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cocktail_list_item, parent, false);
        CocktailViewHolder viewHolder = new CocktailViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CocktailViewHolder holder, int position) {
            holder.bindCocktail(mCocktails.get(position));
    }

    @Override
    public int getItemCount() {
        return mCocktails.size();
    }


    //the 2 methods
//            @Override
//            public void onItemMove(int fromPosition, int toPosition) {
//                 Drink fromCocktail = mCocktails.get(fromPosition);
//                 mCocktails.remove(fromCocktail);
//                 notifyItemMoved(fromPosition,toPosition);
//            }
//
//            @Override
//            public void onItemSwiped(int position) {
//                mCocktails.remove(position);
//                notifyItemRemoved(position);
//
//            }

//    public void  setTouchHelper(ItemTouchHelper touchHelper) {
//        this.mTouchHelper = touchHelper;
//    }

    public class CocktailViewHolder extends RecyclerView.ViewHolder  implements
             View.OnClickListener
            // View.OnTouchListener,
             //GestureDetector.OnGestureListener
         {
        @BindView(R.id.cocktailImageView) ImageView mCocktailImageView;
        @BindView(R.id.cocktailNameTextView) TextView mNameTextView;
        @BindView(R.id.instructionsTextView) TextView mInstructionsTextView;
        @BindView(R.id.cocktailClassTextView) TextView mCocktailClassTextView;

        private Context mContext;
        GestureDetector mGestureDetector;


        public CocktailViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();


          //  mGestureDetector = new GestureDetector (itemView.getContext(),this);
            itemView.setOnClickListener(this);
           // itemView.setOnTouchListener(this);
        }

        public void bindCocktail(Drink cocktail) {
            Picasso.get().load(cocktail.getStrDrinkThumb()).into(mCocktailImageView);
            mNameTextView.setText(cocktail.getStrDrink());
            mInstructionsTextView.setText(cocktail.getStrInstructions());
            mCocktailClassTextView.setText("Type: " + cocktail.getStrAlcoholic());
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, CocktailDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("cocktails", Parcels.wrap(mCocktails));
            mContext.startActivity(intent);
        }

//             @Override
//             public boolean onDown(MotionEvent e) {
//                 return false;
//             }
//
//             @Override
//             public void onShowPress(MotionEvent e) {
//
//             }
//
//             @Override
//             public boolean onSingleTapUp(MotionEvent e) {
//                 return false;
//             }
//
//             @Override
//             public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//                 return false;
//             }
//
//             @Override
//             public void onLongPress(MotionEvent e) {
//                mTouchHelper.startDrag(this);
//
//             }
//
//             @Override
//             public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//                 return false;
//             }
//
//             @Override
//             public boolean onTouch(View v, MotionEvent event) {
//                 mGestureDetector.onTouchEvent(event);
//                 return true;
//             }
         }
}
