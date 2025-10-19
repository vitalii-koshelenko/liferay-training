package com.solteq.liferay.training.common.liferay.constants;

import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.Locale;

public final class LocaleConstants {

    private LocaleConstants() {
    }

    public static final String DEFAULT_LANGUAGE_ID = "en_US";
    public static final Locale DEFAULT_LOCALE = LocaleUtil.fromLanguageId(DEFAULT_LANGUAGE_ID);

}