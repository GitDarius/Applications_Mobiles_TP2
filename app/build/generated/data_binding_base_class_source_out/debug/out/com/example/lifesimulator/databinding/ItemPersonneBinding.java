// Generated by view binder compiler. Do not edit!
package com.example.lifesimulator.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.lifesimulator.R;
import com.google.android.material.imageview.ShapeableImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemPersonneBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView itemPersonneAge;

  @NonNull
  public final ImageView itemPersonneCoeur;

  @NonNull
  public final LinearLayout itemPersonneContainer;

  @NonNull
  public final ShapeableImageView itemPersonneFondGris;

  @NonNull
  public final ImageView itemPersonneFondTombe;

  @NonNull
  public final ImageView itemPersonneImage;

  @NonNull
  public final TextView itemPersonneNom;

  @NonNull
  public final TextView itemPersonneNomConjoint;

  @NonNull
  public final ImageView itemPersonneTombe;

  private ItemPersonneBinding(@NonNull ConstraintLayout rootView, @NonNull TextView itemPersonneAge,
      @NonNull ImageView itemPersonneCoeur, @NonNull LinearLayout itemPersonneContainer,
      @NonNull ShapeableImageView itemPersonneFondGris, @NonNull ImageView itemPersonneFondTombe,
      @NonNull ImageView itemPersonneImage, @NonNull TextView itemPersonneNom,
      @NonNull TextView itemPersonneNomConjoint, @NonNull ImageView itemPersonneTombe) {
    this.rootView = rootView;
    this.itemPersonneAge = itemPersonneAge;
    this.itemPersonneCoeur = itemPersonneCoeur;
    this.itemPersonneContainer = itemPersonneContainer;
    this.itemPersonneFondGris = itemPersonneFondGris;
    this.itemPersonneFondTombe = itemPersonneFondTombe;
    this.itemPersonneImage = itemPersonneImage;
    this.itemPersonneNom = itemPersonneNom;
    this.itemPersonneNomConjoint = itemPersonneNomConjoint;
    this.itemPersonneTombe = itemPersonneTombe;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemPersonneBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemPersonneBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_personne, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemPersonneBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.itemPersonneAge;
      TextView itemPersonneAge = ViewBindings.findChildViewById(rootView, id);
      if (itemPersonneAge == null) {
        break missingId;
      }

      id = R.id.itemPersonneCoeur;
      ImageView itemPersonneCoeur = ViewBindings.findChildViewById(rootView, id);
      if (itemPersonneCoeur == null) {
        break missingId;
      }

      id = R.id.itemPersonneContainer;
      LinearLayout itemPersonneContainer = ViewBindings.findChildViewById(rootView, id);
      if (itemPersonneContainer == null) {
        break missingId;
      }

      id = R.id.itemPersonneFondGris;
      ShapeableImageView itemPersonneFondGris = ViewBindings.findChildViewById(rootView, id);
      if (itemPersonneFondGris == null) {
        break missingId;
      }

      id = R.id.itemPersonneFondTombe;
      ImageView itemPersonneFondTombe = ViewBindings.findChildViewById(rootView, id);
      if (itemPersonneFondTombe == null) {
        break missingId;
      }

      id = R.id.itemPersonneImage;
      ImageView itemPersonneImage = ViewBindings.findChildViewById(rootView, id);
      if (itemPersonneImage == null) {
        break missingId;
      }

      id = R.id.itemPersonneNom;
      TextView itemPersonneNom = ViewBindings.findChildViewById(rootView, id);
      if (itemPersonneNom == null) {
        break missingId;
      }

      id = R.id.itemPersonneNomConjoint;
      TextView itemPersonneNomConjoint = ViewBindings.findChildViewById(rootView, id);
      if (itemPersonneNomConjoint == null) {
        break missingId;
      }

      id = R.id.itemPersonneTombe;
      ImageView itemPersonneTombe = ViewBindings.findChildViewById(rootView, id);
      if (itemPersonneTombe == null) {
        break missingId;
      }

      return new ItemPersonneBinding((ConstraintLayout) rootView, itemPersonneAge,
          itemPersonneCoeur, itemPersonneContainer, itemPersonneFondGris, itemPersonneFondTombe,
          itemPersonneImage, itemPersonneNom, itemPersonneNomConjoint, itemPersonneTombe);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
