package com.example.myapplication_calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
TextView t1,t2;
MaterialButton buttonc,buttonbracrig,buttonbraclef;
MaterialButton bdiv,bmul,bsum,bsub,bequals;
MaterialButton b0,b1,b2,b3,b4,b5,b6,b7,b8,b9;
MaterialButton butac,butdot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        t1=findViewById(R.id.result_tv1);
        t2=findViewById(R.id.result_tv2);
        assignid(buttonc,R.id.clear);
        assignid(buttonbracrig,R.id.bracrig);
        assignid(buttonbraclef,R.id.braclef);
        assignid(bdiv,R.id.divide);
        assignid(bmul,R.id.multiply);
        assignid(bsum,R.id.sum);
        assignid(bsub,R.id.subtract);
        assignid(bequals,R.id.equals);
        assignid(b0,R.id.zero);
        assignid(b1,R.id.one);
        assignid(b2,R.id.two);
        assignid(b3,R.id.three);
        assignid(b4,R.id.four);
        assignid(b5,R.id.five);
        assignid(b6,R.id.six);
        assignid(b7,R.id.seven);
        assignid(b8,R.id.eight);
        assignid(b9,R.id.nine);
        assignid(butac,R.id.ac);
        assignid(butdot,R.id.dot);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    void assignid(MaterialButton btn,int id)
    {
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        MaterialButton button=(MaterialButton) view;
        String buttontext=button.getText().toString();
        String datatocalculate=t1.getText().toString();


        if(buttontext.equals("AC"))
        {
            t1.setText("");
            t2.setText("0");
            return;
        }
        if(buttontext.equals("="))
        {
            t1.setText(t2.getText());
            return;
        }

        if(buttontext.equals("C"))
        {
            if(!t1.getText().toString().isEmpty()){
            datatocalculate=datatocalculate.substring(0,datatocalculate.length()-1);
            }
            else
            {
                t1.setText("0");
            }

        }


        else
        {
            datatocalculate=datatocalculate+buttontext;
        }

        t1.setText(datatocalculate);
        String finalresult = Getresult(datatocalculate);
        if(!finalresult.equals("Error"))
        {
            t2.setText(finalresult);
        }

    }
    String Getresult(String data)
    {
        try
        {
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initStandardObjects();
            String finalres=context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalres.endsWith(".0"))
            {
                finalres=finalres.replace(".0","");
            }
            return finalres;
        }
        catch (Exception e)
        {
            return "Error";
        }
    }
}