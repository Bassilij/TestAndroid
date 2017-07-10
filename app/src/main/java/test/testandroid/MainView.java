package test.testandroid;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Bassilij on 05.07.2017.
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface MainView extends MvpView {
    void showMessage(int message);
}
