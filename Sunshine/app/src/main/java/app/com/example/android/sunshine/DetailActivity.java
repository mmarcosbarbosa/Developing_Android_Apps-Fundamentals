package app.com.example.android.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.ShareActionProvider;
import android.widget.TextView;

public class DetailActivity extends ActionBarActivity {

    private static final String LOG_TAG = DetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private String forecast;

        public PlaceholderFragment() {
            setHasOptionsMenu(true);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

            // Associando um Intent interno com o Intent que chamou a criação da View (se houver)
            Intent intent = getActivity().getIntent();

            if ( (intent != null) && (intent.hasExtra(Intent.EXTRA_TEXT))) {
                // Pegando a informação (texto) que veio com o Intent
                forecast = intent.getStringExtra(Intent.EXTRA_TEXT);

                // Colocando a mensagem no text_view
                TextView detailTextView = (TextView) rootView.findViewById(R.id.text_fragment_detail);
                detailTextView.setText(forecast);

            }
            return rootView;
        }

        //@Override
        public void onCreateOptions(Menu menu, MenuInflater inflater) {

            inflater.inflate(R.menu.detail_fragment,menu);

            MenuItem menuItem = menu.findItem(R.id.menu_item_share);

            ShareActionProvider mShareActionProvider =(ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
            if (mShareActionProvider != null) {
                mShareActionProvider.setShareIntent(createShareForecastIntent());
            } else {
                Log.d(LOG_TAG, "Sharing action provider is null.");
            }
        }

        public Intent createShareForecastIntent() {

            // Criando Intent + ShareActionProvider
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, forecast + "#Sunshine");

            return shareIntent;

        }

    }
}