// Generated by view binder compiler. Do not edit!
package com.example.lifesimulator.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.lifesimulator.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentInfosPersonneBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final LinearLayout infoContainer;

  @NonNull
  public final TextView infosAge;

  @NonNull
  public final Button infosBoutonPartir;

  @NonNull
  public final Button infosBoutonSauvegarder;

  @NonNull
  public final TextView infosFamille;

  @NonNull
  public final ImageView infosFond;

  @NonNull
  public final TextView infosGenre;

  @NonNull
  public final ImageView infosImage;

  @NonNull
  public final ImageView infosImageAleatoire;

  @NonNull
  public final ImageView infosImagePartir;

  @NonNull
  public final EditText infosNom;

  private FragmentInfosPersonneBinding(@NonNull FrameLayout rootView,
      @NonNull LinearLayout infoContainer, @NonNull TextView infosAge,
      @NonNull Button infosBoutonPartir, @NonNull Button infosBoutonSauvegarder,
      @NonNull TextView infosFamille, @NonNull ImageView infosFond, @NonNull TextView infosGenre,
      @NonNull ImageView infosImage, @NonNull ImageView infosImageAleatoire,
      @NonNull ImageView infosImagePartir, @NonNull EditText infosNom) {
    this.rootView = rootView;
    this.infoContainer = infoContainer;
    this.infosAge = infosAge;
    this.infosBoutonPartir = infosBoutonPartir;
    this.infosBoutonSauvegarder = infosBoutonSauvegarder;
    this.infosFamille = infosFamille;
    this.infosFond = infosFond;
    this.infosGenre = infosGenre;
    this.infosImage = infosImage;
    this.infosImageAleatoire = infosImageAleatoire;
    this.infosImagePartir = infosImagePartir;
    this.infosNom = infosNom;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentInfosPersonneBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentInfosPersonneBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_infos_personne, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentInfosPersonneBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.infoContainer;
      LinearLayout infoContainer = ViewBindings.findChildViewById(rootView, id);
      if (infoContainer == null) {
        break missingId;
      }

      id = R.id.infosAge;
      TextView infosAge = ViewBindings.findChildViewById(rootView, id);
      if (infosAge == null) {
        break missingId;
      }

      id = R.id.infosBoutonPartir;
      Button infosBoutonPartir = ViewBindings.findChildViewById(rootView, id);
      if (infosBoutonPartir == null) {
        break missingId;
      }

      id = R.id.infosBoutonSauvegarder;
      Button infosBoutonSauvegarder = ViewBindings.findChildViewById(rootView, id);
      if (infosBoutonSauvegarder == null) {
        break missingId;
      }

      id = R.id.infosFamille;
      TextView infosFamille = ViewBindings.findChildViewById(rootView, id);
      if (infosFamille == null) {
        break missingId;
      }

      id = R.id.infosFond;
      ImageView infosFond = ViewBindings.findChildViewById(rootView, id);
      if (infosFond == null) {
        break missingId;
      }

      id = R.id.infosGenre;
      TextView infosGenre = ViewBindings.findChildViewById(rootView, id);
      if (infosGenre == null) {
        break missingId;
      }

      id = R.id.infosImage;
      ImageView infosImage = ViewBindings.findChildViewById(rootView, id);
      if (infosImage == null) {
        break missingId;
      }

      id = R.id.infosImageAleatoire;
      ImageView infosImageAleatoire = ViewBindings.findChildViewById(rootView, id);
      if (infosImageAleatoire == null) {
        break missingId;
      }

      id = R.id.infosImagePartir;
      ImageView infosImagePartir = ViewBindings.findChildViewById(rootView, id);
      if (infosImagePartir == null) {
        break missingId;
      }

      id = R.id.infosNom;
      EditText infosNom = ViewBindings.findChildViewById(rootView, id);
      if (infosNom == null) {
        break missingId;
      }

      return new FragmentInfosPersonneBinding((FrameLayout) rootView, infoContainer, infosAge,
          infosBoutonPartir, infosBoutonSauvegarder, infosFamille, infosFond, infosGenre,
          infosImage, infosImageAleatoire, infosImagePartir, infosNom);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}