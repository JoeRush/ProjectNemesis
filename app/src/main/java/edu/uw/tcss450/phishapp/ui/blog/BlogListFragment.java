package edu.uw.tcss450.phishapp.ui.blog;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.uw.tcss450.phishapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlogListFragment extends Fragment {

    public BlogListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blog_list, container, false);
        if(view instanceof RecyclerView) {
            ((RecyclerView) view).setLayoutManager(new GridLayoutManager(getContext(), 2));


            // ((LinearLayoutManager)((RecyclerView) view).getLayoutManager()).setOrientation((LinearLayoutManager.HORIZONTAL));
            ((RecyclerView) view).setAdapter(
                    new BlogRecyclerViewAdapter((BlogGenerator.getBlogList()))
            );
        }
        // Inflate the layout for this fragment
        return view;
    }
}
