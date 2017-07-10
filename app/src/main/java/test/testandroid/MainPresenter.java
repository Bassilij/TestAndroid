package test.testandroid;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

/**
 * Created by Bassilij on 05.07.2017.
 */
@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {
    public MainPresenter() {
        getViewState().showMessage(R.string.hello_world);
    }
}
