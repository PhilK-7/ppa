// Generated by data binding compiler. Do not edit!
package com.philk7.ppaprojectapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.philk7.ppaprojectapp.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentAssistantChoiceBinding extends ViewDataBinding {
  @NonNull
  public final TextView assistantDescr;

  @NonNull
  public final CardView assistantDescrCard;

  @NonNull
  public final TextView assistantTitle;

  @NonNull
  public final Button choiceAutoButton;

  @NonNull
  public final Button choiceNAButton;

  @NonNull
  public final Button choiceRAButton;

  @NonNull
  public final Button confirmChoiceButton;

  protected FragmentAssistantChoiceBinding(Object _bindingComponent, View _root,
      int _localFieldCount, TextView assistantDescr, CardView assistantDescrCard,
      TextView assistantTitle, Button choiceAutoButton, Button choiceNAButton,
      Button choiceRAButton, Button confirmChoiceButton) {
    super(_bindingComponent, _root, _localFieldCount);
    this.assistantDescr = assistantDescr;
    this.assistantDescrCard = assistantDescrCard;
    this.assistantTitle = assistantTitle;
    this.choiceAutoButton = choiceAutoButton;
    this.choiceNAButton = choiceNAButton;
    this.choiceRAButton = choiceRAButton;
    this.confirmChoiceButton = confirmChoiceButton;
  }

  @NonNull
  public static FragmentAssistantChoiceBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_assistant_choice, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentAssistantChoiceBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentAssistantChoiceBinding>inflateInternal(inflater, R.layout.fragment_assistant_choice, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentAssistantChoiceBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_assistant_choice, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentAssistantChoiceBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentAssistantChoiceBinding>inflateInternal(inflater, R.layout.fragment_assistant_choice, null, false, component);
  }

  public static FragmentAssistantChoiceBinding bind(@NonNull View view) {
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
  public static FragmentAssistantChoiceBinding bind(@NonNull View view,
      @Nullable Object component) {
    return (FragmentAssistantChoiceBinding)bind(component, view, R.layout.fragment_assistant_choice);
  }
}
