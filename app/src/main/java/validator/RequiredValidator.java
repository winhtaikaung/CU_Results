package validator;

/**
 * Created by winhtaikaung on 11/14/15.
 */

import android.text.TextUtils;

import com.rengwuxian.materialedittext.validation.METValidator;

/**
 * Created by vijay.rawat01 on 7/21/15.
 */
public class RequiredValidator extends METValidator {

    public RequiredValidator(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public boolean isValid(CharSequence charSequence, boolean isEmpty) {
        return !TextUtils.isEmpty(charSequence);
    }
}


