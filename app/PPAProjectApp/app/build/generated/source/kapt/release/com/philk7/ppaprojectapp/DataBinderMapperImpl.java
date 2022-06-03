package com.philk7.ppaprojectapp;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.philk7.ppaprojectapp.databinding.ActivityLocationIndicationBindingImpl;
import com.philk7.ppaprojectapp.databinding.ActivityMidtermSurveyBindingImpl;
import com.philk7.ppaprojectapp.databinding.FragmentAssistantChoiceBindingImpl;
import com.philk7.ppaprojectapp.databinding.FragmentDailyDecisionsBindingImpl;
import com.philk7.ppaprojectapp.databinding.FragmentDecisionMakingBindingImpl;
import com.philk7.ppaprojectapp.databinding.FragmentDecisionPanelBindingImpl;
import com.philk7.ppaprojectapp.databinding.FragmentDevModeBindingImpl;
import com.philk7.ppaprojectapp.databinding.FragmentHomeBindingImpl;
import com.philk7.ppaprojectapp.databinding.FragmentIntroFinalBindingImpl;
import com.philk7.ppaprojectapp.databinding.FragmentIotExplainBindingImpl;
import com.philk7.ppaprojectapp.databinding.FragmentIotQuestionsBindingImpl;
import com.philk7.ppaprojectapp.databinding.FragmentLikertQuestionsBindingImpl;
import com.philk7.ppaprojectapp.databinding.FragmentMovementTestingBindingImpl;
import com.philk7.ppaprojectapp.databinding.FragmentPersonQuestionsBindingImpl;
import com.philk7.ppaprojectapp.databinding.FragmentPersonaAdjustmentBindingImpl;
import com.philk7.ppaprojectapp.databinding.FragmentPersonaQuestionsBindingImpl;
import com.philk7.ppaprojectapp.databinding.FragmentPersonaResultBindingImpl;
import com.philk7.ppaprojectapp.databinding.FragmentPersonaShowBindingImpl;
import com.philk7.ppaprojectapp.databinding.FragmentSharedPrefViewerBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYLOCATIONINDICATION = 1;

  private static final int LAYOUT_ACTIVITYMIDTERMSURVEY = 2;

  private static final int LAYOUT_FRAGMENTASSISTANTCHOICE = 3;

  private static final int LAYOUT_FRAGMENTDAILYDECISIONS = 4;

  private static final int LAYOUT_FRAGMENTDECISIONMAKING = 5;

  private static final int LAYOUT_FRAGMENTDECISIONPANEL = 6;

  private static final int LAYOUT_FRAGMENTDEVMODE = 7;

  private static final int LAYOUT_FRAGMENTHOME = 8;

  private static final int LAYOUT_FRAGMENTINTROFINAL = 9;

  private static final int LAYOUT_FRAGMENTIOTEXPLAIN = 10;

  private static final int LAYOUT_FRAGMENTIOTQUESTIONS = 11;

  private static final int LAYOUT_FRAGMENTLIKERTQUESTIONS = 12;

  private static final int LAYOUT_FRAGMENTMOVEMENTTESTING = 13;

  private static final int LAYOUT_FRAGMENTPERSONQUESTIONS = 14;

  private static final int LAYOUT_FRAGMENTPERSONAADJUSTMENT = 15;

  private static final int LAYOUT_FRAGMENTPERSONAQUESTIONS = 16;

  private static final int LAYOUT_FRAGMENTPERSONARESULT = 17;

  private static final int LAYOUT_FRAGMENTPERSONASHOW = 18;

  private static final int LAYOUT_FRAGMENTSHAREDPREFVIEWER = 19;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(19);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.philk7.ppaprojectapp.R.layout.activity_location_indication, LAYOUT_ACTIVITYLOCATIONINDICATION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.philk7.ppaprojectapp.R.layout.activity_midterm_survey, LAYOUT_ACTIVITYMIDTERMSURVEY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.philk7.ppaprojectapp.R.layout.fragment_assistant_choice, LAYOUT_FRAGMENTASSISTANTCHOICE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.philk7.ppaprojectapp.R.layout.fragment_daily_decisions, LAYOUT_FRAGMENTDAILYDECISIONS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.philk7.ppaprojectapp.R.layout.fragment_decision_making, LAYOUT_FRAGMENTDECISIONMAKING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.philk7.ppaprojectapp.R.layout.fragment_decision_panel, LAYOUT_FRAGMENTDECISIONPANEL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.philk7.ppaprojectapp.R.layout.fragment_dev_mode, LAYOUT_FRAGMENTDEVMODE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.philk7.ppaprojectapp.R.layout.fragment_home, LAYOUT_FRAGMENTHOME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.philk7.ppaprojectapp.R.layout.fragment_intro_final, LAYOUT_FRAGMENTINTROFINAL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.philk7.ppaprojectapp.R.layout.fragment_iot_explain, LAYOUT_FRAGMENTIOTEXPLAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.philk7.ppaprojectapp.R.layout.fragment_iot_questions, LAYOUT_FRAGMENTIOTQUESTIONS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.philk7.ppaprojectapp.R.layout.fragment_likert_questions, LAYOUT_FRAGMENTLIKERTQUESTIONS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.philk7.ppaprojectapp.R.layout.fragment_movement_testing, LAYOUT_FRAGMENTMOVEMENTTESTING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.philk7.ppaprojectapp.R.layout.fragment_person_questions, LAYOUT_FRAGMENTPERSONQUESTIONS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.philk7.ppaprojectapp.R.layout.fragment_persona_adjustment, LAYOUT_FRAGMENTPERSONAADJUSTMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.philk7.ppaprojectapp.R.layout.fragment_persona_questions, LAYOUT_FRAGMENTPERSONAQUESTIONS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.philk7.ppaprojectapp.R.layout.fragment_persona_result, LAYOUT_FRAGMENTPERSONARESULT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.philk7.ppaprojectapp.R.layout.fragment_persona_show, LAYOUT_FRAGMENTPERSONASHOW);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.philk7.ppaprojectapp.R.layout.fragment_shared_pref_viewer, LAYOUT_FRAGMENTSHAREDPREFVIEWER);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYLOCATIONINDICATION: {
          if ("layout/activity_location_indication_0".equals(tag)) {
            return new ActivityLocationIndicationBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_location_indication is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYMIDTERMSURVEY: {
          if ("layout/activity_midterm_survey_0".equals(tag)) {
            return new ActivityMidtermSurveyBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_midterm_survey is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTASSISTANTCHOICE: {
          if ("layout/fragment_assistant_choice_0".equals(tag)) {
            return new FragmentAssistantChoiceBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_assistant_choice is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTDAILYDECISIONS: {
          if ("layout/fragment_daily_decisions_0".equals(tag)) {
            return new FragmentDailyDecisionsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_daily_decisions is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTDECISIONMAKING: {
          if ("layout/fragment_decision_making_0".equals(tag)) {
            return new FragmentDecisionMakingBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_decision_making is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTDECISIONPANEL: {
          if ("layout/fragment_decision_panel_0".equals(tag)) {
            return new FragmentDecisionPanelBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_decision_panel is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTDEVMODE: {
          if ("layout/fragment_dev_mode_0".equals(tag)) {
            return new FragmentDevModeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_dev_mode is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTHOME: {
          if ("layout/fragment_home_0".equals(tag)) {
            return new FragmentHomeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_home is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTINTROFINAL: {
          if ("layout/fragment_intro_final_0".equals(tag)) {
            return new FragmentIntroFinalBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_intro_final is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTIOTEXPLAIN: {
          if ("layout/fragment_iot_explain_0".equals(tag)) {
            return new FragmentIotExplainBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_iot_explain is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTIOTQUESTIONS: {
          if ("layout/fragment_iot_questions_0".equals(tag)) {
            return new FragmentIotQuestionsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_iot_questions is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTLIKERTQUESTIONS: {
          if ("layout/fragment_likert_questions_0".equals(tag)) {
            return new FragmentLikertQuestionsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_likert_questions is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTMOVEMENTTESTING: {
          if ("layout/fragment_movement_testing_0".equals(tag)) {
            return new FragmentMovementTestingBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_movement_testing is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPERSONQUESTIONS: {
          if ("layout/fragment_person_questions_0".equals(tag)) {
            return new FragmentPersonQuestionsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_person_questions is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPERSONAADJUSTMENT: {
          if ("layout/fragment_persona_adjustment_0".equals(tag)) {
            return new FragmentPersonaAdjustmentBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_persona_adjustment is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPERSONAQUESTIONS: {
          if ("layout/fragment_persona_questions_0".equals(tag)) {
            return new FragmentPersonaQuestionsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_persona_questions is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPERSONARESULT: {
          if ("layout/fragment_persona_result_0".equals(tag)) {
            return new FragmentPersonaResultBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_persona_result is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPERSONASHOW: {
          if ("layout/fragment_persona_show_0".equals(tag)) {
            return new FragmentPersonaShowBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_persona_show is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSHAREDPREFVIEWER: {
          if ("layout/fragment_shared_pref_viewer_0".equals(tag)) {
            return new FragmentSharedPrefViewerBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_shared_pref_viewer is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(2);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "question");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(19);

    static {
      sKeys.put("layout/activity_location_indication_0", com.philk7.ppaprojectapp.R.layout.activity_location_indication);
      sKeys.put("layout/activity_midterm_survey_0", com.philk7.ppaprojectapp.R.layout.activity_midterm_survey);
      sKeys.put("layout/fragment_assistant_choice_0", com.philk7.ppaprojectapp.R.layout.fragment_assistant_choice);
      sKeys.put("layout/fragment_daily_decisions_0", com.philk7.ppaprojectapp.R.layout.fragment_daily_decisions);
      sKeys.put("layout/fragment_decision_making_0", com.philk7.ppaprojectapp.R.layout.fragment_decision_making);
      sKeys.put("layout/fragment_decision_panel_0", com.philk7.ppaprojectapp.R.layout.fragment_decision_panel);
      sKeys.put("layout/fragment_dev_mode_0", com.philk7.ppaprojectapp.R.layout.fragment_dev_mode);
      sKeys.put("layout/fragment_home_0", com.philk7.ppaprojectapp.R.layout.fragment_home);
      sKeys.put("layout/fragment_intro_final_0", com.philk7.ppaprojectapp.R.layout.fragment_intro_final);
      sKeys.put("layout/fragment_iot_explain_0", com.philk7.ppaprojectapp.R.layout.fragment_iot_explain);
      sKeys.put("layout/fragment_iot_questions_0", com.philk7.ppaprojectapp.R.layout.fragment_iot_questions);
      sKeys.put("layout/fragment_likert_questions_0", com.philk7.ppaprojectapp.R.layout.fragment_likert_questions);
      sKeys.put("layout/fragment_movement_testing_0", com.philk7.ppaprojectapp.R.layout.fragment_movement_testing);
      sKeys.put("layout/fragment_person_questions_0", com.philk7.ppaprojectapp.R.layout.fragment_person_questions);
      sKeys.put("layout/fragment_persona_adjustment_0", com.philk7.ppaprojectapp.R.layout.fragment_persona_adjustment);
      sKeys.put("layout/fragment_persona_questions_0", com.philk7.ppaprojectapp.R.layout.fragment_persona_questions);
      sKeys.put("layout/fragment_persona_result_0", com.philk7.ppaprojectapp.R.layout.fragment_persona_result);
      sKeys.put("layout/fragment_persona_show_0", com.philk7.ppaprojectapp.R.layout.fragment_persona_show);
      sKeys.put("layout/fragment_shared_pref_viewer_0", com.philk7.ppaprojectapp.R.layout.fragment_shared_pref_viewer);
    }
  }
}
