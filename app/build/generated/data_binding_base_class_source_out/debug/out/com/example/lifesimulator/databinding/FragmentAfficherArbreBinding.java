// Generated by view binder compiler. Do not edit!
package com.example.lifesimulator.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.lifesimulator.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentAfficherArbreBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final LinearLayout affArbreRacine;

  private FragmentAfficherArbreBinding(@NonNull FrameLayout rootView,
      @NonNull LinearLayout affArbreRacine) {
    this.rootView = rootView;
    this.affArbreRacine = affArbreRacine;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentAfficherArbreBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentAfficherArbreBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_afficher_arbre, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentAfficherArbreBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.aff_arbre_racine;
      LinearLayout affArbreRacine = ViewBindings.findChildViewById(rootView, id);
      if (affArbreRacine == null) {
        break missingId;
      }

      return new FragmentAfficherArbreBinding((FrameLayout) rootView, affArbreRacine);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}