package com.example.supermarket;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.TextView;

public class LoadingDialogBar {
    private Context context;
    private Dialog dialog;
    public LoadingDialogBar(Context context){
        this.context = context;
    }
    public void ShowDialog(String title){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.create();
        dialog.show();

        TextView textView = dialog.findViewById(R.id.dialogTitle);
        textView.setText(title);

    }
    public void HideDialog(){
        dialog.dismiss();
    }
}
