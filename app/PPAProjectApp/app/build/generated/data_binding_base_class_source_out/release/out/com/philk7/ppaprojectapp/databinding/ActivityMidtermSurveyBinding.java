// Generated by data binding compiler. Do not edit!
package com.philk7.ppaprojectapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.philk7.ppaprojectapp.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityMidtermSurveyBinding extends ViewDataBinding {
  @NonNull
  public final TextView surveyLinkText;

  protected ActivityMidtermSurveyBinding(Object _bindingComponent, View _root, int _localFieldCount,
      TextView surveyLinkText) {
    super(_bindingComponent, _root, _localFieldCount);
    this.surveyLinkText = surveyLinkText;
  }

  @NonNull
  public static ActivityMidtermSurveyBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_midterm_survey, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityMidtermSurveyBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityMidtermSurveyBinding>inflateInternal(inflater, R.layout.activity_midterm_survey, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityMidtermSurveyBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_midterm_survey, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityMidtermSurveyBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityMidtermSurveyBinding>inflateInternal(inflater, R.layout.activity_midterm_survey, null, false, component);
  }

  public static ActivityMidtermSurveyBinding bind(@NonNull View view) {
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
  public static ActivityMidtermSurveyBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivityMidtermSurveyBinding)bind(component, view, R.layout.activity_midterm_survey);
  }
}