package com.zm.userpicasso;

/**
 * Created by yuzhimin on 17-6-29.
 */

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import static android.provider.ContactsContract.Contacts;
import static com.zm.userpicasso.SampleContactsActivity.ContactsQuery;

class SampleContactsAdapter extends CursorAdapter {
    private final LayoutInflater inflater;

    public SampleContactsAdapter(Context context) {
        super(context, null, 0);
        inflater = LayoutInflater.from(context);
    }

    @Override public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View itemLayout = inflater.inflate(R.layout.sample_contacts_activity_item, viewGroup, false);

        ViewHolder holder = new ViewHolder();
        holder.text1 = (TextView) itemLayout.findViewById(android.R.id.text1);
        holder.icon = (QuickContactBadge) itemLayout.findViewById(android.R.id.icon);

        itemLayout.setTag(holder);

        return itemLayout;
    }

    @Override public void bindView(View view, Context context, Cursor cursor) {
        Uri contactUri = Contacts.getLookupUri(cursor.getLong(ContactsQuery.ID),
                cursor.getString(ContactsQuery.LOOKUP_KEY));

        ViewHolder holder = (ViewHolder) view.getTag();
        holder.text1.setText(cursor.getString(ContactsQuery.DISPLAY_NAME));
        holder.icon.assignContactUri(contactUri);

        Picasso.with(context)
                .load(contactUri)
                .placeholder(R.drawable.contact_picture_placeholder)
                .tag(context)
                .into(holder.icon);
    }

    @Override public int getCount() {
        return getCursor() == null ? 0 : super.getCount();
    }

    private static class ViewHolder {
        TextView text1;
        QuickContactBadge icon;
    }
}
