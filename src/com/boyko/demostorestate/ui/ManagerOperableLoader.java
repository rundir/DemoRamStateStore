package com.boyko.demostorestate.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.boyko.demostorestate.Logger;
import com.boyko.demostorestate.model.DataManager;
import com.boyko.demostorestate.model.ManagerOperable;
import com.boyko.demostorestate.model.ManagerOperableListener;

public class ManagerOperableLoader extends AsyncTaskLoader<List<ManagerOperable>> implements ManagerOperableListener{

	private static final String TAG = "ManagerOperableLoader";
	
	private List<ManagerOperable> data;
	private DataManager dataManger;
	
	public ManagerOperableLoader(Context context) {
		super(context);
	}

	@Override
	public List<ManagerOperable> loadInBackground() {
		List<ManagerOperable> data = new ArrayList<ManagerOperable>();
		Logger.d(TAG, "loadInBackground");
		
		dataManger = DataManager.getInstance();
		dataManger.addListener(this);
		Logger.d(TAG, "dataManger.addListener");
		data = dataManger.getDataAsList();
		
		return data;
	}
	
	  /********************************************************/
	  /** (2) Deliver the results to the registered listener **/
	  /********************************************************/

	  @Override
	  public void deliverResult(List<ManagerOperable> data) {
	    if (isReset()) {
	      // The Loader has been reset; ignore the result and invalidate the data.
	      releaseResources(data);
	      return;
	    }

	    // Hold a reference to the old data so it doesn't get garbage collected.
	    // We must protect it until the new data has been delivered.
	    List<ManagerOperable> oldData = this.data;
	    this.data = data;

	    if (isStarted()) {
	      // If the Loader is in a started state, deliver the results to the
	      // client. The superclass method does this for us.
	      super.deliverResult(data);
	    }

	    // Invalidate the old data as we don't need it any more.
	    if (oldData != null && oldData != data) {
	      releaseResources(oldData);
	    }
	  }

	  /*********************************************************/
	  /** (3) Implement the Loader’s state-dependent behavior **/
	  /*********************************************************/

	  @Override
	  protected void onStartLoading() {
			Logger.d(TAG, "onStartLoading");
	    if (this.data != null) {
	      // Deliver any previously loaded data immediately.
	      deliverResult(this.data);
	    }

	    if (takeContentChanged() || this.data == null) {
	      // When the observer detects a change, it should call onContentChanged()
	      // on the Loader, which will cause the next call to takeContentChanged()
	      // to return true. If this is ever the case (or if the current data is
	      // null), we force a new load.
	      forceLoad();
	    }
	  }

	  @Override
	  protected void onStopLoading() {
			Logger.d(TAG, "onStopLoading");
	    // The Loader is in a stopped state, so we should attempt to cancel the 
	    // current load (if there is one).
	    cancelLoad();

	    // Note that we leave the observer as is. Loaders in a stopped state
	    // should still monitor the data source for changes so that the Loader
	    // will know to force a new load if it is ever started again.
	  }

	  @Override
	  protected void onReset() {
			Logger.d(TAG, "onReset");
	    // Ensure the loader has been stopped.
	    onStopLoading();

	    // At this point we can release the resources associated with 'mData'.
	    if (this.data != null) {
	      releaseResources(this.data);
	      this.data = null;
	    }
	    
		  
		  dataManger.removeListener(this);
			Logger.d(TAG, "dataManger.removeListener");

	  }

	  @Override
	  public void onCanceled(List<ManagerOperable> data) {
	    // Attempt to cancel the current asynchronous load.
	    super.onCanceled(data);

	    // The load has been canceled, so we should release the resources
	    // associated with 'data'.
	    releaseResources(data);
	  }

	  private void releaseResources(List<ManagerOperable> data) {
	    // For a simple List, there is nothing to do. For something like a Cursor, we 
	    // would close it in this method. All resources associated with the Loader
	    // should be released here.
	  }

	@Override
	public void onChange() {
		Logger.d(TAG, "onChange");
		onContentChanged();
	}
}
