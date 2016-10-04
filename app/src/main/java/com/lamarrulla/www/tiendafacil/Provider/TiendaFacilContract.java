package com.lamarrulla.www.tiendafacil.provider;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

import com.lamarrulla.www.tiendafacil.provider.TiendaFacilDatabase.Tables;

/**
 * Created by Qualtop on 02/09/2016.
 */
public final class TiendaFacilContract {
    public static final String CONTENT_AUTHORITY = "com.lamarrulla.www.tiendafacil.provider";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    static final String PATH_USER = Tables.USER;
    static final String PATH_ARTICLE = Tables.ARTICLE;

    private TiendaFacilContract(){}

    interface UserColumns extends BaseColumns{
        public static final String MEMBER_NUMBER = "member_number";

        /** The Constant NAME. */
        public static final String NAME = "name";

        /** The Constant AGE. */
        public static final String AGE = "age";

        public static final String USER = "user";

        /** The Constant GENDER_ID. */
        public static final String GENDER_ID = "gender_id";

        /** The Constant GENDER. */
        public static final String GENDER = "gender";

        /** The Constant HEIGHT. */
        public static final String HEIGHT = "height";

        /** The Constant WEIGHT. */
        public static final String WEIGHT = "weight";

        /** The Constant REGISTRATION_DATE. */
        public static final String REGISTRATION_DATE = "registration_date";

        /** The Constant EMAIL. */
        public static final String EMAIL = "email";

        /** The Constant BIRTH_DATE. */
        public static final String BIRTH_DATE = "dob";

        /** The Constant MEMBER_TYPE. */
        public static final String MEMBER_TYPE = "member_type";

        public static final String USER_FB_ID = "user_fb_id";

        public static final String USER_FB_FIRST_NAME = "user_fb_first_name";

        public static final String USER_FB_LAST_NAME = "user_fb_last_name";

        public static final String USER_FB_BIRTHDAY = "user_fb_birthday";

        public static final String USER_FB_EMAIL = "user_fb_email";

        public static final String USER_FB_GENDER = "user_fb_gender";

        public static final String USER_FB_LOCALE = "user_fb_locale";

        public static final String LATITUD = "latitud";

        public static final String LONGITUD = "longitud";

    }

    interface ArticleColumns extends BaseColumns{
        public static final String ARTICLE_ID = "article_id";
        public static final String ARTICLE_NAME = "article_name";
        public static final String ARTICLE_DESC = "article_desc";
        public static final String ARTICLE_PRECIO = "article_precio";
        public static final String ARTICLE_COSTO = "article_costo";
        public static final String ARTICLE_FOTO = "article_foto";
        public static final String ARTICLE_STOCK = "article_stock";
    }

    public static abstract class user implements UserColumns{
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_USER).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vdn." + CONTENT_AUTHORITY + "." + Tables.USER;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vdn." + CONTENT_AUTHORITY + "." + Tables.USER;
        public static final String DEFAULT_SORT = UserColumns._ID + " COLLATE NOCASE ASC";

        public static Uri buildUserUri(String UserId){
            return CONTENT_URI.buildUpon().appendPath(_ID).build();
        }

        public static String getUserId(Uri uri){
            return uri.getLastPathSegment();
        }

    }

    public static abstract class article implements ArticleColumns{
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_ARTICLE).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/vdn." + CONTENT_AUTHORITY + "." + Tables.ARTICLE;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vdn." + CONTENT_AUTHORITY + "." + Tables.ARTICLE;
        public static final String DEFAULT_SORT = ArticleColumns._ID + "COLLATE NOCASE ASC";

        public static Uri buildArticleUri(String ArticleId){
            return CONTENT_URI.buildUpon().appendPath(_ID).build();
        }

        public static String getArticleId(Uri uri){
            return uri.getLastPathSegment();
        }

    }

}
