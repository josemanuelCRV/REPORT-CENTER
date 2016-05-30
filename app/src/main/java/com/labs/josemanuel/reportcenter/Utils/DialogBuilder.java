package com.labs.josemanuel.reportcenter.Utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by Usuario on 30/05/2016.
 import android.app.AlertDialog;
 import android.app.Dialog;
 import android.app.DialogFragment;
 import android.content.Context;
 import android.os.Bundle;

 /**
 * Created by 21443251 on 04/12/2015.
 */
public class DialogBuilder extends AlertDialog {

    public DialogBuilder(Context context) {
        super(context);
    }


    public void alertUserAboutError() {
        DialogBuilder dialog = new DialogBuilder(getContext());
        dialog.setTitle("No connectivity");
        dialog.setMessage("Please make sure you have an internet connection");
        dialog.show();

    }
}
