package edu.uw.tcss450.phishapp.ui.blog;

import android.graphics.drawable.Icon;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.uw.tcss450.phishapp.R;
import edu.uw.tcss450.phishapp.databinding.FragmentBlogCardBinding;

public class BlogRecyclerViewAdapter extends RecyclerView.Adapter<BlogRecyclerViewAdapter.BlogViewHolder>{
    private final List<BlogPost> mBlogs;

    public BlogRecyclerViewAdapter(List<BlogPost> items) {
        this.mBlogs = items;
    }

    @NonNull
    @Override
    public BlogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BlogViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_blog_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BlogViewHolder holder, int position) {
        holder.setBlog(mBlogs.get(position));
    }

    @Override
    public int getItemCount() {
        return mBlogs.size();
    }

    public class BlogViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public FragmentBlogCardBinding binding;

        public BlogViewHolder(View view) {
            super(view);
            mView = view;
            binding = FragmentBlogCardBinding.bind(view);
            binding.buittonMore.setOnClickListener(this::handleMoreOrLess);
        }

        private void handleMoreOrLess(final View button) {
            if (binding.textPreview.getVisibility() == View.GONE) {
                binding.textPreview.setVisibility(View.VISIBLE);
                binding.buittonMore.setImageIcon(
                        Icon.createWithResource(
                                mView.getContext(),
                                R.drawable.ic_less_grey_24dp));

            } else {
                binding.textPreview.setVisibility((View.GONE));
                binding.buittonMore.setImageIcon(
                        Icon.createWithResource(
                                mView.getContext(),
                                R.drawable.ic_more_grey_24dp));


            }
        }

        void setBlog(final BlogPost blog) {
            binding.buttonFullPost.setOnClickListener(view ->
                    Navigation.findNavController(mView).navigate(
                            BlogListFragmentDirections
                                    .actionNavigationBlogsToBlogPostFragment(blog))

            );
            binding.textTitle.setText(blog.getTitle());
            binding.textPubdate.setText(blog.getPubDate());
            final String preview = Html.fromHtml(
                    blog.getTeaser(),
                    Html.FROM_HTML_MODE_COMPACT)
                    .toString().substring(0, 100)
                    + "...";
            binding.textPreview.setText(preview);

        }
    }
}
