package edu.uw.tcss450.phishapp.ui.blog;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import edu.uw.tcss450.phishapp.R;
import java.io.Serializable;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class BlogListFragmentDirections {
  private BlogListFragmentDirections() {
  }

  @NonNull
  public static ActionNavigationBlogsToBlogPostFragment actionNavigationBlogsToBlogPostFragment(
      @NonNull BlogPost blog) {
    return new ActionNavigationBlogsToBlogPostFragment(blog);
  }

  public static class ActionNavigationBlogsToBlogPostFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionNavigationBlogsToBlogPostFragment(@NonNull BlogPost blog) {
      if (blog == null) {
        throw new IllegalArgumentException("Argument \"blog\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("blog", blog);
    }

    @NonNull
    public ActionNavigationBlogsToBlogPostFragment setBlog(@NonNull BlogPost blog) {
      if (blog == null) {
        throw new IllegalArgumentException("Argument \"blog\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("blog", blog);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("blog")) {
        BlogPost blog = (BlogPost) arguments.get("blog");
        if (Parcelable.class.isAssignableFrom(BlogPost.class) || blog == null) {
          __result.putParcelable("blog", Parcelable.class.cast(blog));
        } else if (Serializable.class.isAssignableFrom(BlogPost.class)) {
          __result.putSerializable("blog", Serializable.class.cast(blog));
        } else {
          throw new UnsupportedOperationException(BlogPost.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
        }
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_navigation_blogs_to_blogPostFragment;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public BlogPost getBlog() {
      return (BlogPost) arguments.get("blog");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionNavigationBlogsToBlogPostFragment that = (ActionNavigationBlogsToBlogPostFragment) object;
      if (arguments.containsKey("blog") != that.arguments.containsKey("blog")) {
        return false;
      }
      if (getBlog() != null ? !getBlog().equals(that.getBlog()) : that.getBlog() != null) {
        return false;
      }
      if (getActionId() != that.getActionId()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (getBlog() != null ? getBlog().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionNavigationBlogsToBlogPostFragment(actionId=" + getActionId() + "){"
          + "blog=" + getBlog()
          + "}";
    }
  }
}
