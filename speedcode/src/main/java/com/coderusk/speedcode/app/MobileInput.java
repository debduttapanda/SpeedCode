package com.coderusk.speedcode.app;

import android.content.Context;
import android.graphics.Rect;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatEditText;

import com.coderusk.speedcode.R;

public class MobileInput extends AppCompatEditText {
    public static String prefix = "+91 ";
    private static final int MAX_LENGTH = 14;
    private CurrencyTextWatcher currencyTextWatcher = new CurrencyTextWatcher(this, prefix);

    public MobileInput(Context context) {
        this(context, null);
    }

    public MobileInput(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.editTextStyle);
    }

    public MobileInput(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setInputType(InputType.TYPE_CLASS_NUMBER);
        this.setHint(prefix);
        this.setFilters(new InputFilter[] { new InputFilter.LengthFilter(MAX_LENGTH) });
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused) {
            this.addTextChangedListener(currencyTextWatcher);
        } else {
            this.removeTextChangedListener(currencyTextWatcher);
        }
        handleCaseCurrencyEmpty(focused);
    }

    private void handleCaseCurrencyEmpty(boolean focused) {
        if (focused) {
            if (getText().toString().isEmpty()) {
                setText(prefix);
            }
        } else {
            if (getText().toString().equals(prefix)) {
                setText("");
            }
        }
    }

    private static class CurrencyTextWatcher implements TextWatcher {
        private final EditText editText;
        private String previousCleanString;
        private String prefix;

        CurrencyTextWatcher(EditText editText, String prefix) {
            this.editText = editText;
            this.prefix = prefix;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // do nothing
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // do nothing
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String str = editable.toString();
            if (str.length() < prefix.length()) {
                editText.setText(prefix);
                editText.setSelection(prefix.length());
                return;
            }
            if (str.equals(prefix)) {
                return;
            }
            String cleanString = str.replace(prefix, "");
            if (cleanString.equals(previousCleanString) || cleanString.isEmpty()) {
                return;
            }
            previousCleanString = cleanString;

            String formattedString = prefix + cleanString;
            editText.removeTextChangedListener(this); // Remove listener
            editText.setText(formattedString);
            handleSelection();
            editText.addTextChangedListener(this); // Add back the listener
        }

        private void handleSelection() {
            if (editText.getText().length() <= MAX_LENGTH) {
                editText.setSelection(editText.getText().length());
            } else {
                editText.setSelection(MAX_LENGTH);
            }
        }
    }

    public int getValue()
    {
        String val = getText().toString().replace(prefix,"");
        int ival = 0;
        try {
            ival = Integer.parseInt(val);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return ival;
    }
}
