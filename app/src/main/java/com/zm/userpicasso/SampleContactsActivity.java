package com.zm.userpicasso;

/**
 * Created by yuzhimin on 17-6-29.
 */

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.widget.ListView;

import static android.provider.ContactsContract.Contacts;

public class SampleContactsActivity extends PicassoSampleActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {
    private SampleContactsAdapter adapter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_contacts_activity);

        adapter = new SampleContactsAdapter(this);

        ListView lv = (ListView) findViewById(android.R.id.list);
        lv.setAdapter(adapter);
        lv.setOnScrollListener(new SampleScrollListener(this));

        getSupportLoaderManager().initLoader(ContactsQuery.QUERY_ID, null, this);
    }

    @Override public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (id == ContactsQuery.QUERY_ID) {
            return new CursorLoader(this, //
                    ContactsQuery.CONTENT_URI, //
                    ContactsQuery.PROJECTION, //
                    ContactsQuery.SELECTION, //
                    null, //
                    ContactsQuery.SORT_ORDER);
        }
        return null;
    }

    @Override public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

    interface ContactsQuery {
        int QUERY_ID = 1;

        Uri CONTENT_URI = Contacts.CONTENT_URI;

        String SELECTION = Contacts.DISPLAY_NAME_PRIMARY
                + "<>''"
                + " AND "
                + Contacts.IN_VISIBLE_GROUP
                + "=1";

        String SORT_ORDER = Contacts.SORT_KEY_PRIMARY;

        String[] PROJECTION = {
                Contacts._ID, //
                Contacts.LOOKUP_KEY, //
                Contacts.DISPLAY_NAME_PRIMARY, //
                Contacts.PHOTO_THUMBNAIL_URI, //
                SORT_ORDER
        };

        int ID = 0;
        int LOOKUP_KEY = 1;
        int DISPLAY_NAME = 2;
    }
}

