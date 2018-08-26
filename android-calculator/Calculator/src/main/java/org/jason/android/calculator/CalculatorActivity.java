package org.jason.android.calculator;

/**
 * Created by jason on 18/03/16.
 */

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CalculatorActivity extends Activity implements View.OnClickListener {
    private static final Logger logger  = Logger.getLogger(CalculatorActivity.class.toString());
    private Button one, two, three, four, five, six, zero, add, sub, mul, delete, equal;
    private EditText disp;
    private OperationType optr = OperationType.Default;
    private CalculatorModulo7Proxy calculator;
    private int first = 0;
    private int second = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        this.one = (Button) findViewById(R.id.one);
        this.two = (Button) findViewById(R.id.two);
        this.three = (Button) findViewById(R.id.three);
        this.four = (Button) findViewById(R.id.four);
        this.five = (Button) findViewById(R.id.five);
        this.six = (Button) findViewById(R.id.six);
        this.zero = (Button) findViewById(R.id.zero);
        this.add = (Button) findViewById(R.id.add);
        this.sub = (Button) findViewById(R.id.sub);
        this.mul = (Button) findViewById(R.id.mul);
        this.delete = (Button) findViewById(R.id.delete);
        this.equal = (Button) findViewById(R.id.equal);
        this.disp = (EditText) findViewById(R.id.display);
        try {
            this.one.setOnClickListener(this);
            this.two.setOnClickListener(this);
            this.three.setOnClickListener(this);
            this.four.setOnClickListener(this);
            this.five.setOnClickListener(this);
            this.six.setOnClickListener(this);
            this.zero.setOnClickListener(this);
            this.delete.setOnClickListener(this);
            this.add.setOnClickListener(this);
            this.sub.setOnClickListener(this);
            this.mul.setOnClickListener(this);
            this.equal.setOnClickListener(this);
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }

        this.calculator = new CalculatorModulo7Proxy();
    }

    @Override
    public void onClick(View arg0) {
        Editable str = this.disp.getText();
        switch (arg0.getId()) {
            case R.id.zero:
                str = str.append(zero.getText());
                this.disp.setText(str);
                break;
            case R.id.one:
                str = str.append(one.getText());
                this.disp.setText(str);
                break;
            case R.id.two:
                str = str.append(two.getText());
                this.disp.setText(str);
                break;
            case R.id.three:
                str = str.append(three.getText());
                this.disp.setText(str);
                break;
            case R.id.four:
                str = str.append(four.getText());
                this.disp.setText(str);
                break;
            case R.id.five:
                str = str.append(five.getText());
                this.disp.setText(str);
                break;
            case R.id.six:
                str = str.append(six.getText());
                this.disp.setText(str);
                break;

            case R.id.delete:
                first = 0;
                second = 0;
                this.optr = OperationType.Default;
                this.disp.setText("");
                break;
            case R.id.add:
                if (this.disp.getText().toString().trim().equals("")) {
                    this.first = 0;
                } else {
                    this.first = Integer.valueOf(this.disp.getText().toString());
                }

                this.disp.setText("");
                this.optr = OperationType.Add;
                break;
            case R.id.sub:
                if (this.disp.getText().toString().trim().equals("")) {
                    this.first = 0;
                } else {
                    this.first = Integer.valueOf(this.disp.getText().toString());
                }

                this.optr = OperationType.Sub;
                this.disp.setText("");
                break;
            case R.id.mul:
                if (this.disp.getText().toString().trim().equals("")) {
                    this.first = 0;
                } else {
                    this.first = Integer.valueOf(this.disp.getText().toString());
                }

                this.optr = OperationType.Mul;
                this.disp.setText("");
                break;

            case R.id.equal:
                if(this.disp.getText().toString().trim().equals("")){
                    this.second = 0;
                } else {
                    this.second = Integer.valueOf(this.disp.getText().toString());
                }

                if(this.optr != null) {
                 this.disp.setText(String.valueOf(this.calculator.operate(this.optr, this.first, this.second)));
                } else {
                    this.disp.setText("0");
                }

                this.optr = OperationType.Default;
                this.first = 0;
                this.second = 0;

                break;
        }
    }
}

enum OperationType{
    Add,Sub,Mul,Default
}