// Generated by data binding compiler. Do not edit!
package com.philk7.ppaprojectapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.philk7.ppaprojectapp.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentIntroFinalBinding extends ViewDataBinding {
  @NonNull
  public final Button introFinishButton;

  @NonNull
  public final TextView ppAssignedText;

  @NonNull
  public final TextView showPersonaText;

  protected FragmentIntroFinalBinding(Object _bindingComponent, View _root, int _localFieldCount,
      Button introFinishButton, TextView ppAssignedText, TextView showPersonaText) {
    super(_bindingComponent, _root, _localFieldCount);
    this.introFinishButton = introFinishButton;
    this.ppAssignedText = ppAssignedText;
    this.showPersonaText = showPersonaText;
  }

  @NonNull
  public static FragmentIntroFinalBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_intro_final, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentIntroFinalBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentIntroFinalBinding>inflateInternal(inflater, R.layout.fragment_intro_final, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentIntroFinalBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_intro_final, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentIntroFinalBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentIntroFinalBinding>inflateInternal(inflater, R.layout.fragment_intro_final, null, false, component);
  }

  public static FragmentIntroFinalBinding bind(@NonNull View view) {
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
  public static FragmentIntroFinalBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentIntroFinalBinding)bind(component, view, R.layout.fragment_intro_final);
  }
}
