package com.example.TCSS450GROUP1.ui.home;

import android.content.res.Resources;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;

import com.example.TCSS450GROUP1.R;
import com.example.TCSS450GROUP1.databinding.FragmentChatMessageBinding;
import com.example.TCSS450GROUP1.ui.chat.ChatMessage;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.shape.CornerFamily;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.NewestMessageViewHolder> {

    private final List<ChatMessage> mMessages;
    private final String mEmail;
    public HomeAdapter(List<ChatMessage> messages, String email) {
        this.mMessages = messages;
        mEmail = email;
    }


    @NonNull
    @Override
    public NewestMessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewestMessageViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_chat_message, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.NewestMessageViewHolder holder, int position) {
        holder.setMessage(mMessages.get(position));
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    class NewestMessageViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private FragmentChatMessageBinding binding;

        public NewestMessageViewHolder(@NonNull View view) {
            super(view);
            mView = view;
            binding = FragmentChatMessageBinding.bind(view);
        }

        void setMessage(final ChatMessage message) {
            final Resources res = mView.getContext().getResources();
            final MaterialCardView card = binding.cardRoot;

            int standard = (int) res.getDimension(R.dimen.chat_margin);

            if (!mEmail.equals(message.getSender())) {
                //This message is from the user. Format it as such
                binding.textMessage.setText(message.getMessage());

                // Set this View to the right (end) side
                ((FrameLayout.LayoutParams) card.getLayoutParams()).gravity =
                        Gravity.END;


                binding.textMessage.setTextColor(
                        res.getColor(R.color.secondaryTextColorFade, null));



                //Round the corners on the left side
                card.setShapeAppearanceModel(
                        card.getShapeAppearanceModel()
                                .toBuilder()
                                .setTopLeftCorner(CornerFamily.ROUNDED,standard * 2)
                                .setBottomLeftCorner(CornerFamily.ROUNDED,standard * 2)
                                .setBottomRightCornerSize(0)
                                .setTopRightCornerSize(0)
                                .build());

                card.requestLayout();
            }
        }
    }
}