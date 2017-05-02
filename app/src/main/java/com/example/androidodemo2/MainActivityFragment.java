package com.example.androidodemo2;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import static android.support.v4.content.pm.ShortcutManagerCompat.createShortcutResultIntent;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_pin) {
            createShortcut();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void createShortcut() {
        ShortcutManager shortcutManager = getActivity().getSystemService(ShortcutManager.class);
        if (shortcutManager.isRequestPinShortcutSupported()) {
            ShortcutInfo shortcut = new ShortcutInfo.Builder(getActivity(), "demoid")
                    .setShortLabel("Demo")
                    .setLongLabel("Open the Android Docu")
                    .setIcon(Icon.createWithResource(getActivity(), R.drawable.pin))
                    .setIntent(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://developer.android.com/preview/features/pinning-shortcuts-widgets.html#shortcuts")))
                    .build();

            shortcutManager.requestPinShortcut(shortcut, null);
        } else
            Toast.makeText(getActivity(), "Pinned shortcuts are not supported!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
