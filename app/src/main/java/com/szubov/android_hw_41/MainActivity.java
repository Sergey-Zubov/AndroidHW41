package com.szubov.android_hw_41;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mEditTextSumForPayment;
    private EditText mEditTextInfoAboutPayment;
    private CheckBox mCheckBoxPaymentFromBankCard;
    private CheckBox mCheckBoxPaymentByMobilePhone;
    private CheckBox mCheckBoxPaymentCashAtAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextSumForPayment = findViewById(R.id.editTextSumForPayment);
        mEditTextInfoAboutPayment = findViewById(R.id.editTextInfoAboutPayment);
        mCheckBoxPaymentFromBankCard = findViewById(R.id.checkBoxPaymentFromBankCard);
        mCheckBoxPaymentByMobilePhone = findViewById(R.id.checkBoxPaymentByMobilePhone);
        mCheckBoxPaymentCashAtAddress = findViewById(R.id.checkBoxPaymentCashAtAddress);

        mCheckBoxPaymentFromBankCard.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mEditTextInfoAboutPayment.setText(null);
                mCheckBoxPaymentByMobilePhone.setChecked(false);
                mCheckBoxPaymentCashAtAddress.setChecked(false);
                mCheckBoxPaymentFromBankCard.setChecked(isChecked);
                mEditTextInfoAboutPayment.setInputType(InputType.TYPE_CLASS_NUMBER);
                mEditTextInfoAboutPayment.setHint(R.string.hint_info_about_payment_card_number);
            }
        });

        mCheckBoxPaymentByMobilePhone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mEditTextInfoAboutPayment.setText(null);
                mCheckBoxPaymentFromBankCard.setChecked(false);
                mCheckBoxPaymentCashAtAddress.setChecked(false);
                mCheckBoxPaymentByMobilePhone.setChecked(isChecked);
                mEditTextInfoAboutPayment.setInputType(InputType.TYPE_CLASS_PHONE);
                mEditTextInfoAboutPayment.setHint(R.string.hint_info_about_payment_mobile_number);
            }
        });

        mCheckBoxPaymentCashAtAddress.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mEditTextInfoAboutPayment.setText(null);
                mCheckBoxPaymentFromBankCard.setChecked(false);
                mCheckBoxPaymentByMobilePhone.setChecked(false);
                mCheckBoxPaymentCashAtAddress.setChecked(isChecked);
                mEditTextInfoAboutPayment.setInputType(InputType.TYPE_CLASS_TEXT);
                mEditTextInfoAboutPayment.setHint(R.string.hint_info_about_payment_cash_at_address);
            }
        });
    }

    public void btnPaymentOkOnClick(View view) {

        while (true) {
            if (mEditTextSumForPayment.length() < 1 || mEditTextInfoAboutPayment.length() <1) {
                Toast.makeText(this, getText(R.string.field_is_empty).toString(),
                        Toast.LENGTH_LONG).show();
                break;
            } else if (mEditTextInfoAboutPayment.length() >= 100) {
                Toast.makeText(this,
                        getText(R.string.field_info_about_payment_contains_too_big_value).toString(),
                        Toast.LENGTH_LONG).show();
                break;
            } else if (mEditTextSumForPayment.length() >= 15) {
                Toast.makeText(this, getText(R.string.field_sum_contains_too_big_value).toString(),
                        Toast.LENGTH_LONG).show();
                break;
            } else {
                try {
                    if (mCheckBoxPaymentFromBankCard.isChecked()) {
                        Toast.makeText(this, getText(R.string.hint_sum_for_payment).toString()
                                + ": " + mEditTextSumForPayment.getText().toString()
                                + "\n" + getText(R.string.payment_method).toString() + ": "
                                + getText(R.string.check_box_card_payment).toString()
                                + "\n" + getText(R.string.hint_info_about_payment_card_number).toString() + ": "
                                + mEditTextInfoAboutPayment.getText().toString(), Toast.LENGTH_LONG).show();
                        mEditTextSumForPayment.setText(null);
                        mEditTextInfoAboutPayment.setText(null);
                        mCheckBoxPaymentFromBankCard.setChecked(true);
                    } else if (mCheckBoxPaymentByMobilePhone.isChecked()) {
                        Toast.makeText(this, getText(R.string.hint_sum_for_payment).toString()
                                + ": " + mEditTextSumForPayment.getText().toString()
                                + "\n" + getText(R.string.payment_method).toString() + ": "
                                + getText(R.string.check_box_mobile_phone_payment).toString()
                                + "\n" + getText(R.string.hint_info_about_payment_mobile_number).toString() + ": "
                                + mEditTextInfoAboutPayment.getText().toString(), Toast.LENGTH_LONG).show();
                        mEditTextSumForPayment.setText(null);
                        mEditTextInfoAboutPayment.setText(null);
                        mCheckBoxPaymentFromBankCard.setChecked(true);
                    } else {
                        Toast.makeText(this, getText(R.string.hint_sum_for_payment).toString()
                                + ": " + mEditTextSumForPayment.getText().toString()
                                + "\n" + getText(R.string.payment_method).toString() + ": "
                                + getText(R.string.check_box_payment_cash_at_address).toString()
                                + "\n" + getText(R.string.hint_info_about_payment_cash_at_address).toString() + ": "
                                + mEditTextInfoAboutPayment.getText().toString(), Toast.LENGTH_LONG).show();
                        mEditTextSumForPayment.setText(null);
                        mEditTextInfoAboutPayment.setText(null);
                        mCheckBoxPaymentFromBankCard.setChecked(true);
                    }
                } catch (NumberFormatException ex) {
                    Toast.makeText(this, R.string.exception_btn_payment_ok,
                            Toast.LENGTH_LONG).show();
                }
            }
            break;
        }
    }
}