// Generated by data binding compiler. Do not edit!
package com.philk7.ppaprojectapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.philk7.ppaprojectapp.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentPersonaResultBinding extends ViewDataBinding {
  @NonNull
  public final Button continueForkingButton;

  @NonNull
  public final LinearLayout linLay;

  @NonNull
  public final TextView matchDegreeLabel1;

  @NonNull
  public final TextView matchDegreeLabel2;

  @NonNull
  public final SeekBar matchDegreeSeekbar;

  @NonNull
  public final TextView personaDescription;

  @NonNull
  public final TextView rateDescriptionText;

  @NonNull
  public final TextView resultHeaderText;

  protected FragmentPersonaResultBinding(Object _bindingComponent, View _root, int _localFieldCount,
      Button continueForkingButton, LinearLayout linLay, TextView matchDegreeLabel1,
      TextView matchDegreeLabel2, SeekBar matchDegreeSeekbar, TextView personaDescription,
      TextView rateDescriptionText, TextView resultHeaderText) {
    super(_bindingComponent, _root, _localFieldCount);
    this.continueForkingButton = continueForkingButton;
    this.linLay = linLay;
    this.matchDegreeLabel1 = matchDegreeLabel1;
    this.matchDegreeLabel2 = matchDegreeLabel2;
    this.matchDegreeSeekbar = matchDegreeSeekbar;
    this.personaDescription = personaDescription;
    this.rateDescriptionText = rateDescriptionText;
    this.resultHeaderText = resultHeaderText;
  }

  @NonNull
  public static FragmentPersonaResultBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_persona_result, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentPersonaResultBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentPersonaResultBinding>inflateInternal(inflater, R.layout.fragment_persona_result, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentPersonaResultBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_persona_result, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentPersonaResultBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentPersonaResultBinding>inflateInternal(inflater, R.layout.fragment_persona_result, null, false, component);
  }

  public static FragmentPersonaResultBinding bind(@NonNull View view) {
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
  public static FragmentPersonaResultBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentPersonaResultBinding)bind(component, view, R.layout.fragment_persona_result);
  }
}