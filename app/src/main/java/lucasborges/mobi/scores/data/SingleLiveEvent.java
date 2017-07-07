package lucasborges.mobi.scores.data;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Lucas Borges on 07/07/17.
 */
public class SingleLiveEvent<T> extends MutableLiveData<T> {

    private static final String TAG = "SingleLiveEvent";
    private final AtomicBoolean mPending = new AtomicBoolean(false);

    @MainThread
    public void observe(LifecycleOwner owner, final Observer<T> observer){
        if(hasActiveObservers()){
            Log.w(TAG, "Multiple observers registered, but only one will'be notified of changes.");
        }

        super.observe(owner, new Observer<T>() {
            @Override
            public void onChanged(@Nullable T t) {
                if(mPending.compareAndSet(true, false)){
                    observer.onChanged(t);
                }
            }
        });
    }

    @MainThread
    public void setValue(@Nullable T t){
        mPending.set(true);
        super.setValue(t);
    }

    @MainThread
    public void call(){
        setValue(null);
    }
}