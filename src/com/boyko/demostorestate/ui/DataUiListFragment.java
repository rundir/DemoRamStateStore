package com.boyko.demostorestate.ui;

import java.util.List;

import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;

import com.boyko.demostorestate.Logger;
import com.boyko.demostorestate.model.ManagerOperable;

public class DataUiListFragment extends ListFragment  implements LoaderManager.LoaderCallbacks<List<ManagerOperable>>{

	private static final String TAG = "DataUiListFragment";
	
	private ManagerOperableAdapter adapter;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Logger.d(TAG, "onActivityCreated");

		adapter = new ManagerOperableAdapter(getActivity(), null);
		setListAdapter(adapter);
		 setListShown(false);
		getLoaderManager().initLoader(0, null, this);
	}

	@Override
	public Loader<List<ManagerOperable>> onCreateLoader(int id, Bundle args) {
		Logger.d(TAG, "onCreateLoader");
		return  new ManagerOperableLoader(getActivity());
	}

	@Override
	public void onLoadFinished(Loader<List<ManagerOperable>> loader, List<ManagerOperable> data) {
		Logger.d(TAG, "onLoadFinished");
		adapter.setData(data);
		// The list should now be shown.
        if (isResumed()) {
            setListShown(true);
        } else {
            setListShownNoAnimation(true);
        }
	}

	@Override
	public void onLoaderReset(Loader<List<ManagerOperable>> loader) {
		Logger.d(TAG, "onLoaderReset");
		adapter.setData(null);
	}
}
