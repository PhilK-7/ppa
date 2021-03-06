// Generated by data binding compiler. Do not edit!
package com.philk7.ppaprojectapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.philk7.ppaprojectapp.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentSharedPrefViewerBinding extends ViewDataBinding {
  @NonNull
  public final RecyclerView spRecycler;

  protected FragmentSharedPrefViewerBinding(Object _bindingComponent, View _root,
      int _localFieldCount, RecyclerView spRecycler) {
    super(_bindingComponent, _root, _localFieldCount);
    this.spRecycler = spRecycler;
  }

  @NonNull
  public static FragmentSharedPrefViewerBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_shared_pref_viewer, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentSharedPrefViewerBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentSharedPrefViewerBinding>inflateInternal(inflater, R.layout.fragment_shared_pref_viewer, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentSharedPrefViewerBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_shared_pref_viewer, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentSharedPrefViewerBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentSharedPrefViewerBinding>inflateInternal(inflater, R.layout.fragment_shared_pref_viewer, null, false, component);
  }

  public static FragmentSharedPrefViewerBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static FragmentSharedPrefViewerBinding bind(@NonNull View view,
      @Nullable Object component) {
    return (FragmentSharedPrefViewerBinding)bind(component, view, R.layout.fragment_shared_pref_viewer);
  }
}
