package com.razanpardazesh.mtglibrary.tools;

import android.text.Editable;
import android.text.TextUtils;

/**
 * Created by MTorabi on 11/6/2016.
 */

public class ValidateHelper {

    public static Boolean validateNecessaryString(Editable input) {


        if (input == null)
            return false;

        if (TextUtils.isEmpty(input.toString()))
            return false;

        if (TextUtils.isEmpty(input.toString().trim()))
            return false;


        return true;
    }

    public static Boolean validateIranCellphone(Editable input) {


        if (input == null)
            return false;

        if (TextUtils.isEmpty(input.toString()))
            return false;

        if (TextUtils.isEmpty(input.toString().trim()))
            return false;

        if (input.length() == 13)
        {
            if (!input.toString().startsWith("+989"))
                return false;

            String tel = input.toString().substring(1);
            if (!TextUtils.isDigitsOnly(tel))
                return false;
        }

        if (input.length() == 12)
        {
            if (!input.toString().startsWith("989"))
                return false;

            if (!TextUtils.isDigitsOnly(input.toString()))
                return false;

            return true;
        }

        if (input.length() == 11)
        {
            if (!input.toString().startsWith("09"))
                return false;

            if (!TextUtils.isDigitsOnly(input.toString()))
                return false;

            return true;
        }

        if (input.length() == 10)
        {
            if (!input.toString().startsWith("9"))
                return false;

            if (!TextUtils.isDigitsOnly(input.toString()))
                return false;

            return true;
        }

        return false;
    }



}
